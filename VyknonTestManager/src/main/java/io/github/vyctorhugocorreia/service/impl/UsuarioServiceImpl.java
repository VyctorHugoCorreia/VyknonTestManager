package io.github.vyctorhugocorreia.service.impl;


import io.github.vyctorhugocorreia.dto.UsuarioDTO;
import io.github.vyctorhugocorreia.entity.PerfilDeAcessoEntity;
import io.github.vyctorhugocorreia.entity.ProdutoEntity;
import io.github.vyctorhugocorreia.entity.TimeEntity;
import io.github.vyctorhugocorreia.entity.UsuarioEntity;

import io.github.vyctorhugocorreia.exception.ProdutoNaoEncontradoException;
import io.github.vyctorhugocorreia.exception.RegraNegocioException;
import io.github.vyctorhugocorreia.exception.TimeNaoEncontradoException;
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

        if (repository.existsByLogin(login)) {
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
                .status("ACTIVE")
                .build();
        return repository.save(usuario);
    }

    @Transactional
    public UsuarioEntity editar(String id, UsuarioDTO dto) {
        UsuarioEntity usuarioExistente = getExistingUser(id);
        String nome = dto.getNome();
        String login = dto.getLogin();
        String senha = dto.getSenha();
        String senhaAntiga = dto.getSenhaAntiga();
        String perfilDeAcesso = dto.getPerfilDeAcesso().getNome();

        if (Objects.nonNull(nome) && !nome.isEmpty() && repository.existsByNome(nome) && !Objects.equals(usuarioExistente.getNome(), nome)) {
            throw new RegraNegocioException("Já existe um usuário com este nome.");
        }

        if (Objects.nonNull(login) && !login.isEmpty() && repository.existsByLogin(login) && !Objects.equals(usuarioExistente.getLogin(), login)) {
            throw new RegraNegocioException("Já existe um usuário com este login.");
        }

        if (Objects.nonNull(senhaAntiga) && !senhaAntiga.isEmpty() && !passwordEncoder.matches(senhaAntiga, usuarioExistente.getSenha())) {
            throw new RegraNegocioException("Senha atual inválida.");
        }

        PerfilDeAcessoEntity perfilDeAcessoEntity = perfilDeAcessoRepository
                .findByNome(perfilDeAcesso)
                .orElseThrow(() -> new RegraNegocioException("Perfil de acesso não encontrado"));

        if(Objects.nonNull(nome) && !nome.isEmpty()){
            usuarioExistente.setNome(nome);
        }
        if(Objects.nonNull(login) && !login.isEmpty()){
            usuarioExistente.setLogin(login);
        }
        if (senha != null && !senha.isEmpty()) {
            usuarioExistente.setSenha(passwordEncoder.encode(senha));
        }
        usuarioExistente.setPerfilDeAcesso(perfilDeAcessoEntity);

        return repository.save(usuarioExistente);
    }

    @Transactional
    public UsuarioEntity editarSenha(UsuarioDTO dto) {
        UsuarioEntity usuarioExistente = getExistingUser(dto.getLogin());
        String login = dto.getLogin();
        String senha = dto.getSenha();
        String senhaAntiga = dto.getSenhaAntiga();

        if (Objects.nonNull(login) && !login.isEmpty() && repository.existsByLogin(login) && !Objects.equals(usuarioExistente.getLogin(), login)) {
            throw new RegraNegocioException("Já existe um usuário com este login.");
        }

        if (Objects.nonNull(senhaAntiga) && !senhaAntiga.isEmpty() && !passwordEncoder.matches(senhaAntiga, usuarioExistente.getSenha())) {
            throw new RegraNegocioException("Senha atual inválida.");
        }

            usuarioExistente.setLogin(login);

            usuarioExistente.setSenha(passwordEncoder.encode(senha));

        return repository.save(usuarioExistente);
    }

    private UsuarioEntity getExistingUser(String login) {
        return repository.findByLogin(login)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado com o login: " + login));
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
        boolean userExists = repository.existsByLogin(usuario.getLogin());

        if (!userExists) {
            throw new RegraNegocioException("Usuário não encontrado.");
        }

        boolean userExistsInactive = repository.existsByLoginAndStatus(usuario.getLogin(), "INACTIVE");

        if (userExistsInactive) {
            throw new RegraNegocioException("Usuário inativo, entre em contato com o administrador para ativar.");
        }

        UserDetails userDetails = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = passwordEncoder.matches(usuario.getSenha(), userDetails.getPassword());

        if (senhasBatem) {
            return userDetails;
        } else {
            throw new RegraNegocioException("Usuário ou senha inválidos.");
        }
    }

    @Override
    @Transactional
    public String deletar(String id, String status) {
        UsuarioEntity usuario = getUserByLogin(id);
        usuario.setStatus(status.toUpperCase());
        repository.save(usuario);

        switch (status.toUpperCase()) {
            case "ACTIVE":
                return "Usuário ativado com sucesso.";
            case "INACTIVE":
                return "Usuário inativado com sucesso.";
            default:
                throw new IllegalArgumentException("Status inválido: " + status);
        }
    }



    private UsuarioEntity getUserByLogin(String id) {
        return repository.findById(id)
                .orElseThrow(TimeNaoEncontradoException::new);
    }





}
