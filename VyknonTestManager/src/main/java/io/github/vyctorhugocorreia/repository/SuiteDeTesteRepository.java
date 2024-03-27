package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.TestPlanEntity;
import io.github.vyctorhugocorreia.entity.testSuiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SuiteDeTesteRepository extends JpaRepository<testSuiteEntity, Long> {

    boolean existsByDescSuiteAndIdPlano(String descSuite, TestPlanEntity idPlano);

    boolean existsByDescSuiteAndIdPlanoAndIdSuiteNot(String DescPlano, TestPlanEntity idPlano, Long idSuite);

    boolean existsByIdPlano(TestPlanEntity plano);
    @Query("SELECT COUNT(s) FROM SuiteDeTesteEntity s WHERE s.idPlano = :plano")
    int countSuitesByPlano(@Param("plano") TestPlanEntity plano);

    @Query("SELECT s FROM SuiteDeTesteEntity s WHERE s.idPlano = :plano")
    List<testSuiteEntity> findBySuitesIdPlano(@Param("plano") TestPlanEntity plano);


    @Query("SELECT p FROM SuiteDeTesteEntity p WHERE " +
            "(:idSuite IS NULL OR p.idSuite = :idSuite) AND " +
            "(:idTime IS NULL OR p.idTime.idTime = :idTime) AND " +
            "(:idTproduto IS NULL OR p.idTproduto.idTproduto = :idTproduto) AND" +
            "(:idPlano IS NULL OR p.idPlano.idPlano = :idPlano) AND" +
            "(:descSuite IS NULL OR p.descSuite LIKE %:descSuite%)")
    List<testSuiteEntity> searchSuite(
            @Param("idSuite") Long idSuite,
            @Param("idTime") Long idTime,
            @Param("idTproduto") Long idTproduto,
            @Param("idPlano") Long idplano,
            @Param("descSuite") String descSuite);
}

