package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ScenarioRepository extends JpaRepository<ScenarioEntity, Integer> {

    List<ScenarioEntity> findAll();
    
    @Query("SELECT c FROM ScenarioEntity c WHERE " +
            "(:idScenario IS NULL OR c.idScenario = :idScenario) AND " +
            "(:titleScenario IS NULL OR c.titleScenario LIKE %:titleScenario%) AND " +
            "(:idTeam IS NULL OR c.idTeam.idTeam = :idTeam) AND " +
            "(:idTestPlan IS NULL OR c.idTestPlan.idTestPlan = :idTestPlan) AND " +
            "(:idTestSuite IS NULL OR c.idTestSuite.idTestSuite = :idTestSuite) AND " +
            "(:idProduct IS NULL OR c.idProduct.idProduct = :idProduct) AND " +
            "(:idScenarioType IS NULL OR c.idScenarioType.idScenarioType = :idScenarioType) AND " +
            "(:idPlatformType IS NULL OR c.idPlatformType.idPlatformType = :idPlatformType) AND " +
            "(:idScenarioStatus IS NULL OR c.idScenarioStatus.idScenarioStatus = :idScenarioStatus) AND " +
            "(:idAutomationStatus IS NULL OR c.idAutomationStatus.idAutomationStatus = :idAutomationStatus) AND" +
            "(:tags IS NULL OR c.tags LIKE %:tags%) " )
    List<ScenarioEntity> searchCenario(
            @Param("idScenario") Long idScenario,
            @Param("titleScenario") String titleScenario,
            @Param("idTeam") Long idTeam,
            @Param("idTestPlan") Long idTestPlan,
            @Param("idTestSuite") Long idTestSuite,
            @Param("idProduct") Long idProduct,
            @Param("idScenarioType") Long idScenarioType,
            @Param("idPlatformType") Long idPlatformType,
            @Param("idScenarioStatus") Long idScenarioStatus,
            @Param("idAutomationStatus") Long idAutomationStatus,
            @Param("tags") String tags);

    @Query("SELECT COUNT(s) FROM ScenarioEntity s WHERE s.idProduct = :idProduct")
    int countScenariosByProduct(@Param("idProduct") ProductEntity idProduct);

    @Query("SELECT COUNT(s) FROM ScenarioEntity s WHERE s.idTestPlan = :idTestPlan")
    int countScenariosByTestPlan(@Param("idTestPlan") TestPlanEntity idTestPlan);

    @Query("SELECT COUNT(s) FROM ScenarioEntity s WHERE s.idTeam = :idTeam")
    int countScenariosByTeam(@Param("idTeam") TeamEntity idTeam);

    @Query("SELECT COUNT(c) FROM ScenarioEntity c WHERE c.idTestSuite = :idTestSuite")
    int countCenariosByTestSuite(@Param("idTestSuite") testSuiteEntity idTestSuite);


    @Query("SELECT COUNT(c) FROM ScenarioEntity c WHERE c.idScenarioType = :idScenarioType " +
            "AND (:idTeam IS NULL OR c.idTeam = :idTeam)")
    int countCenariosByScenarioType(@Param("idScenarioType") ScenarioTypeEntity idScenarioType,
                                            @Param("idTeam") TeamEntity idTeam);

    @Query("SELECT COUNT(c) FROM ScenarioEntity c WHERE c.idScenarioStatus = :idScenarioStatus " +
            "AND (:idTeam IS NULL OR c.idTeam = :idTeam)")
    int countCenariosByScenarioStatus(@Param("idScenarioStatus") ScenarioStatusEntity idScenarioStatus,
                                      @Param("idTeam") TeamEntity idTeam);

    @Query("SELECT COUNT(c) FROM ScenarioEntity c WHERE c.idAutomationStatus = :idAutomationStatus "+
            "AND (:idTeam IS NULL OR c.idTeam = :idTeam)")
    int countCenariosByAutomationStatus(@Param("idAutomationStatus") AutomationStatusEntity idAutomationStatus,
                                        @Param("idTeam") TeamEntity idTeam);

    @Query("SELECT COUNT(c) FROM ScenarioEntity c WHERE c.idPlatformType = :idPlatformType " +
            "AND (:idTeam IS NULL OR c.idTeam = :idTeam)")
    int countCenariosByPlataformType(@Param("idPlatformType") PlatformTypeEntity idPlatformType,
                                     @Param("idTeam") TeamEntity idTeam);

}

