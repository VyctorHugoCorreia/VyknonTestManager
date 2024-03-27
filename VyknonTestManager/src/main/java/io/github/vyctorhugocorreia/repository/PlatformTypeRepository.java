package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.PlatformTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PlatformTypeRepository extends JpaRepository<PlatformTypeEntity, Integer> {

    List<PlatformTypeEntity> findAll();
}
