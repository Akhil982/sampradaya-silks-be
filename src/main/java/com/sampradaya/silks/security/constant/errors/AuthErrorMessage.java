package com.sampradaya.silks.security.constant.errors;

public final class AuthErrorMessage {
    private AuthErrorMessage() {}

    public static final String UN_AUTHENTICATED = "User is not authenticated";

    public static final String UN_AUTHORIZED_PROJECTS = "User doesn't have access to projects.";

    public static final String UN_AUTHORIZED_CLIENTS = "User doesn't have access to clients.";

    public static final String UN_AUTHORIZED_USECASE = "User doesn't have access to use case.";
}
