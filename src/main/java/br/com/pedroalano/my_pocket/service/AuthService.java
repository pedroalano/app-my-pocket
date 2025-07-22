package br.com.pedroalano.my_pocket.service;

import br.com.pedroalano.my_pocket.dto.AuthRequest;
import br.com.pedroalano.my_pocket.dto.AuthResponse;
import br.com.pedroalano.my_pocket.dto.RegisterRequest;
import br.com.pedroalano.my_pocket.model.User;
import br.com.pedroalano.my_pocket.repository.UserRepository;
import br.com.pedroalano.my_pocket.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {
        var user = new User(null,request.name(),request.email(),passwordEncoder.encode(request.password()), "client");
        userRepository.save(user);
    }

    public AuthResponse login(AuthRequest request) {
        var user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        var token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
}
