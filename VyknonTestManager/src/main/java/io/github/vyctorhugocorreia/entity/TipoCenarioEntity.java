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
@Table(name = "Tpcenario")
public class TipoCenarioEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tpcenario")
    private Long idTpcenario;

    @Column(name = "desc_tpcenario")
    private String descTpcenario;

    @Transient
    private int quantidadeCenarios;



}
