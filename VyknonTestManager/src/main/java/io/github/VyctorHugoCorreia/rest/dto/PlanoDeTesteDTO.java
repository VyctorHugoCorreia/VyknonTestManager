package io.github.VyctorHugoCorreia.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PlanoDeTesteDTO {
    private Integer idTime;
    private Integer idProduto;
    private String descPlano;
}
