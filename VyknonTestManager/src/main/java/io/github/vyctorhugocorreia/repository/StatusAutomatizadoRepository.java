package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.StatusAutomatizadoEntity;
import io.github.vyctorhugocorreia.entity.TimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StatusAutomatizadoRepository extends JpaRepository<StatusAutomatizadoEntity, Integer> {

    List<StatusAutomatizadoEntity> findAll();
}

