package io.github.vyctorhugocorreia.service;

import io.github.vyctorhugocorreia.dto.TestSuiteDTO;
import io.github.vyctorhugocorreia.entity.testSuiteEntity;

public interface TestSuiteService {

    testSuiteEntity save(TestSuiteDTO dto);

    testSuiteEntity edit(Long id, TestSuiteDTO dto);

    String delete(Long id);


}
