package io.github.VyctorHugoCorreia.domain.repository;

import io.github.VyctorHugoCorreia.domain.entity.TimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TimeRepository extends JpaRepository<TimeEntity, Integer > {

}
