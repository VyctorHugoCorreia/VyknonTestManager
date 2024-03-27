package io.github.vyctorhugocorreia.service.impl;


import io.github.vyctorhugocorreia.dto.UserDTO;
import io.github.vyctorhugocorreia.entity.AccessProfileEntity;
import io.github.vyctorhugocorreia.entity.UserEntity;

import io.github.vyctorhugocorreia.exception.RuleBusinessException;
import io.github.vyctorhugocorreia.repository.AccessProfileRepository;
import io.github.vyctorhugocorreia.repository.UserRepository;
import io.github.vyctorhugocorreia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final AccessProfileRepository accessProfileRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserEntity save(UserDTO dto){
        String name = dto.getName();
        String login = dto.getLogin();
        String password = passwordEncoder.encode(dto.getPassword());
        String accessProfile = dto.getAccessProfile().getName();

        if (repository.existsByName(name)) {
            throw new RuleBusinessException("Já existe um usuário com este nome.");
        }

        if (repository.existsByLogin(login)) {
            throw new RuleBusinessException("Já existe um usuário com este login.");
        }

        AccessProfileEntity accessProfileEntity = accessProfileRepository
                .findByName(accessProfile)
                .orElseThrow(() -> new RuleBusinessException("Perfil de acesso não encontrado"));

        UserEntity userEntity = UserEntity.builder()
                .name(name)
                .login(login)
                .password(password)
                .accessProfile(accessProfileEntity)
                .status("ACTIVE")
                .build();
        return repository.save(userEntity);
    }

    @Transactional
    public UserEntity edit(String id, UserDTO dto) {
        UserEntity existingUser = getExistingUser(id);
        String name = dto.getName();
        String login = dto.getLogin();
        String password = dto.getPassword();
        String oldPassword = dto.getOldPassword();
        String accessProfile = dto.getAccessProfile().getName();

        if (Objects.nonNull(name) && !name.isEmpty() && repository.existsByName(name) && !Objects.equals(existingUser.getName(), name)) {
            throw new RuleBusinessException("Já existe um usuário com este nome.");
        }

        if (Objects.nonNull(login) && !login.isEmpty() && repository.existsByLogin(login) && !Objects.equals(existingUser.getLogin(), login)) {
            throw new RuleBusinessException("Já existe um usuário com este login.");
        }

        if (Objects.nonNull(oldPassword) && !oldPassword.isEmpty() && !passwordEncoder.matches(oldPassword, existingUser.getPassword())) {
            throw new RuleBusinessException("Senha atual inválida.");
        }

        AccessProfileEntity accessProfileEntity = accessProfileRepository
                .findByName(accessProfile)
                .orElseThrow(() -> new RuleBusinessException("Perfil de acesso não encontrado"));

        if(Objects.nonNull(name) && !name.isEmpty()){
            existingUser.setName(name);
        }
        if(Objects.nonNull(login) && !login.isEmpty()){
            existingUser.setLogin(login);
        }
        if (password != null && !password.isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(password));
        }
        existingUser.setAccessProfile(accessProfileEntity);

        return repository.save(existingUser);
    }

    @Transactional
    public UserEntity passwordEdit(UserDTO dto) {
        UserEntity existingUser = getExistingUser(dto.getLogin());
        String login = dto.getLogin();
        String password = dto.getPassword();
        String oldPassword = dto.getOldPassword();

        if (Objects.nonNull(login) && !login.isEmpty() && repository.existsByLogin(login) && !Objects.equals(existingUser.getLogin(), login)) {
            throw new RuleBusinessException("Já existe um usuário com este login.");
        }

        if (Objects.nonNull(oldPassword) && !oldPassword.isEmpty() && !passwordEncoder.matches(oldPassword, existingUser.getPassword())) {
            throw new RuleBusinessException("Senha atual inválida.");
        }

        existingUser.setLogin(login);

        existingUser.setPassword(passwordEncoder.encode(password));

        return repository.save(existingUser);
    }

    private UserEntity getExistingUser(String login) {
        return repository.findByLogin(login)
                .orElseThrow(() -> new RuleBusinessException("Usuário não encontrado com o login: " + login));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserEntity usuario = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado."));


        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getPassword())
                .roles(String.valueOf(usuario.getAccessProfile()))
                .build();
    }

    public UserDetails authentication(UserEntity usuario) throws RuleBusinessException {
        boolean userExists = repository.existsByLogin(usuario.getLogin());

        if (!userExists) {
            throw new RuleBusinessException("Usuário não encontrado.");
        }

        boolean userExistsInactive = repository.existsByLoginAndStatus(usuario.getLogin(), "INACTIVE");

        if (userExistsInactive) {
            throw new RuleBusinessException("Usuário inativo, entre em contato com o administrador para ativar.");
        }

        UserDetails userDetails = loadUserByUsername(usuario.getLogin());
        boolean passwordEquals = passwordEncoder.matches(usuario.getPassword(), userDetails.getPassword());

        if (passwordEquals) {
            return userDetails;
        } else {
            throw new RuleBusinessException("Usuário ou senha inválidos.");
        }
    }

    @Override
    @Transactional
    public String delete(String id, String status) {
        UserEntity userEntity = getUserByLogin(id);
        userEntity.setStatus(status.toUpperCase());
        repository.save(userEntity);

        switch (status.toUpperCase()) {
            case "ACTIVE":
                return "Usuário ativado com sucesso.";
            case "INACTIVE":
                return "Usuário inativado com sucesso.";
            default:
                throw new IllegalArgumentException("Status inválido: " + status);
        }
    }



    private UserEntity getUserByLogin(String id) {
        return repository.findById(id)
                .orElseThrow( () -> new RuleBusinessException("Time não encontrado"));
    }





}
