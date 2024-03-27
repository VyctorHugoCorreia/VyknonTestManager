package io.github.vyctorhugocorreia.service.impl;


import io.github.vyctorhugocorreia.dto.TestSuiteDTO;
import io.github.vyctorhugocorreia.entity.*;
import io.github.vyctorhugocorreia.exception.*;
import io.github.vyctorhugocorreia.repository.*;
import io.github.vyctorhugocorreia.service.TestSuiteService;
import io.github.vyctorhugocorreia.util.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestSuiteServiceImpl implements TestSuiteService {

    private final SuiteDeTesteRepository testSuiteRepository;
    private final ProdutoRepository productRepository;
    private final TimeRepository teamRepository;
    private final PlanoDeTestesRepository testPlanRepository;
    private final CenarioDeTesteRepository scenarioRepository;
    private final UserInfo userInfo;


    @Override
    public testSuiteEntity save(TestSuiteDTO dto) {
        Long idTeam = dto.getIdTeam();
        TeamEntity teamEntity = teamRepository
                .findById(idTeam.intValue())
                .orElseThrow(TeamNotFoundException::new);

        Long idProduct = dto.getIdProduct();
        ProductEntity product = productRepository
                .findById(idProduct.intValue())
                .filter(p -> p.getIdTeam().getIdTeam().equals(idTeam))
                .orElseThrow(ProductNotFoundException::new);

        Long idTestPlan = dto.getIdTestPlan();
        TestPlanEntity testPlan = testPlanRepository
                .findById(idTestPlan.intValue())
                .filter(p -> p.getIdTeam().getIdTeam().equals(idTeam))
                .orElseThrow(() -> new RuleBusinessException("Plano não encontrado"));


        String descTestSuite = dto.getDescTestSuite();

        validateIfSuiteExistsForPlan(descTestSuite, testPlan);

        testSuiteEntity suite = testSuiteEntity.builder()
                .descTestSuite(descTestSuite)
                .idTeam(teamEntity)
                .idProduct(product)
                .idTestPlan(testPlan)
                .user(userInfo.user())
                .build();
        return testSuiteRepository.save(suite);
    }

    private void validateIfSuiteExistsForPlan(String descTestSuite, TestPlanEntity testPlan) {
        if (testSuiteRepository.existsByDescSuiteAndIdPlano(descTestSuite, testPlan)) {
            throw new RuleBusinessException("Já existe uma suite de testes com o mesmo nome para este plano.");
        }
    }

    @Override
    public testSuiteEntity edit(Long id, TestSuiteDTO dto) {
        testSuiteEntity existingSuite = testSuiteRepository
                .findById(id)
                .orElseThrow(() -> new RuleBusinessException("Suite não encotrada"));

        Long idTeam = dto.getIdTeam();
        TeamEntity teamEntity = teamRepository
                .findById(idTeam.intValue())
                .orElseThrow(() -> new RuleBusinessException("Time não encontrado"));

        Long idProduct = dto.getIdProduct();
        ProductEntity product = productRepository
                .findById(idProduct.intValue())
                .filter(p -> p.getIdTeam().getIdTeam().equals(idTeam))
                .orElseThrow(() -> new RuleBusinessException("Produto não encontrado"));

        Long idTestPlan = dto.getIdTestPlan();
        TestPlanEntity testPlanEntity = testPlanRepository
                .findById(idTestPlan.intValue())
                .filter(p -> p.getIdTeam().getIdTeam().equals(idTeam))
                .orElseThrow(() -> new RuleBusinessException("Produto não encontrado"));

        String descTestSuite = dto.getDescTestSuite();

        validateIfSuiteExistsForPlan(descTestSuite, testPlanEntity, existingSuite);

        existingSuite.setDescTestSuite(dto.getDescTestSuite());
        existingSuite.setIdTeam(teamEntity);
        existingSuite.setIdProduct(product);
        existingSuite.setIdTestPlan(testPlanEntity);


        return testSuiteRepository.save(existingSuite);
    }

    void validateIfSuiteExistsForPlan(String descTestPlan, TestPlanEntity testPlan, testSuiteEntity existingSuite) {
        if (testSuiteRepository.existsByDescSuiteAndIdPlanoAndIdSuiteNot(descTestPlan, testPlan, existingSuite.getIdTestSuite())) {
            throw new RuleBusinessException("Já existe uma suite de testes com o mesmo nome para este plano de testes.");
        }
    }

    @Override
    public String delete(Long idSuite) {
        testSuiteEntity testSuiteEntity = testSuiteRepository.findById(idSuite)
                .orElseThrow(() -> new RuleBusinessException("Suite não encontrada"));

        int scenarioQuantity = scenarioRepository.countCenariosBySuite(testSuiteEntity);

        if (haveLinkedScenario(scenarioQuantity)) {
            throw new RuleBusinessException("Não é possível excluir a suíte pois existem cenários vinculados a ela.");
        }

        testSuiteRepository.delete(testSuiteEntity);
        return "Suite de testes deletada com sucesso.";
    }

    private boolean haveLinkedScenario(int scenarioQuantity) {
        return scenarioQuantity > 0;
    }


    private testSuiteEntity getExistingSuite(Long id) {
        return testSuiteRepository.findById(id)
                .orElseThrow(TestSuiteNotFoundException::new);
    }
}

