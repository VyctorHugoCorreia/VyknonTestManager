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
@Table(name = "scenariostatus")
public class ScenarioStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scenariostatus")
    private Long idScenarioStatus;

    @Column(name = "desc_scenariostatus")
    private String descScenarioStatus;

    @Transient
    private int scenarioQuantity;

}
