package com.flab.commerce.domain.user.exception;


import javax.security.sasl.AuthenticationException;

public class UnauthenticatedUserException extends AuthenticationException {

    public UnauthenticatedUserException(String message) {
        super(message);
    }
}
