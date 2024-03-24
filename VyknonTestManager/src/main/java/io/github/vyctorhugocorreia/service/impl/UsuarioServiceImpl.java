package io.github.vyctorhugocorreia.service.impl;


import io.github.vyctorhugocorreia.dto.UsuarioDTO;
import io.github.vyctorhugocorreia.entity.PerfilDeAcessoEntity;
import io.github.vyctorhugocorreia.entity.TimeEntity;
import io.github.vyctorhugocorreia.entity.UsuarioEntity;

import io.github.vyctorhugocorreia.exception.RegraNegocioException;
import io.github.vyctorhugocorreia.repository.PerfilDeAcessoRepository;
import io.github.vyctorhugocorreia.repository.UsuarioRepository;
import io.github.vyctorhugocorreia.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository repository;
    private final PerfilDeAcessoRepository perfilDeAcessoRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UsuarioEntity salvar(UsuarioDTO dto){
        String nome = dto.getNome();
        String login = dto.getLogin();
        String senha = passwordEncoder.encode(dto.getSenha());
        String perfilDeAcesso = dto.getPerfilDeAcesso().getNome();

        if (repository.existsByNome(nome)) {
            throw new RegraNegocioException("Já existe um usuário com este nome.");
        }

        if (repository.existsBylogin(login)) {
            throw new RegraNegocioException("Já existe um usuário com este login.");
        }

        PerfilDeAcessoEntity perfilDeAcessoEntity = perfilDeAcessoRepository
                .findByNome(perfilDeAcesso)
                .orElseThrow(() -> new RegraNegocioException("Perfil de acesso não encontrado"));

        UsuarioEntity usuario = UsuarioEntity.builder()
                .nome(nome)
                .login(login)
                .senha(senha)
                .perfilDeAcesso(perfilDeAcessoEntity)
                .build();
        return repository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UsuarioEntity usuario = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado."));


        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(String.valueOf(usuario.getPerfilDeAcesso()))
                .build();
    }

    public UserDetails autenticar(UsuarioEntity usuario) throws RegraNegocioException {
        boolean userExists = repository.existsBylogin(usuario.getLogin());

        if (!userExists) {
            throw new RegraNegocioException("Usuário ou senha inválidos.");
        }

        UserDetails userDetails = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = passwordEncoder.matches(usuario.getSenha(), userDetails.getPassword());

        if (senhasBatem) {
            return userDetails;
        } else {
            throw new RegraNegocioException("Usuário ou senha inválidos.");
        }
    }






}
