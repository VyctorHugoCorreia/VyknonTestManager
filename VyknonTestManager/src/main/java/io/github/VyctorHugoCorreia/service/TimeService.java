package io.github.VyctorHugoCorreia.service;

import io.github.VyctorHugoCorreia.domain.entity.TimeEntity;
import io.github.VyctorHugoCorreia.rest.dto.TimeDTO;

public interface TimeService {

    TimeEntity salvar(TimeDTO dto);

    TimeEntity editar(Long id, TimeDTO dto);

    String deletar(Long id);

}
