package com.boza.service;

import com.boza.entity.AuthenticationToken;
import com.boza.entity.UserModel;
import com.boza.swaggergen.model.User;

public interface TokenService {
    /**
     * Creates a new token for the user and returns its {@link AuthenticationToken}.
     * It may add it to the token list or replace the previous one for the user. Never returns {@code null}.
     */
    AuthenticationToken createAuthenticationToken(User user);

    /** Returns user details for a token. */
    UserModel findUserByAccessToken(String token);

}
