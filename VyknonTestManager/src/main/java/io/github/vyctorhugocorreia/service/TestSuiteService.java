package io.github.vyctorhugocorreia.service;

import io.github.vyctorhugocorreia.dto.TestSuiteDTO;
import io.github.vyctorhugocorreia.entity.TestSuiteEntity;

public interface TestSuiteService {

    TestSuiteEntity save(TestSuiteDTO dto);

    TestSuiteEntity edit(Long id, TestSuiteDTO dto);

    String delete(Long id);


}
