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

    @JoinColumn(name = "usuario")
    @ManyToOne
    private UsuarioEntity usuario;

    @Transient
    private int quantidadeCenarios;
}
