package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.UserDTO;
import io.github.vyctorhugocorreia.entity.UserEntity;
import io.github.vyctorhugocorreia.repository.UsuarioRepository;
import io.github.vyctorhugocorreia.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {


    private final UserService userService;
private final UsuarioRepository repository;

    @PostMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<UserEntity> save(@RequestBody @Valid UserDTO dto){
        UserEntity user = userService.save(dto);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<UserEntity>> getUser(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String login,
            @RequestParam(required = false) String accessProfile
    ) {

        List<UserEntity> users = repository.searchUser(name, login,accessProfile);


        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable String id, @RequestParam @Pattern(regexp = "^(ACTIVE|INACTIVE)$") String status) {
        return userService.delete(id, status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> edit(@PathVariable String id, @RequestBody @Valid UserDTO dto) {
        return ResponseEntity.ok(userService.edit(id, dto));
    }


}
