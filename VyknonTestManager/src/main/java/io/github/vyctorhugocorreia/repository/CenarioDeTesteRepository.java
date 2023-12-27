package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.CenarioDeTesteEntity;
import io.github.vyctorhugocorreia.entity.SuiteDeTesteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CenarioDeTesteRepository extends JpaRepository<CenarioDeTesteEntity, Integer> {

    List<CenarioDeTesteEntity> findAll();

    List<CenarioDeTesteEntity> findByIdCenario(CenarioDeTesteEntity cenario);


    @Query("SELECT c FROM CenarioDeTesteEntity c WHERE " +
            "(:idCenario IS NULL OR c.idCenario = :idCenario) AND " +
            "(:tituloCenario IS NULL OR c.tituloCenario LIKE %:tituloCenario%) AND " +
            "(:idTime IS NULL OR c.idTime.idTime = :idTime) AND " +
            "(:idPlano IS NULL OR c.idPlano.idPlano = :idPlano) AND " +
            "(:idSuite IS NULL OR c.idSuite.idSuite = :idSuite) AND " +
            "(:idTproduto IS NULL OR c.idTproduto.idTproduto = :idTproduto) AND " +
            "(:idFuncionalidade IS NULL OR c.idFuncionalidade.idFuncionalidade = :idFuncionalidade) AND " +
            "(:idTpcenario IS NULL OR c.idTpcenario.idTpcenario = :idTpcenario) AND " +
            "(:idPlataforma IS NULL OR c.idPlataforma.idPlataforma = :idPlataforma) AND " +
            "(:idStatus IS NULL OR c.idStatus.idStatus = :idStatus) AND " +
            "(:idAutomatizado IS NULL OR c.idAutomatizado.idAutomatizado = :idAutomatizado) AND" +
            "(:tags IS NULL OR c.tags LIKE %:tags%) " )
    List<CenarioDeTesteEntity> searchCenario(
            @Param("idCenario") Long idCenario,
            @Param("tituloCenario") String tituloCenario,
            @Param("idTime") Long idTime,
            @Param("idPlano") Long idPlano,
            @Param("idSuite") Long idSuite,
            @Param("idTproduto") Long idTproduto,
            @Param("idFuncionalidade") Long idFuncionalidade,
            @Param("idTpcenario") Long idTpcenario,
            @Param("idPlataforma") Long idPlataforma,
            @Param("idStatus") Long idStatus,
            @Param("idAutomatizado") Long idAutomatizado,
            @Param("tags") String tags);


    @Query("SELECT COUNT(c) FROM CenarioDeTesteEntity c WHERE c.idSuite = :idSuite")
    int countCenariosBySuite(@Param("idSuite") SuiteDeTesteEntity suite);

}

