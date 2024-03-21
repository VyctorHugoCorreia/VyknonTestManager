package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.CadastroUsuarioDTO;
import io.github.vyctorhugocorreia.dto.UsuarioDTO;
import io.github.vyctorhugocorreia.entity.PerfilDeAcessoEntity;
import io.github.vyctorhugocorreia.entity.UsuarioEntity;
import io.github.vyctorhugocorreia.entity.UsuarioPerfilDeAcessoEntity;
import io.github.vyctorhugocorreia.repository.UsuarioPerfilDeAcessoRepository;
import io.github.vyctorhugocorreia.repository.UsuarioRepository;
import io.github.vyctorhugocorreia.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private final UsuarioPerfilDeAcessoRepository usuarioPerfilDeAcessoRepository;


    @PostMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<UsuarioEntity> salvar(@RequestBody CadastroUsuarioDTO body){
        UsuarioEntity usuarioSalvo = usuarioService.salvar(body.getUsuario(), body.getPerfilDeAcesso());
        return ResponseEntity.ok(usuarioSalvo);
    }

  //  @GetMapping
  //  @PreAuthorize("hasRole('Administrador')")
  //  public ResponseEntity<List<UsuarioEntity>> listarUsuarios(
  //          @RequestParam(required = false) String nome,
  //          @RequestParam(required = false) String login) {
  //      List<UsuarioEntity> usuarios = usuarioRepository.searchUser(nome,login);
  //      return ResponseEntity.ok(usuarios);
  //  }

    @GetMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String login) {

        List<UsuarioEntity> usuarios = usuarioRepository.searchUser(nome, login);
        List<UsuarioDTO> usuariosDTO = usuarios.stream()
                .map(usuario -> {
                    List<UsuarioPerfilDeAcessoEntity> usuarioPerfis = usuarioPerfilDeAcessoRepository.findByUsuario(usuario);
                    List<String> perfisNomes = usuarioPerfis.stream()
                            .map(UsuarioPerfilDeAcessoEntity::getPerfilDeAcesso)
                            .map(PerfilDeAcessoEntity::getNome)
                            .collect(Collectors.toList());
                    return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getLogin(), perfisNomes);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(usuariosDTO);
    }
}
