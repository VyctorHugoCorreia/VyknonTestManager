package io.github.vyctorhugocorreia.service.impl;


import io.github.vyctorhugocorreia.entity.ProdutoEntity;
import io.github.vyctorhugocorreia.entity.TimeEntity;
import io.github.vyctorhugocorreia.repository.PlanoDeTestesRepository;
import io.github.vyctorhugocorreia.repository.ProdutoRepository;
import io.github.vyctorhugocorreia.repository.TimeRepository;
import io.github.vyctorhugocorreia.exception.ProdutoNaoEncontradoException;
import io.github.vyctorhugocorreia.exception.RegraNegocioException;
import io.github.vyctorhugocorreia.exception.TimeNaoEncontradoException;
import io.github.vyctorhugocorreia.dto.ProdutoDTO;
import io.github.vyctorhugocorreia.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final TimeRepository timeRepository;
    private final PlanoDeTestesRepository planoDeTestesRepository;

    @Override
    @Transactional
    public ProdutoEntity salvar(ProdutoDTO dto) {
        Long idTime = dto.getIdTime();
        TimeEntity time = timeRepository
                .findById(idTime.intValue())
                .orElseThrow(TimeNaoEncontradoException::new);

        String descProduto = dto.getDescProduto();
        validarProdutoParaTimeExistente(descProduto, time);
        if (dto.getDescProduto().trim().isEmpty()) {
            throw new RegraNegocioException("Preencha um nome válido");
        }
        if (dto.getDescProduto().trim().length() > 100) {
            throw new RegraNegocioException("O nome deve ter no máximo 100 caracteres");
        }
        ProdutoEntity produto = ProdutoEntity.builder().
                descProduto(descProduto)
                .idTime(time)
                .build();
        return produtoRepository.save(produto);
    }

    @Override
    public ProdutoEntity editar(Long id, ProdutoDTO dto) {
        ProdutoEntity existingProduto = getExistingProduto(id);
        String novoProdutoDesc = dto.getDescProduto();

        updateDescProduto(existingProduto, novoProdutoDesc);

        if (dto.getIdTime() != null && !dto.getIdTime().equals(existingProduto.getIdTime().getIdTime())) {
            Long novoIdTime = dto.getIdTime();
            TimeEntity novoTime = getTime(novoIdTime);
            validarProdutoParaNovoTime(novoProdutoDesc, novoTime);
            existingProduto.setIdTime(novoTime);
        }
        if (dto.getDescProduto().trim().isEmpty()) {
            throw new RegraNegocioException("Preencha um nome válido");
        }
        if (dto.getDescProduto().trim().length() > 100) {
            throw new RegraNegocioException("O nome deve ter no máximo 100 caracteres");
        }
        return produtoRepository.save(existingProduto);
    }

    @Override
    @Transactional
    public String deletar(Long id) {
        verificarPlanoDeTesteVinculadoAoProduto(id);
        produtoRepository.delete(getExistingProduto(id));

        return "Produto deletado com sucesso.";
    }


    private void verificarPlanoDeTesteVinculadoAoProduto(Long id) {
        ProdutoEntity produto = getExistingProduto(id);

        boolean planoDeTesteVinculado = planoDeTestesRepository.existsByIdTproduto(produto);
        if (planoDeTesteVinculado) {
            throw new RegraNegocioException("Não é possível excluir o produto, pois existem planos de teste vinculado a ele.");
        }
    }


    private ProdutoEntity getExistingProduto(Long id) {
        return produtoRepository.findById(id.intValue())
                .orElseThrow(ProdutoNaoEncontradoException::new);
    }

    private void updateDescProduto(ProdutoEntity produto, String novoProdutoDesc) {
        if (!novoProdutoDesc.equals(produto.getDescProduto())) {
            validarProdutoParaTimeExistente(novoProdutoDesc, produto.getIdTime());
            produto.setDescProduto(novoProdutoDesc);
        }
    }

    private TimeEntity getTime(Long idTime) {
        return timeRepository.findById(idTime.intValue())
                .orElseThrow(TimeNaoEncontradoException::new);
    }

    private void validarProdutoParaTimeExistente(String novoProdutoDesc, TimeEntity time) {
        if (produtoRepository.existsByDescProdutoAndIdTime(novoProdutoDesc, time)) {
            throw new RegraNegocioException("Já existe um produto com o mesmo nome para este time.");
        }
    }

    private void validarProdutoParaNovoTime(String novoProdutoDesc, TimeEntity novoTime) {
        validarProdutoParaTimeExistente(novoProdutoDesc, novoTime);
    }

}
