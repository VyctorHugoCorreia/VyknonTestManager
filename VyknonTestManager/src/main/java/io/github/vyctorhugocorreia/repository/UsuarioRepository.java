package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<UserEntity, String> {


    Optional<UserEntity> findByLogin(String login);

    @Query("SELECT u.nome FROM UsuarioEntity u WHERE u.login = :login")
    String findNomeByLogin(String login);



    @Query("""
            SELECT u FROM UsuarioEntity u WHERE
            (:nome IS NULL OR u.nome LIKE %:nome%)
            AND(:perfilDeAcesso IS NULL OR u.perfilDeAcesso.nome LIKE %:perfilDeAcesso%)
            AND(:login IS NULL OR u.login LIKE %:login%)
            
            """
    )
    List<UserEntity> searchUser(@Param("nome") String nome,
                                @Param("login") String login,
                                @Param("perfilDeAcesso") String perfilDeAcesso
                                   );


    boolean existsByNome(String nome);
    boolean existsByLoginAndStatus(String login, String status);
    boolean existsByLogin(String login);

    @Query("SELECT u.perfilDeAcesso.nome FROM UsuarioEntity u WHERE u.login = :login")
    String findPerfilDeAcessoByLogin(@Param("login") String login);


}
