package io.github.vyctorhugocorreia.service;

import io.github.vyctorhugocorreia.entity.UsuarioEntity;

import java.util.List;

public interface UsuarioService {

    UsuarioEntity salvar(UsuarioEntity Usuario, List<String> PerfilDeAcesso);

   UsuarioEntity obterUsuarioComPerfilDeAcesso(String login);
}
