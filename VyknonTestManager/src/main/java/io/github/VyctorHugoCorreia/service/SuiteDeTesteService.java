package io.github.VyctorHugoCorreia.service;

import io.github.VyctorHugoCorreia.domain.entity.SuiteDeTesteEntity;
import io.github.VyctorHugoCorreia.rest.dto.SuiteDeTesteDTO;

public interface SuiteDeTesteService {

    SuiteDeTesteEntity salvar(SuiteDeTesteDTO dto);
}
