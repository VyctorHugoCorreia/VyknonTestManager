package io.github.VyctorHugoCorreia.service;

import io.github.VyctorHugoCorreia.domain.entity.PlanoDeTesteEntity;
import io.github.VyctorHugoCorreia.rest.dto.PlanoDeTesteDTO;


public interface PlanoDeTesteService {

    PlanoDeTesteEntity salvar(PlanoDeTesteDTO dto);

}
