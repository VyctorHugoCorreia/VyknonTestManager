package io.github.VyctorHugoCorreia.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SuiteDeTesteDTO {
    private Integer idTime;
    private Integer idProduto;
    private Integer idPlano;
    private String descSuite;
}
