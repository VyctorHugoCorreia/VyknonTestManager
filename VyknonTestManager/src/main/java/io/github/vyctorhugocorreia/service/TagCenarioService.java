package io.github.vyctorhugocorreia.service;

import io.github.vyctorhugocorreia.dto.TagCenarioDTO;
import io.github.vyctorhugocorreia.entity.TagCenarioEntity;

public interface TagCenarioService {

    TagCenarioEntity salvar(TagCenarioDTO dto);

     TagCenarioEntity editar(Long id, TagCenarioDTO dto);

     String deletar(Long id);

}
