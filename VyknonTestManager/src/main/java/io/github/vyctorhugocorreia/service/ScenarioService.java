package io.github.vyctorhugocorreia.service;

import io.github.vyctorhugocorreia.dto.ScenarioDTO;
import io.github.vyctorhugocorreia.entity.ScenarioEntity;

public interface ScenarioService {

    ScenarioEntity save(ScenarioDTO dto);

  ScenarioEntity edit(Long id, ScenarioDTO dto);

  String delete(Long id);


}
