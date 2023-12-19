package io.github.vyctorhugocorreia.service.impl;


import io.github.vyctorhugocorreia.dto.FuncionalidadeDTO;
import io.github.vyctorhugocorreia.entity.FuncionalidadeEntity;
import io.github.vyctorhugocorreia.entity.ProdutoEntity;
import io.github.vyctorhugocorreia.entity.TimeEntity;
import io.github.vyctorhugocorreia.exception.FuncionalidadeNaoEncontradaException;
import io.github.vyctorhugocorreia.exception.ProdutoNaoEncontradoException;
import io.github.vyctorhugocorreia.exception.RegraNegocioException;
import io.github.vyctorhugocorreia.exception.TimeNaoEncontradoException;
import io.github.vyctorhugocorreia.repository.FuncionalidadeRepository;
import io.github.vyctorhugocorreia.repository.ProdutoRepository;
import io.github.vyctorhugocorreia.repository.TimeRepository;
import io.github.vyctorhugocorreia.service.FuncionalidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class FuncionalidadeServiceImpl implements FuncionalidadeService {

    private final ProdutoRepository produtoRepository;
    private final TimeRepository timeRepository;
    private final FuncionalidadeRepository funcionalidadeRepository;

    @Override
    @Transactional
    public FuncionalidadeEntity salvar(FuncionalidadeDTO dto) {
        Long idTime = dto.getIdTime();
        TimeEntity time = timeRepository
                .findById(idTime.intValue())
                .orElseThrow(TimeNaoEncontradoException::new);

        Long idProduto = dto.getIdTproduto();
        ProdutoEntity produto = produtoRepository
                .findById(idProduto.intValue())
                .filter(p -> p.getIdTime().getIdTime().equals(idTime))
                .orElseThrow(ProdutoNaoEncontradoException::new);


        String descFuncionalidade = dto.getDescFuncionalidade();

        validarSeFuncionalidadeExisteParaProduto(descFuncionalidade, produto);
        if (dto.getDescFuncionalidade().trim().isEmpty()) {
            throw new RegraNegocioException("Preencha um nome válido");
        }
        if (dto.getDescFuncionalidade().trim().length() > 100) {
            throw new RegraNegocioException("O nome deve ter no máximo 100 caracteres");
        }
        FuncionalidadeEntity funcionalidade = FuncionalidadeEntity.builder()
                .descFuncionalidade(descFuncionalidade)
                .idTime(time)
                .idTproduto(produto)
                .build();
        return funcionalidadeRepository.save(funcionalidade);
    }


    public FuncionalidadeEntity editar(Long funcionalidadeId, FuncionalidadeDTO dto) {
        // Verifique se a funcionalidade existe
        FuncionalidadeEntity funcionalidadeExistente = funcionalidadeRepository
                .findById(funcionalidadeId.intValue())
                .orElseThrow(FuncionalidadeNaoEncontradaException::new);

        Long idTime = dto.getIdTime();
        TimeEntity time = timeRepository
                .findById(idTime.intValue())
                .orElseThrow(TimeNaoEncontradoException::new);

        Long idProduto = dto.getIdTproduto();
        ProdutoEntity produto = produtoRepository
                .findById(idProduto.intValue())
                .filter(p -> p.getIdTime().getIdTime().equals(idTime))
                .orElseThrow(ProdutoNaoEncontradoException::new);

        String descFuncionalidade = dto.getDescFuncionalidade();
        if (dto.getDescFuncionalidade().trim().isEmpty()) {
            throw new RegraNegocioException("Preencha um nome válido");
        }
        if (dto.getDescFuncionalidade().trim().length() > 100) {
            throw new RegraNegocioException("O nome deve ter no máximo 100 caracteres");
        }

        // Valide se a nova descrição da funcionalidade não entra em conflito com outras funcionalidades do mesmo produto
        validarSeFuncionalidadeExisteParaProdutoEOutras(descFuncionalidade, produto, funcionalidadeExistente);

        funcionalidadeExistente.setDescFuncionalidade(dto.getDescFuncionalidade());
        funcionalidadeExistente.setIdTime(time);
        funcionalidadeExistente.setIdTproduto(produto);

        return funcionalidadeRepository.save(funcionalidadeExistente);
    }

    @Override
    @Transactional
    public String deletar(Long id) {
        funcionalidadeRepository.delete(getExistingFuncionalidade(id));
        return "Funcionalidade deletado com sucesso.";
    }

    private FuncionalidadeEntity getExistingFuncionalidade(Long id) {
        return funcionalidadeRepository.findById(id.intValue())
                .orElseThrow(FuncionalidadeNaoEncontradaException::new);
    }


    void validarSeFuncionalidadeExisteParaProduto(String descFuncionalidade, ProdutoEntity produto) {
        if (funcionalidadeRepository.existsByDescFuncionalidadeAndIdTproduto(descFuncionalidade, produto)) {
            throw new RegraNegocioException("Já existe uma funcionalidade com o mesmo nome para este produto.");
        }
    }

    void validarSeFuncionalidadeExisteParaProdutoEOutras(String descFuncionalidade, ProdutoEntity produto, FuncionalidadeEntity funcionalidadeExistente) {
        if (funcionalidadeRepository.existsByDescFuncionalidadeAndIdTprodutoAndIdFuncionalidadeNot(descFuncionalidade, produto, funcionalidadeExistente.getIdFuncionalidade())) {
            throw new RegraNegocioException("Já existe uma funcionalidade com o mesmo nome para este produto.");
        }
    }
}
