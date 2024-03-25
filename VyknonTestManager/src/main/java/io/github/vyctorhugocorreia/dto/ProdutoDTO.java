package io.github.vyctorhugocorreia.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    private Long idTime;

    private Long idTproduto;

    @NotEmpty(message = "Preencha o nome do produto.")
    private String descProduto;

    private String usuario;
}
