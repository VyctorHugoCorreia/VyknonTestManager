package io.github.vyctorhugocorreia.dto;

import io.github.vyctorhugocorreia.entity.PerfilDeAcessoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private String id;
    private String nome;
    private String login;
    private String senha;
    private String senhaAntiga;
    private String status;
    private PerfilDeAcessoEntity perfilDeAcesso;
}
