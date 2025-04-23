package edu.miu.cs489.dentalms.auth;

import edu.miu.cs489.dentalms.user.Role;

public record MeResponse(
        Long id,
        String email,
        Role role
) {
}
