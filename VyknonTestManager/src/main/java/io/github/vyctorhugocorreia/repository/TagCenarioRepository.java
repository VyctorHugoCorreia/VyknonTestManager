package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.CenarioDeTesteEntity;
import io.github.vyctorhugocorreia.entity.StatusAutomatizadoEntity;
import io.github.vyctorhugocorreia.entity.TagCenarioEntity;
import io.github.vyctorhugocorreia.entity.TimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TagCenarioRepository extends JpaRepository<TagCenarioEntity, Integer> {

    List<TagCenarioEntity> findAll();

    TagCenarioEntity findIdCenarioByDescTagIgnoreCase(String descTag);



    boolean existsByDescTagAndIdCenario(String descProduto, CenarioDeTesteEntity idCenario);

}

