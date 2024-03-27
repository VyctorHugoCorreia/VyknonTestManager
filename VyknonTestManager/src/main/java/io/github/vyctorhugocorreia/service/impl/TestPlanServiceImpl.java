package io.github.vyctorhugocorreia.service.impl;


import io.github.vyctorhugocorreia.dto.TestPlanDTO;
import io.github.vyctorhugocorreia.entity.*;
import io.github.vyctorhugocorreia.exception.*;
import io.github.vyctorhugocorreia.repository.PlanoDeTestesRepository;
import io.github.vyctorhugocorreia.repository.ProdutoRepository;
import io.github.vyctorhugocorreia.repository.SuiteDeTesteRepository;
import io.github.vyctorhugocorreia.repository.TimeRepository;
import io.github.vyctorhugocorreia.service.TestPlanService;
import io.github.vyctorhugocorreia.util.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class TestPlanServiceImpl implements TestPlanService {

    private final ProdutoRepository productRepository;
    private final TimeRepository teamRepository;
    private final SuiteDeTesteRepository testSuiteRepository;
    private final UserInfo userInfo;
    private final PlanoDeTestesRepository testPlanRepository;

    @Override
    @Transactional
    public TestPlanEntity save(TestPlanDTO dto) {
        Long idTeam = dto.getIdTeam();
        TeamEntity team = teamRepository
                .findById(idTeam.intValue())
                .orElseThrow(() -> new RegraNegocioException("Time não encontrado"));

        Long idProduct = dto.getIdProduct();
        ProductEntity product = productRepository
                .findById(idProduct.intValue())
                .filter(p -> p.getIdTeam().getIdTeam().equals(idTeam))
                .orElseThrow(() -> new RegraNegocioException("Produto não encontrado"));


        String descTestPlan = dto.getDescTestPlan();

        validateIfPlanExistsForProduct(descTestPlan, product);

        TestPlanEntity plano = TestPlanEntity.builder()
                .descTestPlan(descTestPlan)
                .idTeam(team)
                .idProduct(product)
                .user(userInfo.user())
                .build();
        return testPlanRepository.save(plano);
    }


    public TestPlanEntity edit(Long idTestPlan, TestPlanDTO dto) {
        TestPlanEntity existingTestPlan = testPlanRepository
                .findById(idTestPlan.intValue())
                .orElseThrow(PlanoDeTestesNaoEncontradaException::new);

        Long idTeam = dto.getIdTeam();
        TeamEntity time = teamRepository
                .findById(idTeam.intValue())
                .orElseThrow(TimeNaoEncontradoException::new);

        Long idProduto = dto.getIdProduct();
        ProductEntity product = productRepository
                .findById(idProduto.intValue())
                .filter(p -> p.getIdTeam().getIdTeam().equals(idTeam))
                .orElseThrow(ProdutoNaoEncontradoException::new);

        String descTestPlan = dto.getDescTestPlan();

        validateIfPlanExistsForProductAndOthers(descTestPlan, product, existingTestPlan);

        existingTestPlan.setDescTestPlan(dto.getDescTestPlan());
        existingTestPlan.setIdTeam(time);
        existingTestPlan.setIdProduct(product);


        return testPlanRepository.save(existingTestPlan);
    }

    @Override
    @Transactional
    public String delete(Long id) {
        checkSuitesLinkedToTestPlan(id);
        testPlanRepository.delete(getExistingPlano(id));
        return "Plano de testes deletado com sucesso.";
    }


    private void checkSuitesLinkedToTestPlan(Long id) {
        TestPlanEntity testPlan = getExistingPlano(id);
        boolean SuitesVinculadas = testSuiteRepository.existsByIdPlano(testPlan);
        if (SuitesVinculadas) {
            throw new RegraNegocioException("Não é possível excluir o plano, pois existem suites vinculadas a ele.");
        }

    }

    private TestPlanEntity getExistingPlano(Long id) {
        return testPlanRepository.findById(id.intValue())
                .orElseThrow(PlanoDeTestesNaoEncontradaException::new);
    }


    void validateIfPlanExistsForProduct(String descTestPlan, ProductEntity product) {
        if (testPlanRepository.existsByDescPlanoAndIdTproduto(descTestPlan, product)) {
            throw new RegraNegocioException("Já existe um plano com o mesmo nome para este produto.");
        }
    }

    void validateIfPlanExistsForProductAndOthers(String descTestPlan, ProductEntity product, TestPlanEntity existingTestPlan) {
        if (testPlanRepository.existsByDescPlanoAndIdTprodutoAndIdPlanoNot(descTestPlan, product, existingTestPlan.getIdTestPlan())) {
            throw new RegraNegocioException("Já existe um plano de testes com o mesmo nome para este produto.");
        }
    }
}
