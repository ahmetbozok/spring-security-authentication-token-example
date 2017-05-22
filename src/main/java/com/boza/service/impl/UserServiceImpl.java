package com.boza.service.impl;

import com.boza.entity.RoleModel;
import com.boza.entity.UserModel;
import com.boza.repository.RoleRepository;
import com.boza.repository.UserRepository;
import com.boza.service.UserService;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public UserModel createUser(final UserModel user) {
        Preconditions.checkNotNull(user);

        UserModel savedUser = userRepository.save(user);
        if (user.getUserRoles() != null && !user.getUserRoles().isEmpty()) {
            for (RoleModel role: user.getUserRoles()) {
                role.setUser(user);
            }
            roleRepository.save(user.getUserRoles());
        }
        return savedUser;
    }

    @Override
    public UserModel getUser(final Long userId) {
        Preconditions.checkNotNull(userId);

        return userRepository.findOne(userId);
    }

    @Override
    public List<UserModel> getUsers() {
        List<UserModel> users = userRepository.findAll();
        if (users == null) {
            users = new ArrayList<>();
        }
        return users;
    }

    @Override
    public UserModel loadUserByUsername(final String username) throws UsernameNotFoundException {
        Preconditions.checkNotNull(username);

        final UserModel user = userRepository.getUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username was not found: " + username);
        }

        return user;
    }

    @Override
    public UserModel getUserByUserName(final String userName) {
        return loadUserByUsername(userName);
    }

}
