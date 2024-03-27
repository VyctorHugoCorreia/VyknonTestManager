package io.github.vyctorhugocorreia.service.impl;


import io.github.vyctorhugocorreia.entity.ProductEntity;
import io.github.vyctorhugocorreia.entity.TeamEntity;
import io.github.vyctorhugocorreia.repository.PlanoDeTestesRepository;
import io.github.vyctorhugocorreia.repository.ProdutoRepository;
import io.github.vyctorhugocorreia.repository.TimeRepository;
import io.github.vyctorhugocorreia.exception.ProductNotFoundException;
import io.github.vyctorhugocorreia.exception.RuleBusinessException;
import io.github.vyctorhugocorreia.exception.TeamNotFoundException;
import io.github.vyctorhugocorreia.dto.ProductDTO;
import io.github.vyctorhugocorreia.service.ProductService;
import io.github.vyctorhugocorreia.util.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProdutoRepository productRepository;
    private final TimeRepository teamRepository;
    private final PlanoDeTestesRepository testPlanRepository;
    private final UserInfo userInfo;


    @Override
    @Transactional
    public ProductEntity save(ProductDTO dto) {
        Long idTeam = dto.getIdTeam();
        TeamEntity teamEntity = teamRepository
                .findById(idTeam.intValue())
                .orElseThrow(() -> new RuleBusinessException("Time não encontrado"));

        String descProduct = dto.getDescProduct();
        validateProductForExistingTeam(descProduct, teamEntity);
        if (dto.getDescProduct().trim().isEmpty()) {
            throw new RuleBusinessException("Preencha um nome válido");
        }
        if (dto.getDescProduct().trim().length() > 100) {
            throw new RuleBusinessException("O nome deve ter no máximo 100 caracteres");
        }

        ProductEntity product = ProductEntity.builder().
                descProduct(descProduct)
                .idTeam(teamEntity)
                .user(userInfo.user())
                .build();
        return productRepository.save(product);
    }

    @Override
    public ProductEntity edit(Long id, ProductDTO dto) {
        ProductEntity existingProduct = getExistingProduto(id);
        String newProductDesc = dto.getDescProduct();

        updatedescProduct(existingProduct, newProductDesc);

        if (dto.getIdTeam() != null && !dto.getIdTeam().equals(existingProduct.getIdTeam().getIdTeam())) {
            Long newIdTeam = dto.getIdTeam();
            TeamEntity newTeam = getTeam(newIdTeam);
            validateProductForNewTeam(newProductDesc, newTeam);
            existingProduct.setIdTeam(newTeam);
        }
        if (dto.getDescProduct().trim().isEmpty()) {
            throw new RuleBusinessException("Preencha um nome válido");
        }
        if (dto.getDescProduct().trim().length() > 100) {
            throw new RuleBusinessException("O nome deve ter no máximo 100 caracteres");
        }
        return productRepository.save(existingProduct);
    }

    @Override
    @Transactional
    public String delete(Long id) {
        checkProductLinkedTestPlan(id);
        productRepository.delete(getExistingProduto(id));

        return "Produto deletado com sucesso.";
    }


    private void checkProductLinkedTestPlan(Long id) {
        ProductEntity produto = getExistingProduto(id);

        boolean planoDeTesteVinculado = testPlanRepository.existsByIdTproduto(produto);
        if (planoDeTesteVinculado) {
            throw new RuleBusinessException("Não é possível excluir o produto, pois existem planos de teste vinculado a ele.");
        }
    }


    private ProductEntity getExistingProduto(Long id) {
        return productRepository.findById(id.intValue())
                .orElseThrow(ProductNotFoundException::new);
    }

    private void updatedescProduct(ProductEntity product, String newProductDesc) {
        if (!newProductDesc.equals(product.getDescProduct())) {
            validateProductForExistingTeam(newProductDesc, product.getIdTeam());
            product.setDescProduct(newProductDesc);
        }
    }

    private TeamEntity getTeam(Long idTeam) {
        return teamRepository.findById(idTeam.intValue())
                .orElseThrow(TeamNotFoundException::new);
    }

    private void validateProductForExistingTeam(String newProductDesc, TeamEntity time) {
        if (productRepository.existsByDescProdutoAndIdTime(newProductDesc, time)) {
            throw new RuleBusinessException("Já existe um produto com o mesmo nome para este time.");
        }
    }

    private void validateProductForNewTeam(String novoProdutoDesc, TeamEntity novoTime) {
        validateProductForExistingTeam(novoProdutoDesc, novoTime);
    }

}
