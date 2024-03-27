package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.TestPlanEntity;
import io.github.vyctorhugocorreia.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TestPlanRepository extends JpaRepository<TestPlanEntity, Integer> {

    boolean existsByDescTestPlanAndIdProduct(String DescPlano, ProductEntity idTproduto);

    boolean existsByDescTestPlanAndIdProductAndIdTestPlanNot(String DescPlano, ProductEntity idTproduto, Long idPlano);




    boolean existsByIdProduct(ProductEntity produto);
    @Query("SELECT p FROM TestPlanEntity p WHERE " +
            "(:idTestPlan IS NULL OR p.idTestPlan = :idTestPlan) AND " +
            "(:idTeam IS NULL OR p.idTeam.idTeam = :idTeam) AND " +
            "(:idProduct IS NULL OR p.idProduct.idProduct = :idProduct) AND" +
            "(:descTestPlan IS NULL OR p.descTestPlan LIKE %:descTestPlan%)")
    List<TestPlanEntity> searchPlano(
            @Param("idTestPlan") Long idTestPlan,
            @Param("idTeam") Long idTeam,
            @Param("idProduct") Long idProduct,
            @Param("descTestPlan") String descTestPlan);
}

