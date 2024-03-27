package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.ProductEntity;
import io.github.vyctorhugocorreia.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    List<ProductEntity> findByIdTeam(TeamEntity time);


    boolean existsByDescProductAndIdTeam(String descProduto, TeamEntity idTime);


    @Query("SELECT p FROM ProductEntity p WHERE " +
            "(:idTeam IS NULL OR p.idTeam.idTeam = :idTeam) AND " +
            "(:idProduct IS NULL OR p.idProduct = :idProduct) AND" +
            "(:descProduct IS NULL OR p.descProduct LIKE %:descProduct%)")
    List<ProductEntity> searchProduct(@Param("idTeam") Long idTeam,
                                      @Param("idProduct") Long idProduct,
                                      @Param("descProduct") String descProduct);
}

