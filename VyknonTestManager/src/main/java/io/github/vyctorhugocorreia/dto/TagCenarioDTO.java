package io.github.vyctorhugocorreia.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagCenarioDTO {

    private long idTag;

    private long idCenario;

    private String descTag;
}
