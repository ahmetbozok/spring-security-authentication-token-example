package com.boza.facade;

import com.boza.swaggergen.model.Credential;
import com.boza.swaggergen.model.User;
import java.util.List;

public interface UserFacade {
    User createUser(User user);
    User getUser(String userId);
    List<User> getUsers();
    User getUserByUserName(String userName);
    User checkCredential(Credential credential);
}
