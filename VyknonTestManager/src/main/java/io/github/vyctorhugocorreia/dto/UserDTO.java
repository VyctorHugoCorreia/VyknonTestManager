package io.github.vyctorhugocorreia.dto;

import io.github.vyctorhugocorreia.entity.AccessProfileEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String id;
    private String name;
    private String login;
    private String password;
    private String oldPassword;
    private String status;
    private AccessProfileEntity accessProfile;
}
