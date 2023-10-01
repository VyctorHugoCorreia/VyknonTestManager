package io.github.vyctorhugocorreia.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanoDeTestesDTO {

    private Long idTime;

    private Long idTproduto;

    private Long idPlano;

    @NotEmpty(message = "Preencha o nome do plano de testes.")
    private String descPlano;
}
