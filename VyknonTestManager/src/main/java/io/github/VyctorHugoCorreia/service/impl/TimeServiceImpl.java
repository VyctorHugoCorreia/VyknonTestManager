package io.github.VyctorHugoCorreia.service.impl;


import io.github.VyctorHugoCorreia.domain.entity.TimeEntity;
import io.github.VyctorHugoCorreia.domain.repository.TimeRepository;
import io.github.VyctorHugoCorreia.exception.RegraNegocioException;
import io.github.VyctorHugoCorreia.exception.TimeNaoEncontradoException;
import io.github.VyctorHugoCorreia.rest.dto.TimeDTO;
import io.github.VyctorHugoCorreia.service.TimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimeServiceImpl implements TimeService {


    private final TimeRepository timeRepository;


    @Override
    @Transactional
    public TimeEntity salvar(TimeDTO dto) {
        TimeEntity time = new TimeEntity();
        String nomeTime = dto.getNomeTime();
        validarSeTimeJaEstaCadastrado(nomeTime);
        time.setNomeTime(nomeTime);

        return timeRepository.save(time);
    }

    private void validarSeTimeJaEstaCadastrado(String nomeTime) {
        if (timeRepository.existsByNomeTime(nomeTime)) {
            throw new RegraNegocioException("Já existe um time com o mesmo nome.");
        }
    }


    @Override
    @Transactional
    public List<TimeEntity> searchTime(Long id, String nome) {
       List<TimeEntity> times = timeRepository.searchTime(id, nome);
        if (times.isEmpty()) {
            throw new TimeNaoEncontradoException();
        }
        return times;
    }





}
