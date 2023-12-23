package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.CenarioDeTesteEntity;
import io.github.vyctorhugocorreia.entity.TimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CenarioDeTesteRepository extends JpaRepository<CenarioDeTesteEntity, Integer> {

    List<CenarioDeTesteEntity> findAll();

    List<CenarioDeTesteEntity> findByIdCenario(CenarioDeTesteEntity cenario);


    @Query("SELECT c FROM CenarioDeTesteEntity c WHERE " +
            "(:idCenario IS NULL OR c.idCenario = :idCenario) AND " +
            "(:tituloCenario IS NULL OR c.tituloCenario LIKE %:tituloCenario%)")
    List<CenarioDeTesteEntity> searchCenario(@Param("idCenario") String idCenario,
                                @Param("tituloCenario") String descCenario);

}

