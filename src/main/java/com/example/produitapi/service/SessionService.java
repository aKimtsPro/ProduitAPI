package com.example.produitapi.service;

import com.example.produitapi.config.jwt.JwtTokenProvider;
import com.example.produitapi.mapper.UserMapper;
import com.example.produitapi.models.dto.UserDTO;
import com.example.produitapi.models.entity.Role;
import com.example.produitapi.models.entity.User;
import com.example.produitapi.models.form.UserLoginForm;
import com.example.produitapi.models.form.UserRegisterForm;
import com.example.produitapi.repository.RoleRepository;
import com.example.produitapi.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SessionService {

    private final AuthenticationManager authManager;
    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider tokenProvider;
    private final RoleRepository roleRepository;

    public SessionService(AuthenticationManager authManager, UserRepository repository, UserMapper mapper, PasswordEncoder encoder, JwtTokenProvider tokenProvider, RoleRepository roleRepository) {
        this.authManager = authManager;
        this.repository = repository;
        this.mapper = mapper;
        this.encoder = encoder;
        this.tokenProvider = tokenProvider;
        this.roleRepository = roleRepository;
    }

    public UserDTO signIn(UserLoginForm form){

        try {
            authManager.authenticate( new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword()) );
            UserDTO dto = mapper.toDto( repository.findByUsername(form.getUsername()).orElseThrow() );
            dto.setToken( tokenProvider.createToken(dto.getUsername(), dto.getRoles()) );
            return dto;
        }catch (AuthenticationException | NoSuchElementException exception){
            throw new RuntimeException("Username/Password invalides");
        }

    }

    public UserDTO signUp(UserRegisterForm form){

        User user = new User();
        user.setUsername(form.getUsername());
        user.setPassword(encoder.encode(form.getPassword()));

        user.setRoles( List.of( roleRepository.findByNom("USER").orElseThrow() ) );

        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialNonExpired(true);
        user.setEnabled(true);

        UserDTO rslt = mapper.toDto( repository.save(user) );
        rslt.setToken( tokenProvider.createToken(rslt.getUsername(), rslt.getRoles()) );

        return rslt;
    }
}
