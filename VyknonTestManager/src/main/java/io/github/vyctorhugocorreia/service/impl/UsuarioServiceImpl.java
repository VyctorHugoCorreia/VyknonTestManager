package io.github.vyctorhugocorreia.service.impl;


import io.github.vyctorhugocorreia.entity.PerfilDeAcessoEntity;
import io.github.vyctorhugocorreia.entity.UsuarioEntity;

import io.github.vyctorhugocorreia.entity.UsuarioPerfilDeAcessoEntity;
import io.github.vyctorhugocorreia.repository.PerfilDeAcessoRepository;
import io.github.vyctorhugocorreia.repository.UsuarioPerfilDeAcessoRepository;
import io.github.vyctorhugocorreia.repository.UsuarioRepository;
import io.github.vyctorhugocorreia.service.UsuarioService;
import lombok.RequiredArgsConstructor;
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
    private final UsuarioPerfilDeAcessoRepository usuarioPerfilDeAcessoRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UsuarioEntity salvar(UsuarioEntity usuario, List<String> grupos){
      String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        repository.save(usuario);

        List<UsuarioPerfilDeAcessoEntity> listaUsuarioPerfilDeAcesso = grupos.stream().map(nomePerfil -> {
                    Optional<PerfilDeAcessoEntity> perfil = perfilDeAcessoRepository.findByNome(nomePerfil);
                    if (perfil.isPresent()) {
                        PerfilDeAcessoEntity perfilDeAcesso = perfil.get();
                        return new UsuarioPerfilDeAcessoEntity(usuario, perfilDeAcesso);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        usuarioPerfilDeAcessoRepository.saveAll(listaUsuarioPerfilDeAcesso);

        return usuario;
    }

    @Transactional
    public UsuarioEntity obterUsuarioComPerfilDeAcesso(String login){
        Optional<UsuarioEntity> usuarioOptional = repository.findByLogin(login);
        if(usuarioOptional.isEmpty()){
            return null;
        }

        UsuarioEntity usuario = usuarioOptional.get();
        List<String> permissoes = usuarioPerfilDeAcessoRepository.findPerfilDeAcessoByUsuario(usuario);
        usuario.setPerfilDeAcesso(permissoes);

        return usuario;
    }

}
