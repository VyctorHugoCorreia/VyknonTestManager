package io.github.VyctorHugoCorreia.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ctimeproduto")
public class ProdutoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tproduto")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_time")
    private TimeEntity idTime;

    @Column(name = "desc_produto")
    private String descProduto;


}
