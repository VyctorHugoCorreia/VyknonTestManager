package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.TestPlanEntity;
import io.github.vyctorhugocorreia.entity.testSuiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TestSuiteRepository extends JpaRepository<testSuiteEntity, Long> {

    boolean existsByDescTestSuiteAndIdTestPlan(String descSuite, TestPlanEntity idPlano);

    boolean existsByDescTestSuiteAndIdTestPlanAndIdTestSuiteNot(String DescPlano, TestPlanEntity idPlano, Long idSuite);

    boolean existsByIdTestPlan(TestPlanEntity plano);
    @Query("SELECT COUNT(s) FROM TestSuiteEntity s WHERE s.idTestPlan = :idTestPlan")
    int countSuitesByTestPlan(@Param("plano") TestPlanEntity idTestPlan);

    @Query("SELECT s FROM TestSuiteEntity s WHERE s.idTestPlan = :idTestPlan")
    List<testSuiteEntity> findBySuitesIdTestPlan(@Param("idTestPlan") TestPlanEntity idTestPlan);


    @Query("SELECT p FROM TestSuiteEntity p WHERE " +
            "(:idTestSuite IS NULL OR p.idTestSuite = :idTestSuite) AND " +
            "(:idTeam IS NULL OR p.idTeam.idTeam = :idTeam) AND " +
            "(:idProduct IS NULL OR p.idProduct.idProduct = :idProduct) AND" +
            "(:idTestPlan IS NULL OR p.idTestPlan.idTestPlan = :idTestPlan) AND" +
            "(:descTestSuite IS NULL OR p.descTestSuite LIKE %:descTestSuite%)")
    List<testSuiteEntity> searchSuite(
            @Param("idTestSuite") Long idTestSuite,
            @Param("idTeam") Long idTeam,
            @Param("idProduct") Long idProduct,
            @Param("idTestPlan") Long idTestPlan,
            @Param("descTestSuite") String descTestSuite);
}

