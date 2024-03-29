package io.github.vyctorhugocorreia.service.impl;


import io.github.vyctorhugocorreia.dto.PlanoDeTestesDTO;
import io.github.vyctorhugocorreia.entity.*;
import io.github.vyctorhugocorreia.exception.*;
import io.github.vyctorhugocorreia.repository.PlanoDeTestesRepository;
import io.github.vyctorhugocorreia.repository.ProdutoRepository;
import io.github.vyctorhugocorreia.repository.SuiteDeTesteRepository;
import io.github.vyctorhugocorreia.repository.TimeRepository;
import io.github.vyctorhugocorreia.service.PlanoDeTestesService;
import io.github.vyctorhugocorreia.util.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PlanoDeTestesServiceImpl implements PlanoDeTestesService {

    private final ProdutoRepository produtoRepository;
    private final TimeRepository timeRepository;
    private final SuiteDeTesteRepository suiteDeTesteRepository;
    private final UserInfo userInfo;
    private final PlanoDeTestesRepository planoDeTestesRepository;

    @Override
    @Transactional
    public PlanoDeTesteEntity salvar(PlanoDeTestesDTO dto) {
        Long idTime = dto.getIdTime();
        TimeEntity time = timeRepository
                .findById(idTime.intValue())
                .orElseThrow(TimeNaoEncontradoException::new);

        Long idProduto = dto.getIdTproduto();
        ProdutoEntity produto = produtoRepository
                .findById(idProduto.intValue())
                .filter(p -> p.getIdTime().getIdTime().equals(idTime))
                .orElseThrow(ProdutoNaoEncontradoException::new);


        String descPlano = dto.getDescPlano();

        validarSePlanoExisteParaProduto(descPlano, produto);

        PlanoDeTesteEntity plano = PlanoDeTesteEntity.builder()
                .descPlano(descPlano)
                .idTime(time)
                .idTproduto(produto)
                .usuario(userInfo.usuario())
                .build();
        return planoDeTestesRepository.save(plano);
    }


    public PlanoDeTesteEntity editar(Long planoId, PlanoDeTestesDTO dto) {
        PlanoDeTesteEntity planoExistente = planoDeTestesRepository
                .findById(planoId.intValue())
                .orElseThrow(PlanoDeTestesNaoEncontradaException::new);

        Long idTime = dto.getIdTime();
        TimeEntity time = timeRepository
                .findById(idTime.intValue())
                .orElseThrow(TimeNaoEncontradoException::new);

        Long idProduto = dto.getIdTproduto();
        ProdutoEntity produto = produtoRepository
                .findById(idProduto.intValue())
                .filter(p -> p.getIdTime().getIdTime().equals(idTime))
                .orElseThrow(ProdutoNaoEncontradoException::new);

        String descPlano = dto.getDescPlano();

        validarSePlanoExisteParaProdutoEOutras(descPlano, produto, planoExistente);

        // Atualize os atributos do planoExistente com os novos valores
        planoExistente.setDescPlano(dto.getDescPlano());
        planoExistente.setIdTime(time);
        planoExistente.setIdTproduto(produto);


        return planoDeTestesRepository.save(planoExistente);
    }

    @Override
    @Transactional
    public String deletar(Long id) {
        verificarSuitesVinculadoAoPlanoDeTeste(id);
        planoDeTestesRepository.delete(getExistingPlano(id));
        return "Plano de testes deletado com sucesso.";
    }


    private void verificarSuitesVinculadoAoPlanoDeTeste(Long id) {
        PlanoDeTesteEntity plano = getExistingPlano(id);
        boolean SuitesVinculadas = suiteDeTesteRepository.existsByIdPlano(plano);
        if (SuitesVinculadas) {
            throw new RegraNegocioException("Não é possível excluir o plano, pois existem suites vinculadas a ele.");
        }

    }

    private PlanoDeTesteEntity getExistingPlano(Long id) {
        return planoDeTestesRepository.findById(id.intValue())
                .orElseThrow(PlanoDeTestesNaoEncontradaException::new);
    }


    void validarSePlanoExisteParaProduto(String descPlano, ProdutoEntity produto) {
        if (planoDeTestesRepository.existsByDescPlanoAndIdTproduto(descPlano, produto)) {
            throw new RegraNegocioException("Já existe um plano com o mesmo nome para este produto.");
        }
    }

    void validarSePlanoExisteParaProdutoEOutras(String descPlano, ProdutoEntity produto, PlanoDeTesteEntity planoDeTestesExistente) {
        if (planoDeTestesRepository.existsByDescPlanoAndIdTprodutoAndIdPlanoNot(descPlano, produto, planoDeTestesExistente.getIdPlano())) {
            throw new RegraNegocioException("Já existe um plano de testes com o mesmo nome para este produto.");
        }
    }
}
