package io.github.VyctorHugoCorreia.domain.repository;


import io.github.VyctorHugoCorreia.domain.entity.SuiteDeTesteEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SuiteDeTestesRepository extends JpaRepository<SuiteDeTesteEntity, Integer> {
}
