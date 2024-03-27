package io.github.vyctorhugocorreia.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDTO {

    private String login;
    private String password;
}
