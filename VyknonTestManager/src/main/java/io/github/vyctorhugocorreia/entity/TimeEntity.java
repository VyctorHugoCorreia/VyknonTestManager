package io.github.vyctorhugocorreia.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Ttime")
public class TimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_time")
    private Long idTime;

    @Column(name = "nome_time")
    @NotBlank(message = "Preencha um nome válido")
    private String nomeTime;

    @JoinColumn(name = "id_usuario")
    @ManyToOne
    private UsuarioEntity usuario;

    @Transient
    private int quantidadeCenarios;

}
