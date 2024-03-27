package io.github.vyctorhugocorreia.service.impl;


import io.github.vyctorhugocorreia.dto.ScenarioDTO;
import io.github.vyctorhugocorreia.dto.StepDTO;
import io.github.vyctorhugocorreia.entity.*;
import io.github.vyctorhugocorreia.exception.*;
import io.github.vyctorhugocorreia.repository.*;
import io.github.vyctorhugocorreia.service.ScenarioService;
import io.github.vyctorhugocorreia.util.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ScenarioImpl implements ScenarioService {

    private final TeamRepository teamRepository;

    private final TestPlanRepository testPlanRepository;

    private final io.github.vyctorhugocorreia.repository.testSuiteRepository testSuiteRepository;

    private final ProductRepository productRepository;

    private final ScenarioTypeRepository scenarioTypeRepository;

    private final PlatformTypeRepository platformTypeRepository;

    private final ScenarioStatusRepository scenarioStatusRepository;

    private final AutomationStatusRepository automationStatusRepository;

    private final ScenarioRepository scenarioRepository;

    private final HistoryStatusScenarioRepository historyStatusScenarioRepository;

    private final UserInfo userInfo;
    @Override
    @Transactional
    public ScenarioEntity save(ScenarioDTO dto) {
        String titleScenario = dto.getTitleScenario();
        String descScenario = dto.getDescScenario();
        String linkScenario = dto.getLinkScenario();
        List<StepDTO> stepsList = dto.getSteps();
        List<String> tagsList = dto.getTags();

        Long idTeam = dto.getIdTeam();
        Long idTestPlan = dto.getIdTestPlan();
        Long idTestSuite = dto.getIdTestSuite();
        Long idProduct = dto.getIdProduct();
        Long idScenarioType = dto.getIdScenarioType();
        Long idPlatformType = dto.getIdPlatformType();
        Long idScenarioStatus = dto.getIdScenarioStatus();
        Long idAutomationStatus = dto.getIdAutomationStatus();


        TeamEntity team = teamRepository
                .findById(idTeam.intValue())
                .orElseThrow(TeamNotFoundException::new);

        TestPlanEntity testPlan = testPlanRepository
                .findById(idTestPlan.intValue())
                .orElseThrow(TestPlanNotFoundException::new);

        testSuiteEntity testSuite = testSuiteRepository
                .findById(idTestSuite)
                .orElseThrow(TestSuiteNotFoundException::new);

        ProductEntity product = productRepository
                .findById(idProduct.intValue())
                .orElseThrow(ProductNotFoundException::new);

        ScenarioTypeEntity scenarioType = scenarioTypeRepository
                .findById(idScenarioType.intValue())
                .orElseThrow(() -> new RuleBusinessException("Tipo de cenário não encontrado"));

        PlatformTypeEntity platformType = platformTypeRepository
                .findById(idPlatformType.intValue())
                .orElseThrow(() -> new RuleBusinessException("Plataforma não encontrada"));

        ScenarioStatusEntity scenarioStatus = scenarioStatusRepository
                .findById(idScenarioStatus.intValue())
                .orElseThrow(() -> new RuleBusinessException("Status do cenário não encontrada"));

        AutomationStatusEntity automationStatus = automationStatusRepository
                .findById(idAutomationStatus.intValue())
                .orElseThrow(() -> new RuleBusinessException("Status automatizado não encontrada"));


ScenarioEntity scenario = ScenarioEntity.builder()
        .titleScenario(titleScenario)
        .descScenario(descScenario)
        .linkScenario(linkScenario)
        .idTeam(team)
        .idTestPlan(testPlan)
        .idTestSuite(testSuite)
        .idProduct(product)
        .idScenarioType(scenarioType)
        .idPlatformType(platformType)
        .idScenarioStatus(scenarioStatus)
        .idAutomationStatus(automationStatus)
        .steps(stepsList)
        .tags(tagsList)
        .dateCreation(LocalDateTime.now())
        .dateUpdate(LocalDateTime.now())
        .user(userInfo.user())
        .build();

        return scenarioRepository.save(scenario);
    }

    @Override
    @Transactional
    public ScenarioEntity edit(Long id, ScenarioDTO dto) {
        String titleScenario = dto.getTitleScenario();
        String descScenario = dto.getDescScenario();
        String linkScenario = dto.getLinkScenario();
        List<StepDTO> stepsList = dto.getSteps();
        List<String> tagsList = dto.getTags();

        Long idTeam = dto.getIdTeam();
        Long idTestPlan = dto.getIdTestPlan();
        Long idTestSuite = dto.getIdTestSuite();
        Long idProduct = dto.getIdProduct();
        Long idScenarioType = dto.getIdScenarioType();
        Long idPlatformType = dto.getIdPlatformType();
        Long idScenarioStatus = dto.getIdScenarioStatus();
        Long idAutomationStatus = dto.getIdAutomationStatus();


        ScenarioEntity existingScenario = scenarioRepository
                .findById(id.intValue())
                .orElseThrow(() -> new RuleBusinessException("Cenário não encontrado"));

        TeamEntity team = teamRepository
                .findById(idTeam.intValue())
                .orElseThrow(TeamNotFoundException::new);

        TestPlanEntity testPlan = testPlanRepository
                .findById(idTestPlan.intValue())
                .orElseThrow(TestPlanNotFoundException::new);

        testSuiteEntity testSuite = testSuiteRepository
                .findById(idTestSuite)
                .orElseThrow(TestSuiteNotFoundException::new);

        ProductEntity product = productRepository
                .findById(idProduct.intValue())
                .orElseThrow(ProductNotFoundException::new);

        ScenarioTypeEntity scenarioType = scenarioTypeRepository
                .findById(idScenarioType.intValue())
                .orElseThrow(() -> new RuleBusinessException("Tipo de cenário não encontrado"));

        PlatformTypeEntity platformType = platformTypeRepository
                .findById(idPlatformType.intValue())
                .orElseThrow(() -> new RuleBusinessException("Plataforma não encontrada"));

        ScenarioStatusEntity scenarioStatus = scenarioStatusRepository
                .findById(idScenarioStatus.intValue())
                .orElseThrow(() -> new RuleBusinessException("Status do cenário não encontrada"));

        AutomationStatusEntity automationStatus = automationStatusRepository
                .findById(idAutomationStatus.intValue())
                .orElseThrow(() -> new RuleBusinessException("Status automatizado não encontrada"));

        existingScenario.setTitleScenario(titleScenario);
        existingScenario.setDescScenario(descScenario);
        existingScenario.setLinkScenario(linkScenario);
        existingScenario.setSteps(stepsList);
        existingScenario.setTags(tagsList);
        existingScenario.setIdTeam(team);
        existingScenario.setIdTestPlan(testPlan);
        existingScenario.setIdTestSuite(testSuite);
        existingScenario.setIdProduct(product);
        existingScenario.setIdScenarioType(scenarioType);
        existingScenario.setIdPlatformType(platformType);
        existingScenario.setIdScenarioStatus(scenarioStatus);
        existingScenario.setIdAutomationStatus(automationStatus);
        existingScenario.setDateUpdate(LocalDateTime.now());

        return scenarioRepository.save(existingScenario);
    }



    @Override
    @Transactional
    public String delete(Long id) {

        ScenarioEntity scenario = scenarioRepository.findById(id.intValue())
                .orElseThrow(() -> new RuleBusinessException("Cenário não encontrado"));

        historyStatusScenarioRepository.deleteByScenario(id);

        scenarioRepository.delete(scenario);

        return "Cenário deletado com sucesso.";
    }



}
