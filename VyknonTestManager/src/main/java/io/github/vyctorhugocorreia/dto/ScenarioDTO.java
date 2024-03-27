package io.github.vyctorhugocorreia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScenarioDTO {
    private Long idScenario;
    private String titleScenario;
    private String descScenario;
    private String linkScenario;
    private Long idTeam;
    private Long idTestPlan;
    private Long idTestSuite;
    private Long idProduct;
    private Long idScenarioType;
    private Long idPlatformType;
    private Long idScenarioStatus;
    private Long idAutomationStatus;
    private List<StepDTO> steps;
    private List<String> tags;
    private LocalDateTime dateCreation;
    private LocalDateTime dateUpdate;
    private String user;
}
