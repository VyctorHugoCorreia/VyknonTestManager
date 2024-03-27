package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CenarioDeTesteRepository extends JpaRepository<ScenarioEntity, Integer> {

    List<ScenarioEntity> findAll();

    List<ScenarioEntity> findByIdCenario(ScenarioEntity cenario);

    @Query("SELECT c FROM CenarioDeTesteEntity c WHERE " +
            "(:idCenario IS NULL OR c.idCenario = :idCenario) AND " +
            "(:tituloCenario IS NULL OR c.tituloCenario LIKE %:tituloCenario%) AND " +
            "(:idTime IS NULL OR c.idTime.idTime = :idTime) AND " +
            "(:idPlano IS NULL OR c.idPlano.idPlano = :idPlano) AND " +
            "(:idSuite IS NULL OR c.idSuite.idSuite = :idSuite) AND " +
            "(:idTproduto IS NULL OR c.idTproduto.idTproduto = :idTproduto) AND " +
            "(:idTpcenario IS NULL OR c.idTpcenario.idTpcenario = :idTpcenario) AND " +
            "(:idPlataforma IS NULL OR c.idPlataforma.idPlataforma = :idPlataforma) AND " +
            "(:idStatus IS NULL OR c.idStatus.idStatus = :idStatus) AND " +
            "(:idAutomatizado IS NULL OR c.idAutomatizado.idAutomatizado = :idAutomatizado) AND" +
            "(:tags IS NULL OR c.tags LIKE %:tags%) " )
    List<ScenarioEntity> searchCenario(
            @Param("idCenario") Long idCenario,
            @Param("tituloCenario") String tituloCenario,
            @Param("idTime") Long idTime,
            @Param("idPlano") Long idPlano,
            @Param("idSuite") Long idSuite,
            @Param("idTproduto") Long idTproduto,
            @Param("idTpcenario") Long idTpcenario,
            @Param("idPlataforma") Long idPlataforma,
            @Param("idStatus") Long idStatus,
            @Param("idAutomatizado") Long idAutomatizado,
            @Param("tags") String tags);

    @Query("SELECT COUNT(s) FROM CenarioDeTesteEntity s WHERE s.idTproduto = :idTproduto")
    int countScenariosByProduto(@Param("idTproduto") ProductEntity idTproduto);

    @Query("SELECT COUNT(s) FROM CenarioDeTesteEntity s WHERE s.idPlano = :idPlano")
    int countScenariosByTestPlan(@Param("idPlano") TestPlanEntity idPlano);

    @Query("SELECT COUNT(s) FROM CenarioDeTesteEntity s WHERE s.idTime = :idTime")
    int countScenariosByTime(@Param("idTime") TeamEntity idTime);

    @Query("SELECT COUNT(c) FROM CenarioDeTesteEntity c WHERE c.idSuite = :idSuite")
    int countCenariosBySuite(@Param("idSuite") testSuiteEntity idSuite);


    @Query("SELECT COUNT(c) FROM CenarioDeTesteEntity c WHERE c.idTpcenario = :idTpcenario " +
            "AND (:idTime IS NULL OR c.idTime = :idTime)")
    int countCenariosByScenarioType(@Param("idTpcenario") ScenarioTypeEntity idTpcenario,
                                            @Param("idTime") TeamEntity idTime);

    @Query("SELECT COUNT(c) FROM CenarioDeTesteEntity c WHERE c.idStatus = :idStatus " +
            "AND (:idTime IS NULL OR c.idTime = :idTime)")
    int countCenariosByScenarioStatus(@Param("idStatus") ScenarioStatusEntity idStatus,
                                      @Param("idTime") TeamEntity idTime);

    @Query("SELECT COUNT(c) FROM CenarioDeTesteEntity c WHERE c.idAutomatizado = :idAutomatizado "+
            "AND (:idTime IS NULL OR c.idTime = :idTime)")
    int countCenariosByAutomationStatus(@Param("idAutomatizado") AutomationStatusEntity idAutomatizado,
                                        @Param("idTime") TeamEntity idTime);

    @Query("SELECT COUNT(c) FROM CenarioDeTesteEntity c WHERE c.idPlataforma = :idPlataforma " +
            "AND (:idTime IS NULL OR c.idTime = :idTime)")
    int countCenariosByPlataformType(@Param("idPlataforma") PlatformTypeEntity idPlataforma,
                                     @Param("idTime") TeamEntity idTime);

    @Query("SELECT COUNT(c) FROM CenarioDeTesteEntity c " +
            "WHERE (:idPlano IS NULL OR c.idPlano = :idPlano) " +
            "AND (:idSuite IS NULL OR c.idSuite = :idSuite) " +
            "AND (:idTpcenario IS NULL OR c.idTpcenario = :idTpcenario) " +
            "AND (:idStatus IS NULL OR c.idStatus = :idStatus) " +
            "AND (:idAutomatizado IS NULL OR c.idAutomatizado = :idAutomatizado) " +
            "AND (:idPlataforma IS NULL OR c.idPlataforma = :idPlataforma)")
    int countCenariosByMultipleCriteria(
            @Param("idPlano") TestPlanEntity idPlano,
            @Param("idSuite") testSuiteEntity idSuite,
            @Param("idTpcenario") ScenarioTypeEntity idTpcenario,
            @Param("idStatus") ScenarioStatusEntity idStatus,
            @Param("idAutomatizado") AutomationStatusEntity idAutomatizado,
            @Param("idPlataforma") PlatformTypeEntity idPlataforma);

}

