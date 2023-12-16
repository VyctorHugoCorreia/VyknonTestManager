package io.github.vyctorhugocorreia.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuiteDeTesteDTO {

    private Long idSuite;

    private Long idTproduto;

    private Long idTime;

    private Long idPlano;

    @NotEmpty(message = "Preencha o nome da suite de testes.")
    private String descSuite;
}
