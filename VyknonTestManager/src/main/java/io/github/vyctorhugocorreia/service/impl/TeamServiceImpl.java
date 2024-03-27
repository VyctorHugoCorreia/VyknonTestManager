package io.github.vyctorhugocorreia.service.impl;

import io.github.vyctorhugocorreia.entity.TeamEntity;
import io.github.vyctorhugocorreia.repository.ProdutoRepository;
import io.github.vyctorhugocorreia.repository.TimeRepository;
import io.github.vyctorhugocorreia.exception.RegraNegocioException;
import io.github.vyctorhugocorreia.exception.TimeNaoEncontradoException;
import io.github.vyctorhugocorreia.dto.TeamDTO;
import io.github.vyctorhugocorreia.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.github.vyctorhugocorreia.util.UserInfo;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TimeRepository teamRepository;
    private final ProdutoRepository productRepository;
    private final UserInfo userInfo;
    @Override
    @Transactional
    public TeamEntity save(TeamDTO dto) {
        String name = dto.getName();
        if (dto.getName().trim().isEmpty()) {
            throw new RegraNegocioException("Preencha um nome válido");
        }
        if (dto.getName().trim().length() > 100) {
            throw new RegraNegocioException("O nome do time deve ter no máximo 100 caracteres");
        }
        validateIfTeamIsAlreadyRegistered(name);


        TeamEntity teamEntity = TeamEntity.builder()
                .nameTeam(name)
                .user(userInfo.user())
                .build();
        return teamRepository.save(teamEntity);
    }


    @Override
    @Transactional
    public TeamEntity edit(Long id, TeamDTO dto) {
        TeamEntity existingTime = getTeamById(id);
        String newName = dto.getName();
        if (newName.trim().isEmpty()) {
            throw new RegraNegocioException("Preencha um nome válido");
        }
        if (newName.trim().length() > 100) {
            throw new RegraNegocioException("O nome do time deve ter no máximo 100 caracteres");
        }
        if (!newName.toLowerCase(Locale.ROOT).equals(existingTime.getNameTeam().toLowerCase(Locale.ROOT))) {
            validateIfTeamIsAlreadyRegistered(newName);
        }

        existingTime.setNameTeam(newName);

        return teamRepository.save(existingTime);
    }

    @Override
    @Transactional
    public String delete(Long id) {
        TeamEntity teamEntity = getTeamById(id);
        checkedLinkedProducts(teamEntity);

        teamRepository.delete(teamEntity);

        return "Time deletado com sucesso.";
    }

    private TeamEntity getTeamById(Long id) {
        return teamRepository.findById(id.intValue())
                .orElseThrow(TimeNaoEncontradoException::new);
    }

    private void validateIfTeamIsAlreadyRegistered(String name) {
        if (teamRepository.existsByNomeTime(name)) {
            throw new RegraNegocioException("Já existe um time com o mesmo nome.");
        }
    }

    private void checkedLinkedProducts(TeamEntity time) {
        if (!productRepository.findByIdTime(time).isEmpty()) {
            throw new RegraNegocioException("Não é possível excluir o time, pois há produtos vinculados a ele.");
        }
    }
}
