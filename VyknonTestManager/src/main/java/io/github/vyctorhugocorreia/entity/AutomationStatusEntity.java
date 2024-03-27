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
@Table(name = "automationstatus")
public class AutomationStatusEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_automationstatus")
    private Long idAutomationStatus;

    @Column(name = "desc_automationstatus")
    private String descAutomationStatus;

    @Transient
    private int scenarioQuantity;



}
