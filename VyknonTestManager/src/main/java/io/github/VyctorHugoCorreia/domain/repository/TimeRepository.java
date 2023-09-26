package io.github.VyctorHugoCorreia.domain.repository;

import io.github.VyctorHugoCorreia.domain.entity.TimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


public interface TimeRepository extends JpaRepository<TimeEntity, Integer> {

    Optional<TimeEntity> findByNomeTime(String nome);

    List<TimeEntity> findAll();

    Optional<TimeEntity> findByIdTimeAndNomeTime(Long idTime, String nomeTime);

    List<TimeEntity> findByNomeTimeContaining(String partialName);

    boolean existsByNomeTime(String nomeTime);

    @Query("SELECT t FROM TimeEntity t WHERE " +
            "(:idTime IS NULL OR t.idTime = :idTime) AND " +
            "(:nomeTime IS NULL OR t.nomeTime LIKE %:nomeTime%)")
    List<TimeEntity> searchTime(@Param("idTime") Long idTime,
                                @Param("nomeTime") String nomeTime);
}

