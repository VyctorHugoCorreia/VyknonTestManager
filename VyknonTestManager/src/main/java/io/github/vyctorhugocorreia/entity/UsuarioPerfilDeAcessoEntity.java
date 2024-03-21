package io.github.vyctorhugocorreia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Data
@Table(name = "usuarioperfildeacesso")
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPerfilDeAcessoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;


    @ManyToOne
    @JoinColumn(name = "id_perfil")
    private PerfilDeAcessoEntity perfilDeAcesso;

    public UsuarioPerfilDeAcessoEntity(UsuarioEntity usuario, PerfilDeAcessoEntity perfilDeAcesso) {
        this.usuario = usuario;
        this.perfilDeAcesso = perfilDeAcesso;
    }
}
