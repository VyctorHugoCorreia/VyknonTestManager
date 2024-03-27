package io.github.vyctorhugocorreia.service.impl;


import io.github.vyctorhugocorreia.dto.HistoryStatusScenarioDTO;
import io.github.vyctorhugocorreia.entity.*;
import io.github.vyctorhugocorreia.exception.RegraNegocioException;
import io.github.vyctorhugocorreia.repository.*;
import io.github.vyctorhugocorreia.service.HistoryStatusScenarioService;
import io.github.vyctorhugocorreia.util.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class HistoryStatusScenarioImpl implements HistoryStatusScenarioService {

    private final HistoryStatusScenarioRepository historyStatusScenarioRepository;
    private final CenarioDeTesteRepository scenarioDeTesteRepository;
    private final StatusCenarioRepository scenarioStatusRepository;

    private final TimeRepository timeRepository;
private final UserInfo userInfo;
    @Override
    @Transactional
    public HistoryStatusScenarioEntity save(HistoryStatusScenarioDTO dto) {

        int idScenario = dto.getIdScenario().intValue();
        int idStatusBefore = dto.getStatusBefore().intValue();
        int idStatusAfter = dto.getStatusAfter().intValue();

        ScenarioEntity scenario = scenarioDeTesteRepository.findById(idScenario)
                .orElseThrow(() -> new RegraNegocioException("Cenário não encontrado com ID: " + dto.getIdScenario()));

        ScenarioStatusEntity statusBefore = scenarioStatusRepository.findById(idStatusBefore)
                .orElseThrow(() -> new RegraNegocioException("Status Before não encontrado com ID: " + idStatusBefore));

        ScenarioStatusEntity statusAfter = scenarioStatusRepository
                .findById(idStatusAfter)
                .orElseThrow(() -> new RegraNegocioException("Status do cenário não encontrada"));

        HistoryStatusScenarioEntity historyStatusScenario = HistoryStatusScenarioEntity.builder()
                .idScenario(scenario)
                .statusBefore(statusBefore)
                .statusAfter(statusAfter)
                .dateUpdate(LocalDateTime.now())
                .user(userInfo.user())
                .build();


        return historyStatusScenarioRepository.save(historyStatusScenario);
    }

}
