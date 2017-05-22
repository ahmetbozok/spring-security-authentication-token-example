package com.boza.facade.impl;

import com.boza.entity.UserModel;
import com.boza.exception.InvalidCredentialExceptiın;
import com.boza.facade.UserFacade;
import com.boza.service.UserService;
import com.boza.swaggergen.model.Credential;
import com.boza.swaggergen.model.User;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Override
    public User createUser(final User user) {
        UserModel userModel = modelMapper.map(user, UserModel.class);
        userModel = userService.createUser(userModel);
        return modelMapper.map(userModel, User.class);
    }

    @Override
    public User getUser(final String userId) {
        UserModel userModel = userService.getUser(new Long(userId));
        return modelMapper.map(userModel, User.class);
    }

    @Override
    public List<User> getUsers() {
        Iterable<UserModel> userModels = userService.getUsers();
        List<User> users = null;
        if (userModels != null) {
            users = Lists.newArrayList(userModels).stream()
            .map(u -> modelMapper.map(u, User.class)).collect(Collectors.toList());
        }

        return users;
    }

    @Override
    public User getUserByUserName(final String userName) {
        UserModel userModel = userService.getUserByUserName(userName);
        return modelMapper.map(userModel, User.class);
    }

    @Override
    public User checkCredential(final Credential credential) {
        User user = getUserByUserName(credential.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("Username was not found: " + credential.getUsername());
        }

        if (!user.getPassword().equals(credential.getPassword())) {
            throw new InvalidCredentialExceptiın();
        }

        return user;
    }
}
