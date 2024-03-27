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
@Table(name = "scenariotype")
public class ScenarioTypeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scenarioType")
    private Long idScenarioType;

    @Column(name = "desc_scenarioType")
    private String descScenarioType;

    @Transient
    private int scenarioQuantity;



}
