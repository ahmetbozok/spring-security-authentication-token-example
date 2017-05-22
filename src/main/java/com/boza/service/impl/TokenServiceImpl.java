package com.boza.service.impl;

import com.boza.entity.AuthenticationToken;
import com.boza.entity.UserModel;
import com.boza.repository.AuthenticationTokenRepository;
import com.boza.service.TokenService;
import com.boza.swaggergen.model.User;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticationTokenRepository authenticationTokenRepository;

    @Override
    public AuthenticationToken createAuthenticationToken(final User user) {
        UserModel userModel = modelMapper.map(user, UserModel.class);
        AuthenticationToken accessToken = new AuthenticationToken(userModel, UUID.randomUUID().toString());
        return this.authenticationTokenRepository.save(accessToken);
    }

    @Override
    public UserModel findUserByAccessToken(final String token) {
        AuthenticationToken accessToken = this.authenticationTokenRepository.findByToken(token);

        if (null == accessToken) {
            return null;
        }

        if (accessToken.isExpired()) {
            this.authenticationTokenRepository.delete(accessToken);
            return null;
        }

        return accessToken.getUser();
    }
}
