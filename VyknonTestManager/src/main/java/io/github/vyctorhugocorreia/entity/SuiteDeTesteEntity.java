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
@Table(name = "csuitetestes")

public class SuiteDeTesteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_suite")
    private Long idSuite;

    @Column(name = "desc_suite")
    private String descSuite;

    @ManyToOne
    @JoinColumn(name = "id_time")
    private TimeEntity idTime;

    @ManyToOne
    @JoinColumn(name = "id_tproduto")
    private ProdutoEntity idTproduto;

    @ManyToOne
    @JoinColumn(name = "id_plano")
    private PlanoDeTesteEntity idPlano;


}
