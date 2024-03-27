package io.github.vyctorhugocorreia.service.impl;


import io.github.vyctorhugocorreia.dto.AccessProfileDTO;
import io.github.vyctorhugocorreia.entity.AccessProfileEntity;
import io.github.vyctorhugocorreia.exception.RegraNegocioException;
import io.github.vyctorhugocorreia.repository.PerfilDeAcessoRepository;
import io.github.vyctorhugocorreia.service.AccessProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccessProfileServiceImpl implements AccessProfileService {
    private final PerfilDeAcessoRepository repository;

    @Transactional
    public AccessProfileEntity save(AccessProfileDTO dto) {
        String name = dto.getName();

        if (repository.existsByNome(name)) {
            throw new RegraNegocioException("Já existe um perfil com este nome.");
        }

        AccessProfileEntity perfil = AccessProfileEntity.builder()
                .name(name)
                .build();

        return repository.save(perfil);
    }




}
