package com.example.produitapi.service;

import com.example.produitapi.mapper.impl.UserMapper;
import com.example.produitapi.models.dto.UserDTO;
import com.example.produitapi.models.entity.User;
import com.example.produitapi.models.form.user.UserForm;
import com.example.produitapi.repository.RoleRepository;
import com.example.produitapi.repository.RoleUserRepository;
import com.example.produitapi.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, BaseService<UserDTO, UserForm, UserForm, Long> {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final RoleUserRepository roleUserRepository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;

    public UserDetailsServiceImpl(UserRepository repository, RoleRepository roleRepository, RoleUserRepository roleUserRepository, UserMapper mapper, PasswordEncoder encoder) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.roleUserRepository = roleUserRepository;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("L'utilisateur avec le username " + s + " n'existe pas."));
    }

    @Override
    @PreAuthorize("hasAuthority('USER')")
    public UserDTO getOne(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("utilisateur d'id {"+id+"} non trouvé"));
    }

    @Override
    @PreAuthorize("hasAuthority('USER')")
    public List<UserDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserDTO insert(UserForm form) {
        User user = mapper.formToEntity(form);

        user.setPassword( encoder.encode(user.getPassword()) );

        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialNonExpired(true);
        user.setEnabled(true);

        return mapper.toDto(repository.save( user ));
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserDTO delete(Long id) {

        User toDelete =  repository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("utilisateur d'id {"+id+"}"));

        repository.delete(toDelete);

        return mapper.toDto( toDelete );
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserDTO update(Long id, UserForm form) { // TODO : separer update basic info/pswd/adresse/4bool

        User toUpdate = repository.findById(id)
                    .orElseThrow(()-> new IllegalArgumentException("utilisateur d'id {"+id+"}"));

        toUpdate.setUsername( form.getUsername() );
        toUpdate.setPassword( encoder.encode(form.getPassword()) );
        toUpdate.setEmail(form.getEmail());
        toUpdate.setMoyenPayement(form.getMoyenPayement());

        toUpdate = repository.save(toUpdate);

        return mapper.toDto( toUpdate );

    }
}
