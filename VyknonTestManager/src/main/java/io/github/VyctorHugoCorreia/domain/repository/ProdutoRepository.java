package io.github.VyctorHugoCorreia.domain.repository;

import io.github.VyctorHugoCorreia.domain.entity.ProdutoEntity;
import io.github.VyctorHugoCorreia.domain.entity.TimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {

    List<ProdutoEntity> findByIdTime(TimeEntity time);


    boolean existsByDescProdutoAndIdTime(String descProduto, TimeEntity idTime);

    @Query("SELECT p FROM ProdutoEntity p WHERE " +
            "(:idTime IS NULL OR p.idTime.idTime = :idTime) AND " +
            "(:idTproduto IS NULL OR p.idTproduto = :idTproduto) AND" +
            "(:descProduto IS NULL OR p.descProduto LIKE %:descProduto%)")
    List<ProdutoEntity> searchProduto(@Param("idTime") Long idTime,
                                   @Param("idTproduto") Long idTproduto,
                                   @Param("descProduto") String descProduto);
}

