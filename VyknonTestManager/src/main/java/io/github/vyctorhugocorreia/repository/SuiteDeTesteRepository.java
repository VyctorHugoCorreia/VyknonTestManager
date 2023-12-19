package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.PlanoDeTesteEntity;
import io.github.vyctorhugocorreia.entity.ProdutoEntity;
import io.github.vyctorhugocorreia.entity.SuiteDeTesteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SuiteDeTesteRepository extends JpaRepository<SuiteDeTesteEntity, Long> {

    boolean existsByDescSuiteAndIdPlano(String descSuite, PlanoDeTesteEntity idPlano);

    boolean existsByDescSuiteAndIdPlanoAndIdSuiteNot(String DescPlano, PlanoDeTesteEntity idPlano, Long idSuite);

    boolean existsByIdPlano(PlanoDeTesteEntity plano);
    @Query("SELECT COUNT(s) FROM SuiteDeTesteEntity s WHERE s.idPlano = :plano")
    int countSuitesByPlano(@Param("plano") PlanoDeTesteEntity plano);

    @Query("SELECT s FROM SuiteDeTesteEntity s WHERE s.idPlano = :plano")
    List<SuiteDeTesteEntity> findBySuitesIdPlano(@Param("plano") PlanoDeTesteEntity plano);


    @Query("SELECT p FROM SuiteDeTesteEntity p WHERE " +
            "(:idSuite IS NULL OR p.idSuite = :idSuite) AND " +
            "(:idTime IS NULL OR p.idTime.idTime = :idTime) AND " +
            "(:idTproduto IS NULL OR p.idTproduto.idTproduto = :idTproduto) AND" +
            "(:idPlano IS NULL OR p.idPlano.idPlano = :idPlano) AND" +
            "(:descSuite IS NULL OR p.descSuite LIKE :descSuite)")
    List<SuiteDeTesteEntity> searchSuite(
            @Param("idSuite") Long idSuite,
            @Param("idTime") Long idTime,
            @Param("idTproduto") Long idTproduto,
            @Param("idPlano") Long idplano,
            @Param("descSuite") String descSuite);
}

