package io.github.vyctorhugocorreia.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestPlanDTO {

    private Long idTestPlan;

    @NotEmpty(message = "Preencha o nome do plano de testes.")
    private String descTestPlan;

    private Long idTeam;


    private Long idProduct;

    private String user;
}
