package io.github.vyctorhugocorreia.service;

import io.github.vyctorhugocorreia.dto.CenarioDeTesteDTO;
import io.github.vyctorhugocorreia.dto.HistoryStatusScenarioDTO;
import io.github.vyctorhugocorreia.entity.CenarioDeTesteEntity;
import io.github.vyctorhugocorreia.entity.HistoryStatusScenarioEntity;

public interface HistoriyStatusScenarioService {

    HistoryStatusScenarioEntity salvar(HistoryStatusScenarioDTO dto);



}
