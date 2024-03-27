package io.github.vyctorhugocorreia.controller;

import io.github.vyctorhugocorreia.dto.UserDTO;
import io.github.vyctorhugocorreia.entity.UserEntity;
import io.github.vyctorhugocorreia.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trocar-senha")
@RequiredArgsConstructor
@CrossOrigin
public class ChangePasswordController {


    private final UserService userService;

    @PutMapping
    public ResponseEntity<UserEntity> editar(@RequestBody @Valid UserDTO dto) {
        return ResponseEntity.ok(userService.passwordEdit(dto));
    }
}
