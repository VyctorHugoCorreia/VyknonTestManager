package io.github.vyctorhugocorreia.service;

import io.github.vyctorhugocorreia.dto.TestPlanDTO;
import io.github.vyctorhugocorreia.entity.TestPlanEntity;

public interface TestPlanService {

    TestPlanEntity save(TestPlanDTO dto);

    TestPlanEntity edit(Long id, TestPlanDTO dto);

    String delete(Long id);


}
