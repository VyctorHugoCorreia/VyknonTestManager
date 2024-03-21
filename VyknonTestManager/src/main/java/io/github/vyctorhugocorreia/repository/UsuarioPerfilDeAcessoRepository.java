package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.UsuarioEntity;
import io.github.vyctorhugocorreia.entity.UsuarioPerfilDeAcessoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioPerfilDeAcessoRepository extends JpaRepository<UsuarioPerfilDeAcessoEntity, String> {

    @Query("""
            select distinct p.nome
            from UsuarioPerfilDeAcessoEntity up
            join up.perfilDeAcesso p
            join up.usuario u
            where u = ?1
            
    """)
    List<String> findPerfilDeAcessoByUsuario(UsuarioEntity usuario);

    @Query("SELECT upa FROM UsuarioPerfilDeAcessoEntity upa WHERE upa.usuario = :usuario")
    List<UsuarioPerfilDeAcessoEntity> findByUsuario(UsuarioEntity usuario);

}
