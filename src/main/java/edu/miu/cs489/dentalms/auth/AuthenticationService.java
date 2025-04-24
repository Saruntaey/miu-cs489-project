package edu.miu.cs489.dentalms.auth;

import edu.miu.cs489.dentalms.config.JwtService;
import edu.miu.cs489.dentalms.exception.user.EmailDuplicateException;
import edu.miu.cs489.dentalms.model.OfficeManager;
import edu.miu.cs489.dentalms.user.Repo.UserRepo;
import edu.miu.cs489.dentalms.user.Role;
import edu.miu.cs489.dentalms.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.username(),
                        authenticationRequest.password()
                )
        );
        //generate token for the user
        var user = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse registerOfficeManager(RegisterRequest registerRequest) {
        if (userRepo.findByEmail(registerRequest.username()).isPresent()) {
            throw new EmailDuplicateException(registerRequest.username());
        }
        User user = new OfficeManager(
                registerRequest.firstName(),
                registerRequest.lastName(),
                registerRequest.username(),
                passwordEncoder.encode(registerRequest.password())
        );
        User registeredUser = userRepo.save(user);
        //Generate token for this user
        String token = jwtService.generateToken(registeredUser);
        return new AuthenticationResponse(token);
    }

    public MeResponse me() {
        var p = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new MeResponse(
                p.getId(),
                p.getEmail(),
                p.getRole()
        );
    }

}
