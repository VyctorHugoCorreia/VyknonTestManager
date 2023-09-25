package io.github.VyctorHugoCorreia.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cplanotestes")

public class PlanoDeTesteEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plano")
    private Integer idPlano;

    @ManyToOne
    @JoinColumn(name = "id_time")
    private TimeEntity idTime;

    @ManyToOne
    @JoinColumn(name = "id_tproduto")
    private ProdutoEntity idProduto;

    @Column(name = "desc_plano")
    private String descPlano;

}
