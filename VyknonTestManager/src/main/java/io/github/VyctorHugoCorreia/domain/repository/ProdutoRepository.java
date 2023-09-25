package io.github.VyctorHugoCorreia.domain.repository;

import io.github.VyctorHugoCorreia.domain.entity.ProdutoEntity;
import io.github.VyctorHugoCorreia.domain.entity.TimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {

    @Query("SELECT p.idTime FROM ProdutoEntity p WHERE p.id = :produtoId")
    Integer findTimeIdByProdutoId(Integer produtoId);


}

