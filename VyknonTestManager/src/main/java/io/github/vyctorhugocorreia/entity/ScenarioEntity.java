package io.github.vyctorhugocorreia.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private TeamEntity idTeam;

    @ManyToOne
    @JoinColumn(name = "id_testplan")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private TestPlanEntity idTestPlan;

    @ManyToOne
    @JoinColumn(name = "id_testsuite")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private TestSuiteEntity idTestSuite;

    @ManyToOne
    @JoinColumn(name = "id_product")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ProductEntity idProduct;


    @ManyToOne
    @JoinColumn(name = "id_scenariotype")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ScenarioTypeEntity idScenarioType;

    @ManyToOne
    @JoinColumn(name = "id_platformtype")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private PlatformTypeEntity idPlatformType;

    @ManyToOne
    @JoinColumn(name = "id_scenariostatus")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ScenarioStatusEntity idScenarioStatus;

    @ManyToOne
    @JoinColumn(name = "id_automationstatus")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserEntity user;

    public String getNameTeam() {
        if (idTeam != null) {
            return idTeam.getNameTeam();
        } else {
            return null;
        }
    }

    public String getDescProduct() {
        if (idProduct != null) {
            return idProduct.getDescProduct();
        } else {
            return null;
        }
    }

    public String getDescTesPlan() {
        if (idTestPlan != null) {
            return idTestPlan.getDescTestPlan();
        } else {
            return null;
        }
    }

    public String getDescTesSuite() {
        if (idTestSuite != null) {
            return idTestSuite.getDescTestSuite();
        } else {
            return null;
        }
    }

    public String getDescScenarioType() {
        if (idScenarioType != null) {
            return idScenarioType.getDescScenarioType();
        } else {
            return null;
        }
    }

    public String getDescPlatformType() {
        if (idPlatformType != null) {
            return idPlatformType.getDescPlatformType();
        } else {
            return null;
        }
    }

    public String getDescScenarioStatus() {
        if (idScenarioStatus != null) {
            return idScenarioStatus.getDescScenarioStatus();
        } else {
            return null;
        }
    }

    public String getDescAutomationStatus() {
        if (idAutomationStatus != null) {
            return idAutomationStatus.getDescAutomationStatus();
        } else {
            return null;
        }
    }
}
