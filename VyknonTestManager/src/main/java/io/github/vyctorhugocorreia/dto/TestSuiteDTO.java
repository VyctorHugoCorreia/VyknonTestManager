package io.github.vyctorhugocorreia.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestSuiteDTO {

    private Long idTestSuite;

    private Long idProduct;

    private Long idTeam;

    private Long idTestPlan;

    @NotEmpty(message = "Preencha o nome da suite de testes.")
    private String descTestSuite;

    private String user;
}
