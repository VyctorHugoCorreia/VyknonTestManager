package io.github.vyctorhugocorreia.service;

import io.github.vyctorhugocorreia.dto.PerfilDeAcessoDTO;
import io.github.vyctorhugocorreia.dto.UsuarioDTO;
import io.github.vyctorhugocorreia.entity.PerfilDeAcessoEntity;
import io.github.vyctorhugocorreia.entity.UsuarioEntity;

public interface PerfilDeAcessoService {

    PerfilDeAcessoEntity salvar(PerfilDeAcessoDTO dto);

}
