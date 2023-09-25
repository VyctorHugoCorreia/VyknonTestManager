package io.github.VyctorHugoCorreia.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "csuitetestes")

public class SuiteDeTesteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_suite")
    private Integer idSuite;

    @ManyToOne
    @JoinColumn(name = "id_time")
    private TimeEntity idTime;

    @ManyToOne
    @JoinColumn(name = "id_tproduto")
    private ProdutoEntity idProduto;

    @ManyToOne
    @JoinColumn(name = "id_plano")
    private PlanoDeTesteEntity idPlano;

    @Column(name = "desc_suite")
    private String descSuite;
}
