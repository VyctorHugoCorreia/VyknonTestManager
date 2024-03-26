package io.github.vyctorhugocorreia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cplanotestes")

public class PlanoDeTesteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plano")
    private Long idPlano;

    @Column(name = "desc_plano")
    private String descPlano;

    @ManyToOne
    @JoinColumn(name = "id_Time")
    private TimeEntity idTime;

    @ManyToOne
    @JoinColumn(name = "id_tproduto")
    private ProdutoEntity idTproduto;

    @JoinColumn(name = "id_usuario")
    @ManyToOne
    private UsuarioEntity usuario;

    @Transient
    private int quantidadeSuites;

    @Transient
    private int quantidadeCenarios;
}
