package com.boza.controller;

import com.boza.exception.AuthenticationException;
import com.boza.exception.InvalidCredentialExceptiın;
import com.boza.exception.UnauthorizedException;
import com.boza.swaggergen.model.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerErrorAdvice {

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BaseResponse handleUserNotFoundException(UsernameNotFoundException ex) {
        BaseResponse response = new BaseResponse();
        response.setSuccess(Boolean.FALSE);
        response.setError("1");
        response.setMessage(ex.getMessage());
        return response;
    }

    @ExceptionHandler(InvalidCredentialExceptiın.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public BaseResponse  handleInvalidCredentialException(InvalidCredentialExceptiın ex) {
        BaseResponse response = new BaseResponse();
        response.setSuccess(Boolean.FALSE);
        response.setError("2");
        response.setMessage("Invalid username or password!");
        return response;
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public BaseResponse  handleAuthenticationException(AuthenticationException ex) {
        BaseResponse response = new BaseResponse();
        response.setSuccess(Boolean.FALSE);
        response.setError("3");
        response.setMessage("Invalid token!");
        return response;
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public BaseResponse  handleUnauthorizedException(UnauthorizedException ex) {
        BaseResponse response = new BaseResponse();
        response.setSuccess(Boolean.FALSE);
        response.setError("4");
        response.setMessage("Access denied!");
        return response;
    }

}
