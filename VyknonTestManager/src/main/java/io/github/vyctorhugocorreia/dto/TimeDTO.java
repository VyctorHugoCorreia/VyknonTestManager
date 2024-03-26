package io.github.vyctorhugocorreia.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeDTO {

    private String idTime;

    @NotEmpty(message = "Preencha o nome do time.")
    private String nomeTime;

    private String usuario;
}
