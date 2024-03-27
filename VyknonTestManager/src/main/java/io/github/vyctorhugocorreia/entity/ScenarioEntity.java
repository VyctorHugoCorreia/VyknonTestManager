package io.github.vyctorhugocorreia.entity;

import io.github.vyctorhugocorreia.ListConverter;
import io.github.vyctorhugocorreia.dto.StepDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "scenario")


public class ScenarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scenario")
    private Long idScenario;

    @Column(name = "title_scenario")
    private String titleScenario;

    @Column(name = "desc_scenario")
    private String descScenario;

    @Column(name = "link_scenario")
    private String linkScenario;

    @ManyToOne
    @JoinColumn(name = "id_team")
    private TeamEntity idTeam;

    @ManyToOne
    @JoinColumn(name = "id_testPlan")
    private TestPlanEntity idTestPlan;

    @ManyToOne
    @JoinColumn(name = "id_testSuite")
    private TestSuiteEntity idTestSuite;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private ProductEntity idProduct;


    @ManyToOne
    @JoinColumn(name = "id_scenarioType")
    private ScenarioTypeEntity idScenarioType;

    @ManyToOne
    @JoinColumn(name = "id_platformType")
    private PlatformTypeEntity idPlatformType;

    @ManyToOne
    @JoinColumn(name = "id_scenariostatus")
    private ScenarioStatusEntity idScenarioStatus;

    @ManyToOne
    @JoinColumn(name = "id_automationstatus")
    private AutomationStatusEntity idAutomationStatus;


    @Column(name = "steps")
    @Convert(converter = ListConverter.class)
    private List<StepDTO> steps;

    @Column(name = "tags")
    @Convert(converter = ListConverter.class)
    private List<String> tags;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @Column(name = "date_update")
    private LocalDateTime dateUpdate;

    @JoinColumn(name = "id_user")
    @ManyToOne
    private UserEntity user;
}
