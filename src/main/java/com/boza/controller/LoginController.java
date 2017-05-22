package com.boza.controller;

import com.boza.entity.AuthenticationToken;
import com.boza.entity.UserModel;
import com.boza.exception.UnauthorizedException;
import com.boza.facade.UserFacade;
import com.boza.service.TokenService;
import com.boza.swaggergen.controller.AuthApi;
import com.boza.swaggergen.model.AccessToken;
import com.boza.swaggergen.model.Credential;
import com.boza.swaggergen.model.User;
import javax.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController extends AuthApi {

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authManager;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AccessToken authentication(final Credential credential, final HttpSession httpSession) {
        User user = userFacade.checkCredential(credential);

        UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(credential.getUsername(), credential.getPassword());
        Authentication authentication = this.authManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserModel)) {
            throw new UnauthorizedException();
        }

        AuthenticationToken authToken = tokenService.createAuthenticationToken(user);
        return modelMapper.map(authToken, AccessToken.class);
    }
}
