package edu.miu.cs489.dentalms.auth;

public record AuthenticationRequest(
        String username,
        String password
) {
}
