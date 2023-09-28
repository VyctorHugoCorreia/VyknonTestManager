package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.TimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface TimeRepository extends JpaRepository<TimeEntity, Integer> {


    boolean existsByNomeTime(String nomeTime);

    @Query("SELECT t FROM TimeEntity t WHERE " +
            "(:idTime IS NULL OR t.idTime = :idTime) AND " +
            "(:nomeTime IS NULL OR t.nomeTime LIKE %:nomeTime%)")
    List<TimeEntity> searchTime(@Param("idTime") Long idTime,
                                @Param("nomeTime") String nomeTime);
}

