package io.github.vyctorhugocorreia.security;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class IdentificacaoUsuario {
    private String id;
    private String nome;
    private String login;
    private List<String> perfilDeAcesso;

    public IdentificacaoUsuario(String id, String nome, String login, List<String> perfilDeAcesso) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.perfilDeAcesso = perfilDeAcesso;
    }

    public List<String> getPerfilDeAcesso() {
        if(perfilDeAcesso == null){
            perfilDeAcesso = new ArrayList<>();
        }
        return perfilDeAcesso;
    }
}
