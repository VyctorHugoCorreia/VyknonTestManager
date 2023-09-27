package io.github.VyctorHugoCorreia.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ctimeproduto")

public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tproduto")
    private Long idTproduto;

    @Column(name = "desc_produto")
    private String descProduto;

    @ManyToOne
    @JoinColumn(name = "id_Time")
    private TimeEntity idTime;


}
