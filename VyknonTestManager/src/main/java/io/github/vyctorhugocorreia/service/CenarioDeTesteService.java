package io.github.vyctorhugocorreia.service;

import io.github.vyctorhugocorreia.dto.CenarioDeTesteDTO;
import io.github.vyctorhugocorreia.dto.PlanoDeTestesDTO;
import io.github.vyctorhugocorreia.entity.CenarioDeTesteEntity;
import io.github.vyctorhugocorreia.entity.PlanoDeTesteEntity;

public interface CenarioDeTesteService {

    CenarioDeTesteEntity salvar(CenarioDeTesteDTO dto);

  //  CenarioDeTesteEntity editar(Long id, CenarioDeTesteDTO dto);

  String deletar(Long id);


}
