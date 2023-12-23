package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.CenarioDeTesteEntity;
import io.github.vyctorhugocorreia.entity.ProdutoEntity;
import io.github.vyctorhugocorreia.entity.TagCenarioEntity;
import io.github.vyctorhugocorreia.entity.TimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CenarioDeTesteRepository extends JpaRepository<CenarioDeTesteEntity, Integer> {

    List<CenarioDeTesteEntity> findAll();

    List<CenarioDeTesteEntity> findByIdCenario(CenarioDeTesteEntity cenario);
}

