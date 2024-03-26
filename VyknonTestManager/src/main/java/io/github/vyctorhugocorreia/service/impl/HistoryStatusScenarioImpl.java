package io.github.vyctorhugocorreia.service.impl;


import io.github.vyctorhugocorreia.dto.HistoryStatusScenarioDTO;
import io.github.vyctorhugocorreia.entity.*;
import io.github.vyctorhugocorreia.exception.RegraNegocioException;
import io.github.vyctorhugocorreia.repository.*;
import io.github.vyctorhugocorreia.service.HistoriyStatusScenarioService;
import io.github.vyctorhugocorreia.util.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class HistoryStatusScenarioImpl implements HistoriyStatusScenarioService {

    private final HistoryStatusScenarioRepository historyStatusScenarioRepository;
    private final CenarioDeTesteRepository cenarioDeTesteRepository;
    private final StatusCenarioRepository statusCenarioRepository;

    private final TimeRepository timeRepository;
private final UserInfo userInfo;
    @Override
    @Transactional
    public HistoryStatusScenarioEntity salvar(HistoryStatusScenarioDTO dto) {

        int idCenario = dto.getIdCenario().intValue();
        int idStatusBefore = dto.getStatusBefore().intValue();
        int idStatusAfter = dto.getStatusAfter().intValue();

        CenarioDeTesteEntity cenario = cenarioDeTesteRepository.findById(idCenario)
                .orElseThrow(() -> new RegraNegocioException("Cenário não encontrado com ID: " + dto.getIdCenario()));

        StatusCenarioEntity statusBefore = statusCenarioRepository.findById(idStatusBefore)
                .orElseThrow(() -> new RegraNegocioException("Status Before não encontrado com ID: " + idStatusBefore));

        StatusCenarioEntity statusAfter = statusCenarioRepository
                .findById(idStatusAfter)
                .orElseThrow(() -> new RegraNegocioException("Status do cenário não encontrada"));

        HistoryStatusScenarioEntity historyStatusScenario = HistoryStatusScenarioEntity.builder()
                .idCenario(cenario)
                .statusBefore(statusBefore)
                .statusAfter(statusAfter)
                .dateUpdate(LocalDateTime.now())
                .usuario(userInfo.usuario())
                .build();


        return historyStatusScenarioRepository.save(historyStatusScenario);
    }

}
