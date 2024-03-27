package io.github.vyctorhugocorreia.service;

import io.github.vyctorhugocorreia.dto.UserDTO;
import io.github.vyctorhugocorreia.entity.UserEntity;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserEntity save(UserDTO dto);
    UserDetails loadUserByUsername(String username);
    UserDetails authentication(UserEntity usuario);

    String delete(String id, @Pattern(regexp = "^(ACTIVE|INACTIVE)$") String status);
    UserEntity edit(String id, UserDTO dto);
    UserEntity passwordEdit(UserDTO dto);


}
