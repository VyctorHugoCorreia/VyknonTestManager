package io.github.vyctorhugocorreia.dto;

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
    private List<String> perfilDeAcesso;
}
