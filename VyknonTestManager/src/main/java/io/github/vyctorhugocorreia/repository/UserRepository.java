package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {


    Optional<UserEntity> findByLogin(String login);

    @Query("SELECT u.name FROM UserEntity u WHERE u.login = :login")
    String findNameByLogin(String login);



    @Query("""
            SELECT u FROM UserEntity u WHERE
            (:name IS NULL OR u.nome LIKE %:name%)
            AND(:accessProfile IS NULL OR u.accessProfile.name LIKE %:accessProfile%)
            AND(:login IS NULL OR u.login LIKE %:login%)
            
            """
    )
    List<UserEntity> searchUser(@Param("name") String name,
                                @Param("login") String login,
                                @Param("accessProfile") String accessProfile
                                   );


    boolean existsByName(String nome);
    boolean existsByLoginAndStatus(String login, String status);
    boolean existsByLogin(String login);

    @Query("SELECT u.accessProfile.nome FROM UserEntity u WHERE u.login = :login")
    String findAccessProfileByLogin(@Param("login") String login);


}
