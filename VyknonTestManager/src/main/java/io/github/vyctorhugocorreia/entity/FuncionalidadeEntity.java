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
@Table(name = "cfuncionalidade")

public class FuncionalidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionalidade")
    private Long idFuncionalidade;

    @Column(name = "desc_funcionalidade")
    private String descFuncionalidade;

    @ManyToOne
    @JoinColumn(name = "id_Time")
    private TimeEntity idTime;

    @ManyToOne
    @JoinColumn(name = "id_tproduto")
    private ProdutoEntity idTproduto;

    @Transient
    private int quantidadeCenarios;
}
