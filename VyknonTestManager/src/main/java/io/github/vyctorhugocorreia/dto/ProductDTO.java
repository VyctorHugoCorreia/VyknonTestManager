package io.github.vyctorhugocorreia.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long idTeam;

    private Long idProduct;

    @NotEmpty(message = "Preencha o nome do produto.")
    private String descProduct;

    private String user;
}
