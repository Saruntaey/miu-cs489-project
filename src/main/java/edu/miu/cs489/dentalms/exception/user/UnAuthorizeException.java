package edu.miu.cs489.dentalms.exception.user;

public class UnAuthorizeException extends RuntimeException {
    public UnAuthorizeException() {
        super("unauthorized");
    }
}
