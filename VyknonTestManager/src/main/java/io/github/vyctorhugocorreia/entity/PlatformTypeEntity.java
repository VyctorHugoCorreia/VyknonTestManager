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
@Table(name = "plataformtype")
public class PlatformTypeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_platformtype")
    private Long idPlatformType;

    @Column(name = "desc_platformtype")
    private String descPlatformType;

    @Transient
    private int scenarioQuantity;

}
