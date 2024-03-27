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
@Table(name = "product")

public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long idProduct;

    @Column(name = "desc_product")
    private String descProduct;

    @ManyToOne
    @JoinColumn(name = "id_team")
    private TeamEntity idTeam;

    @JoinColumn(name = "id_user")
    @ManyToOne
    private UserEntity user;

    @Transient
    private int scenarioQuantity;
}
