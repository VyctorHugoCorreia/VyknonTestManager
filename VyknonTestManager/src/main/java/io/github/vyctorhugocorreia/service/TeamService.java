package io.github.vyctorhugocorreia.service;

import io.github.vyctorhugocorreia.entity.TeamEntity;
import io.github.vyctorhugocorreia.dto.TeamDTO;

public interface TeamService {

    TeamEntity save(TeamDTO dto);

    TeamEntity edit(Long id, TeamDTO dto);

    String delete(Long id);

}
