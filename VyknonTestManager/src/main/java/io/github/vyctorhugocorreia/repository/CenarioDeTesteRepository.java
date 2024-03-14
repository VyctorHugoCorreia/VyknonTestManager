package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.*;
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
            @Param("idTpcenario") Long idTpcenario,
            @Param("idPlataforma") Long idPlataforma,
            @Param("idStatus") Long idStatus,
            @Param("idAutomatizado") Long idAutomatizado,
            @Param("tags") String tags);

    @Query("SELECT COUNT(s) FROM CenarioDeTesteEntity s WHERE s.idTproduto = :idTproduto")
    int countScenariosByProduto(@Param("idTproduto") ProdutoEntity idTproduto);

    @Query("SELECT COUNT(s) FROM CenarioDeTesteEntity s WHERE s.idPlano = :idPlano")
    int countScenariosByTestPlan(@Param("idPlano") PlanoDeTesteEntity idPlano);

    @Query("SELECT COUNT(s) FROM CenarioDeTesteEntity s WHERE s.idTime = :idTime")
    int countScenariosByTime(@Param("idTime") TimeEntity idTime);

    @Query("SELECT COUNT(c) FROM CenarioDeTesteEntity c WHERE c.idSuite = :idSuite")
    int countCenariosBySuite(@Param("idSuite") SuiteDeTesteEntity idSuite);


    @Query("SELECT COUNT(c) FROM CenarioDeTesteEntity c WHERE c.idTpcenario = :idTpcenario " +
            "AND (:idTime IS NULL OR c.idTime = :idTime)")
    int countCenariosByScenarioType(@Param("idTpcenario") TipoCenarioEntity idTpcenario,
                                            @Param("idTime") TimeEntity idTime);

    @Query("SELECT COUNT(c) FROM CenarioDeTesteEntity c WHERE c.idStatus = :idStatus " +
            "AND (:idTime IS NULL OR c.idTime = :idTime)")
    int countCenariosByScenarioStatus(@Param("idStatus") StatusCenarioEntity idStatus,
                                      @Param("idTime") TimeEntity idTime);

    @Query("SELECT COUNT(c) FROM CenarioDeTesteEntity c WHERE c.idAutomatizado = :idAutomatizado "+
            "AND (:idTime IS NULL OR c.idTime = :idTime)")
    int countCenariosByAutomationStatus(@Param("idAutomatizado") StatusAutomatizadoEntity idAutomatizado,
                                        @Param("idTime") TimeEntity idTime);

    @Query("SELECT COUNT(c) FROM CenarioDeTesteEntity c WHERE c.idPlataforma = :idPlataforma " +
            "AND (:idTime IS NULL OR c.idTime = :idTime)")
    int countCenariosByPlataformType(@Param("idPlataforma") TipoPlataformaEntity idPlataforma,
                                     @Param("idTime") TimeEntity idTime);

    @Query("SELECT COUNT(c) FROM CenarioDeTesteEntity c " +
            "WHERE (:idPlano IS NULL OR c.idPlano = :idPlano) " +
            "AND (:idSuite IS NULL OR c.idSuite = :idSuite) " +
            "AND (:idTpcenario IS NULL OR c.idTpcenario = :idTpcenario) " +
            "AND (:idStatus IS NULL OR c.idStatus = :idStatus) " +
            "AND (:idAutomatizado IS NULL OR c.idAutomatizado = :idAutomatizado) " +
            "AND (:idPlataforma IS NULL OR c.idPlataforma = :idPlataforma)")
    int countCenariosByMultipleCriteria(
            @Param("idPlano") PlanoDeTesteEntity idPlano,
            @Param("idSuite") SuiteDeTesteEntity idSuite,
            @Param("idTpcenario") TipoCenarioEntity idTpcenario,
            @Param("idStatus") StatusCenarioEntity idStatus,
            @Param("idAutomatizado") StatusAutomatizadoEntity idAutomatizado,
            @Param("idPlataforma") TipoPlataformaEntity idPlataforma);

}

