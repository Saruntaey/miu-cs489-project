package edu.miu.cs489.dentalms.exception.user;

public class EmailDuplicateException extends RuntimeException{
    public EmailDuplicateException(String email) {
        super("email : " + email + " already exists");
    }
}
