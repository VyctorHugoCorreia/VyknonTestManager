package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.FuncionalidadeEntity;
import io.github.vyctorhugocorreia.entity.StatusAutomatizadoEntity;
import io.github.vyctorhugocorreia.entity.TipoCenarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TipoCenarioRepository extends JpaRepository<TipoCenarioEntity, Integer> {
    List<TipoCenarioEntity> findAll();
}

