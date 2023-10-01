package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.FuncionalidadeEntity;
import io.github.vyctorhugocorreia.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
;import java.util.List;


public interface FuncionalidadeRepository extends JpaRepository<FuncionalidadeEntity, Integer> {


    boolean existsByDescFuncionalidadeAndIdTproduto(String descProduto, ProdutoEntity idTproduto);

    boolean existsByDescFuncionalidadeAndIdTprodutoAndIdFuncionalidadeNot(String descFuncionalidade, ProdutoEntity idTproduto, Long idFuncionalidade);


    @Query("SELECT f FROM FuncionalidadeEntity f WHERE " +
            "(:idTime IS NULL OR f.idTime.idTime = :idTime) AND " +
            "(:idTproduto IS NULL OR f.idTproduto.idTproduto = :idTproduto) AND" +
            "(:descFuncionalidade IS NULL OR f.descFuncionalidade LIKE %:descFuncionalidade%)")
    List<FuncionalidadeEntity> searchFuncionalidade(@Param("idTime") Long idTime,
                                      @Param("idTproduto") Long idTproduto,
                                      @Param("descFuncionalidade") String descFuncionalidade);
}

