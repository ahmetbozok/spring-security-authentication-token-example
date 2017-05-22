package com.boza.service;

import com.boza.entity.UserModel;
import java.util.List;

public interface UserService {
    UserModel createUser(UserModel user);
    UserModel getUser(Long userId);
    List<UserModel> getUsers();
    UserModel getUserByUserName(String userName);
}
