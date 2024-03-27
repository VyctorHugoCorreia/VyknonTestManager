package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {


    boolean existsByNameTeam(String nomeTime);

    @Query("SELECT t FROM TeamEntity t WHERE " +
            "(:idTeam IS NULL OR t.idTeam = :idTeam) AND " +
            "(:nameTeam IS NULL OR t.nameTeam LIKE %:nameTeam%)")
    List<TeamEntity> searchTeam(@Param("idTeam") Long idTeam,
                                @Param("nomeTeam") String nameTeam);
}

