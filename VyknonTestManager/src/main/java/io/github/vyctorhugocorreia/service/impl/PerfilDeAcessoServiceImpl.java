package io.github.vyctorhugocorreia.service.impl;


import io.github.vyctorhugocorreia.dto.PerfilDeAcessoDTO;
import io.github.vyctorhugocorreia.dto.UsuarioDTO;
import io.github.vyctorhugocorreia.entity.PerfilDeAcessoEntity;
import io.github.vyctorhugocorreia.entity.UsuarioEntity;
import io.github.vyctorhugocorreia.exception.RegraNegocioException;
import io.github.vyctorhugocorreia.repository.PerfilDeAcessoRepository;
import io.github.vyctorhugocorreia.repository.UsuarioRepository;
import io.github.vyctorhugocorreia.service.PerfilDeAcessoService;
import io.github.vyctorhugocorreia.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PerfilDeAcessoServiceImpl implements PerfilDeAcessoService {
    private final PerfilDeAcessoRepository repository;

    @Transactional
    public PerfilDeAcessoEntity salvar(PerfilDeAcessoDTO dto) {
        String nome = dto.getNome();

        if (repository.existsByNome(nome)) {
            throw new RegraNegocioException("Já existe um perfil com este nome.");
        }

        PerfilDeAcessoEntity perfil = PerfilDeAcessoEntity.builder()
                .nome(nome)
                .build();

        return repository.save(perfil);
    }




}
