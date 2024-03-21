package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.TimeEntity;
import io.github.vyctorhugocorreia.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<UsuarioEntity, String> {

    Optional<UsuarioEntity> findByLogin(String login);


    @Query("""
            SELECT u FROM UsuarioEntity u WHERE
            (:nome IS NULL OR u.nome LIKE %:nome%)
            AND(:login IS NULL OR u.login LIKE %:login%)
            
            """
    )
    List<UsuarioEntity> searchUser(@Param("nome") String nome,
                                   @Param("login") String login);

}
