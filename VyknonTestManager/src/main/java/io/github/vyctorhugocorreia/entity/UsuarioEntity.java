package io.github.vyctorhugocorreia.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;


    @Column(name = "nome")
    @NotBlank(message = "Preencha um nome válido")
    private String nome;

    @Column(name = "login")
    @NotBlank(message = "Preencha um login válido")
    private String login;

    @Column(name = "senha")
    @NotBlank(message = "Preencha uma senha válido")
    private String senha;

    @Transient
    private List<String> perfilDeAcesso;


}
