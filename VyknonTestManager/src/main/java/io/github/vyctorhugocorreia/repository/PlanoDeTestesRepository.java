package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.TestPlanEntity;
import io.github.vyctorhugocorreia.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PlanoDeTestesRepository extends JpaRepository<TestPlanEntity, Integer> {

    boolean existsByDescPlanoAndIdTproduto(String DescPlano, ProductEntity idTproduto);

    boolean existsByDescPlanoAndIdTprodutoAndIdPlanoNot(String DescPlano, ProductEntity idTproduto, Long idPlano);




    boolean existsByIdTproduto(ProductEntity produto);
    @Query("SELECT p FROM PlanoDeTesteEntity p WHERE " +
            "(:idPlano IS NULL OR p.idPlano = :idPlano) AND " +
            "(:idTime IS NULL OR p.idTime.idTime = :idTime) AND " +
            "(:idTproduto IS NULL OR p.idTproduto.idTproduto = :idTproduto) AND" +
            "(:descPlano IS NULL OR p.descPlano LIKE %:descPlano%)")
    List<TestPlanEntity> searchPlano(
            @Param("idPlano") Long idPlano,
            @Param("idTime") Long idTime,
            @Param("idTproduto") Long idTproduto,
            @Param("descPlano") String descPlano);
}

