package io.github.VyctorHugoCorreia.service;

import io.github.VyctorHugoCorreia.domain.entity.TimeEntity;
import io.github.VyctorHugoCorreia.rest.dto.TimeDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TimeService {

    TimeEntity salvar(TimeDTO dto);

    List<TimeEntity> searchTime(Long id, String nome);

}
