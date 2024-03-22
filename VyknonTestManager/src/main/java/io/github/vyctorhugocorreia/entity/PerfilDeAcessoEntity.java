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
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "perfildeacesso")
public class PerfilDeAcessoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "nome")
    @NotBlank(message = "Preencha um nome válido")
    private String nome;

    public PerfilDeAcessoEntity(String nome) {
        this.nome = nome;
    }

}
