package io.github.vyctorhugocorreia.service.impl;

import io.github.vyctorhugocorreia.entity.TeamEntity;
import io.github.vyctorhugocorreia.repository.ProductRepository;
import io.github.vyctorhugocorreia.repository.TeamRepository;
import io.github.vyctorhugocorreia.exception.RuleBusinessException;
import io.github.vyctorhugocorreia.exception.TeamNotFoundException;
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

    private final TeamRepository teamRepository;
    private final ProductRepository productRepository;
    private final UserInfo userInfo;
    @Override
    @Transactional
    public TeamEntity save(TeamDTO dto) {
        String name = dto.getName();
        if (dto.getName().trim().isEmpty()) {
            throw new RuleBusinessException("Preencha um nome válido");
        }
        if (dto.getName().trim().length() > 100) {
            throw new RuleBusinessException("O nome do time deve ter no máximo 100 caracteres");
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
            throw new RuleBusinessException("Preencha um nome válido");
        }
        if (newName.trim().length() > 100) {
            throw new RuleBusinessException("O nome do time deve ter no máximo 100 caracteres");
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
                .orElseThrow(TeamNotFoundException::new);
    }

    private void validateIfTeamIsAlreadyRegistered(String name) {
        if (teamRepository.existsByNameTeam(name)) {
            throw new RuleBusinessException("Já existe um time com o mesmo nome.");
        }
    }

    private void checkedLinkedProducts(TeamEntity team) {
        if (!productRepository.findByIdTeam(team).isEmpty()) {
            throw new RuleBusinessException("Não é possível excluir o time, pois há produtos vinculados a ele.");
        }
    }
}
