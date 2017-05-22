package com.boza.controller;

import com.boza.facade.UserFacade;
import com.boza.service.UserService;
import com.boza.swaggergen.controller.UsersApi;
import com.boza.swaggergen.model.CreateUserRequest;
import com.boza.swaggergen.model.User;
import com.boza.swaggergen.model.UserResponse;
import com.boza.swaggergen.model.UsersResponse;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends UsersApi{

    @Autowired
    private UserFacade userFacade;

    @Override
    public UserResponse createUser(final CreateUserRequest createUserRequest, final HttpSession httpSession) {
        User user = userFacade.createUser(createUserRequest.getUser());
        UserResponse response = new UserResponse();
        response.setSuccess(Boolean.TRUE);
        response.setUser(user);
        return response;
    }

    @Override
    public UserResponse getUser(final String userId, final HttpSession httpSession) {
        User user = userFacade.getUser(userId);
        UserResponse response = new UserResponse();
        response.setSuccess(Boolean.TRUE);
        response.setUser(user);
        return response;
    }

    @Override
    public UsersResponse users(final HttpSession httpSession) {
        List<User> users = userFacade.getUsers();
        UsersResponse response = new UsersResponse();
        response.setSuccess(Boolean.TRUE);
        response.setUsers(users);
        return response;
    }
}
