package io.github.vyctorhugocorreia.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {

    private String idTeam;

    @NotEmpty(message = "Preencha o nome do time.")
    private String name;

    private String user;
}
