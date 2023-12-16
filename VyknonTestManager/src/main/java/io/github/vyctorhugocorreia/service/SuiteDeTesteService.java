package io.github.vyctorhugocorreia.service;

import io.github.vyctorhugocorreia.dto.PlanoDeTestesDTO;
import io.github.vyctorhugocorreia.dto.SuiteDeTesteDTO;
import io.github.vyctorhugocorreia.entity.PlanoDeTesteEntity;
import io.github.vyctorhugocorreia.entity.SuiteDeTesteEntity;

public interface SuiteDeTesteService {

    SuiteDeTesteEntity salvar(SuiteDeTesteDTO dto);

    SuiteDeTesteEntity editar(Long id, SuiteDeTesteDTO dto);

    String deletar(Long id);


}
