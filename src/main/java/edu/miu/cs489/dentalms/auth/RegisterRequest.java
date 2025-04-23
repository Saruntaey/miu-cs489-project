package edu.miu.cs489.dentalms.auth;


import edu.miu.cs489.dentalms.user.Role;

public record RegisterRequest(
        String firstName,
        String lastName,
        String username,
        String password
) {
}
