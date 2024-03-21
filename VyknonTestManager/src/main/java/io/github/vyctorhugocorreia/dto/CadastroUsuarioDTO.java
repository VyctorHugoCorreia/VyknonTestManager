package io.github.vyctorhugocorreia.dto;

import io.github.vyctorhugocorreia.entity.UsuarioEntity;
import lombok.Data;

import java.util.List;

@Data
public class CadastroUsuarioDTO {

    private UsuarioEntity usuario;
    private List<String> perfilDeAcesso;
}
