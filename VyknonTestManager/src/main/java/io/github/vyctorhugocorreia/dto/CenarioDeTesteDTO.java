package io.github.vyctorhugocorreia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CenarioDeTesteDTO {
    private Long idCenario;
    private String tituloCenario;
    private String descCenario;
    private String linkCenario;
    private Long idTime;
    private Long idPlano;
    private Long idSuite;
    private Long idTproduto;
    private Long idFuncionalidade;
    private Long idTpcenario;
    private Long idPlataforma;
    private Long idStatus;
    private Long idAutomatizado;
    private List<StepDTO> steps;
    private List<String> tags;
}
