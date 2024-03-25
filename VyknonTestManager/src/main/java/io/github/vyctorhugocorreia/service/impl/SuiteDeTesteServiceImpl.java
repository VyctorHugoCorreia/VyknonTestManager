package io.github.vyctorhugocorreia.service.impl;


import io.github.vyctorhugocorreia.dto.SuiteDeTesteDTO;
import io.github.vyctorhugocorreia.entity.*;
import io.github.vyctorhugocorreia.exception.*;
import io.github.vyctorhugocorreia.repository.*;
import io.github.vyctorhugocorreia.service.SuiteDeTesteService;
import io.github.vyctorhugocorreia.util.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SuiteDeTesteServiceImpl implements SuiteDeTesteService {

    private final SuiteDeTesteRepository suiteDeTesteRepository;
    private final ProdutoRepository produtoRepository;
    private final TimeRepository timeRepository;
    private final PlanoDeTestesRepository planoDeTestesRepository;
    private final CenarioDeTesteRepository cenarioDeTesteRepository;
    private final UserInfo userInfo;


    @Override
    public SuiteDeTesteEntity salvar(SuiteDeTesteDTO dto) {
        Long idTime = dto.getIdTime();
        TimeEntity time = timeRepository
                .findById(idTime.intValue())
                .orElseThrow(TimeNaoEncontradoException::new);

        Long idProduto = dto.getIdTproduto();
        ProdutoEntity produto = produtoRepository
                .findById(idProduto.intValue())
                .filter(p -> p.getIdTime().getIdTime().equals(idTime))
                .orElseThrow(ProdutoNaoEncontradoException::new);

        Long idPlano = dto.getIdPlano();
        System.out.println(idPlano);
        System.out.println(idTime);
        PlanoDeTesteEntity plano = planoDeTestesRepository
                .findById(idPlano.intValue())
                .filter(p -> p.getIdTime().getIdTime().equals(idTime))
                .orElseThrow(PlanoDeTestesNaoEncontradaException::new);

        Optional<UsuarioEntity> usuarioOptional = userInfo.obterUsuarioLogado();
        UsuarioEntity usuario = usuarioOptional.orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));


        String descSuite = dto.getDescSuite();

        validarSeSuiteExisteParaPlano(descSuite, plano);

        SuiteDeTesteEntity suite = SuiteDeTesteEntity.builder()
                .descSuite(descSuite)
                .idTime(time)
                .idTproduto(produto)
                .idPlano(plano)
                .usuario(usuario)
                .build();
        return suiteDeTesteRepository.save(suite);
    }

    private void validarSeSuiteExisteParaPlano(String descSuite, PlanoDeTesteEntity plano) {
        if (suiteDeTesteRepository.existsByDescSuiteAndIdPlano(descSuite, plano)) {
            throw new RegraNegocioException("Já existe uma suite de testes com o mesmo nome para este plano.");
        }
    }

    @Override
    public SuiteDeTesteEntity editar(Long id, SuiteDeTesteDTO dto) {
        SuiteDeTesteEntity suiteExistente = suiteDeTesteRepository
                .findById(id)
                .orElseThrow(SuiteDeTesteNaoEncotradoException::new);

        Long idTime = dto.getIdTime();
        TimeEntity time = timeRepository
                .findById(idTime.intValue())
                .orElseThrow(TimeNaoEncontradoException::new);

        Long idProduto = dto.getIdTproduto();
        ProdutoEntity produto = produtoRepository
                .findById(idProduto.intValue())
                .filter(p -> p.getIdTime().getIdTime().equals(idTime))
                .orElseThrow(ProdutoNaoEncontradoException::new);

        Long idPlano = dto.getIdPlano();
        PlanoDeTesteEntity plano = planoDeTestesRepository
                .findById(idPlano.intValue())
                .filter(p -> p.getIdTime().getIdTime().equals(idTime))
                .orElseThrow(ProdutoNaoEncontradoException::new);

        String descSuite = dto.getDescSuite();

        validarSeSuiteExisteParaPlano(descSuite, plano, suiteExistente);

        // Atualize os atributos do planoExistente com os novos valores
        suiteExistente.setDescSuite(dto.getDescSuite());
        suiteExistente.setIdTime(time);
        suiteExistente.setIdTproduto(produto);
        suiteExistente.setIdPlano(plano);


        return suiteDeTesteRepository.save(suiteExistente);
    }

    void validarSeSuiteExisteParaPlano(String descPlano, PlanoDeTesteEntity plano, SuiteDeTesteEntity suiteDeTesteExistente) {
        if (suiteDeTesteRepository.existsByDescSuiteAndIdPlanoAndIdSuiteNot(descPlano, plano, suiteDeTesteExistente.getIdSuite())) {
            throw new RegraNegocioException("Já existe uma suite de testes com o mesmo nome para este plano de testes.");
        }
    }

    @Override
    public String deletar(Long idSuite) {
        SuiteDeTesteEntity suite = suiteDeTesteRepository.findById(idSuite)
                .orElseThrow(() -> new RegraNegocioException("Suite não encontrada"));

        int quantidadeCenarios = cenarioDeTesteRepository.countCenariosBySuite(suite);

        if (possuiCenariosVinculados(quantidadeCenarios)) {
            throw new RegraNegocioException("Não é possível excluir a suíte pois existem cenários vinculados a ela.");
        }

        suiteDeTesteRepository.delete(suite);
        return "Suite de testes deletada com sucesso.";
    }

    private boolean possuiCenariosVinculados(int quantidadeCenarios) {
        return quantidadeCenarios > 0;
    }


    private SuiteDeTesteEntity getExistingSuite(Long id) {
        return suiteDeTesteRepository.findById(id)
                .orElseThrow(SuiteDeTesteNaoEncotradoException::new);
    }
}

