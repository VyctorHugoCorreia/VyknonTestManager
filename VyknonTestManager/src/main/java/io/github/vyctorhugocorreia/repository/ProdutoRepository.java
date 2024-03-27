package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.ProductEntity;
import io.github.vyctorhugocorreia.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProdutoRepository extends JpaRepository<ProductEntity, Integer> {

    List<ProductEntity> findByIdTime(TeamEntity time);


    boolean existsByDescProdutoAndIdTime(String descProduto, TeamEntity idTime);


    @Query("SELECT p FROM ProdutoEntity p WHERE " +
            "(:idTime IS NULL OR p.idTime.idTime = :idTime) AND " +
            "(:idTproduto IS NULL OR p.idTproduto = :idTproduto) AND" +
            "(:descProduto IS NULL OR p.descProduto LIKE %:descProduto%)")
    List<ProductEntity> searchProduto(@Param("idTime") Long idTime,
                                      @Param("idTproduto") Long idTproduto,
                                      @Param("descProduto") String descProduto);
}

