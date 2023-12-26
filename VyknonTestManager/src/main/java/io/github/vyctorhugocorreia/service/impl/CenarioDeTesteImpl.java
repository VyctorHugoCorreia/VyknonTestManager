package io.github.vyctorhugocorreia.service.impl;


import io.github.vyctorhugocorreia.dto.CenarioDeTesteDTO;
import io.github.vyctorhugocorreia.dto.PlanoDeTestesDTO;
import io.github.vyctorhugocorreia.dto.StepDTO;
import io.github.vyctorhugocorreia.entity.*;
import io.github.vyctorhugocorreia.exception.*;
import io.github.vyctorhugocorreia.repository.*;
import io.github.vyctorhugocorreia.service.CenarioDeTesteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CenarioDeTesteImpl implements CenarioDeTesteService {

    private final TimeRepository timeRepository;

    private final PlanoDeTestesRepository planoDeTestesRepository;

    private final SuiteDeTesteRepository suiteDeTesteRepository;

    private final ProdutoRepository produtoRepository;

    private final FuncionalidadeRepository funcionalidadeRepository;

    private final TipoCenarioRepository tipoCenarioRepository;

    private final TipoPlataformaRepository tipoPlataformaRepository;

    private final StatusCenarioRepository statusCenarioRepository;

    private final StatusAutomatizadoRepository statusAutomatizadoRepository;

    private final CenarioDeTesteRepository cenarioDeTesteRepository;

    @Override
    @Transactional
    public CenarioDeTesteEntity salvar(CenarioDeTesteDTO dto) {
        String tituloCenario = dto.getTituloCenario();
        String descCenario = dto.getDescCenario();
        String linkCenario = dto.getLinkCenario();
        List<StepDTO> stepsList = dto.getSteps();
        List<String> tagsList = dto.getTags();

        Long idTime = dto.getIdTime();
        Long idPlano = dto.getIdPlano();
        Long idSuite = dto.getIdSuite();
        Long idTproduto = dto.getIdTproduto();
        Long idFuncionalidade = dto.getIdFuncionalidade();
        Long idTpcenario = dto.getIdTpcenario();
        Long idPlataforma = dto.getIdPlataforma();
        Long idStatus = dto.getIdStatus();
        Long idAutomatizado = dto.getIdAutomatizado();


        TimeEntity time = timeRepository
                .findById(idTime.intValue())
                .orElseThrow(TimeNaoEncontradoException::new);

        PlanoDeTesteEntity plano = planoDeTestesRepository
                .findById(idPlano.intValue())
                .orElseThrow(PlanoDeTestesNaoEncontradaException::new);

        SuiteDeTesteEntity suite = suiteDeTesteRepository
                .findById(idSuite)
                .orElseThrow(SuiteDeTesteNaoEncotradoException::new);

        ProdutoEntity produto = produtoRepository
                .findById(idTproduto.intValue())
                .orElseThrow(ProdutoNaoEncontradoException::new);

        FuncionalidadeEntity funcionalidade = funcionalidadeRepository
                .findById(idFuncionalidade.intValue())
                .orElseThrow(FuncionalidadeNaoEncontradaException::new);

        TipoCenarioEntity tipoCenario = tipoCenarioRepository
                .findById(idTpcenario.intValue())
                .orElseThrow(() -> new RegraNegocioException("Tipo de cenário não encontrado"));

        TipoPlataformaEntity tipoPlataforma = tipoPlataformaRepository
                .findById(idPlataforma.intValue())
                .orElseThrow(() -> new RegraNegocioException("Plataforma não encontrada"));

        StatusCenarioEntity statusCenario = statusCenarioRepository
                .findById(idStatus.intValue())
                .orElseThrow(() -> new RegraNegocioException("Status do cenário não encontrada"));

        StatusAutomatizadoEntity statusAutomatizado = statusAutomatizadoRepository
                .findById(idAutomatizado.intValue())
                .orElseThrow(() -> new RegraNegocioException("Status automatizado não encontrada"));

CenarioDeTesteEntity cenario = CenarioDeTesteEntity.builder()
        .tituloCenario(tituloCenario)
        .descCenario(descCenario)
        .linkCenario(linkCenario)
        .idTime(time)
        .idPlano(plano)
        .idSuite(suite)
        .idTproduto(produto)
        .idFuncionalidade(funcionalidade)
        .idTpcenario(tipoCenario)
        .idPlataforma(tipoPlataforma)
        .idStatus(statusCenario)
        .idAutomatizado(statusAutomatizado)
        .steps(stepsList)
        .tags(tagsList)
        .build();

        return cenarioDeTesteRepository.save(cenario);
    }

    @Override
    @Transactional
    public CenarioDeTesteEntity editar(Long id, CenarioDeTesteDTO dto) {
        String tituloCenario = dto.getTituloCenario();
        String descCenario = dto.getDescCenario();
        String linkCenario = dto.getLinkCenario();
        List<StepDTO> stepsList = dto.getSteps();
        List<String> tagsList = dto.getTags();

        Long idTime = dto.getIdTime();
        Long idPlano = dto.getIdPlano();
        Long idSuite = dto.getIdSuite();
        Long idTproduto = dto.getIdTproduto();
        Long idFuncionalidade = dto.getIdFuncionalidade();
        Long idTpcenario = dto.getIdTpcenario();
        Long idPlataforma = dto.getIdPlataforma();
        Long idStatus = dto.getIdStatus();
        Long idAutomatizado = dto.getIdAutomatizado();


        CenarioDeTesteEntity cenarioExistente = cenarioDeTesteRepository
                .findById(id.intValue())
                .orElseThrow(() -> new RegraNegocioException("Cenário não encontrado"));

        TimeEntity time = timeRepository
                .findById(idTime.intValue())
                .orElseThrow(TimeNaoEncontradoException::new);

        PlanoDeTesteEntity plano = planoDeTestesRepository
                .findById(idPlano.intValue())
                .orElseThrow(PlanoDeTestesNaoEncontradaException::new);

        SuiteDeTesteEntity suite = suiteDeTesteRepository
                .findById(idSuite)
                .orElseThrow(SuiteDeTesteNaoEncotradoException::new);

        ProdutoEntity produto = produtoRepository
                .findById(idTproduto.intValue())
                .orElseThrow(ProdutoNaoEncontradoException::new);

        FuncionalidadeEntity funcionalidade = funcionalidadeRepository
                .findById(idFuncionalidade.intValue())
                .orElseThrow(FuncionalidadeNaoEncontradaException::new);

        TipoCenarioEntity tipoCenario = tipoCenarioRepository
                .findById(idTpcenario.intValue())
                .orElseThrow(() -> new RegraNegocioException("Tipo de cenário não encontrado"));

        TipoPlataformaEntity tipoPlataforma = tipoPlataformaRepository
                .findById(idPlataforma.intValue())
                .orElseThrow(() -> new RegraNegocioException("Plataforma não encontrada"));

        StatusCenarioEntity statusCenario = statusCenarioRepository
                .findById(idStatus.intValue())
                .orElseThrow(() -> new RegraNegocioException("Status do cenário não encontrada"));

        StatusAutomatizadoEntity statusAutomatizado = statusAutomatizadoRepository
                .findById(idAutomatizado.intValue())
                .orElseThrow(() -> new RegraNegocioException("Status automatizado não encontrada"));

        // Atualize todos os campos do cenário existente com base no DTO recebido
        cenarioExistente.setTituloCenario(tituloCenario);
        cenarioExistente.setDescCenario(descCenario);
        cenarioExistente.setLinkCenario(linkCenario);
        cenarioExistente.setSteps(stepsList);
        cenarioExistente.setTags(tagsList);
        cenarioExistente.setIdTime(time);
        cenarioExistente.setIdPlano(plano);
        cenarioExistente.setIdSuite(suite);
        cenarioExistente.setIdTproduto(produto);
        cenarioExistente.setIdFuncionalidade(funcionalidade);
        cenarioExistente.setIdTpcenario(tipoCenario);
        cenarioExistente.setIdPlataforma(tipoPlataforma);
        cenarioExistente.setIdStatus(statusCenario);
        cenarioExistente.setIdAutomatizado(statusAutomatizado);

        return cenarioDeTesteRepository.save(cenarioExistente);
    }



    @Override
    @Transactional
    public String deletar(Long id) {
        CenarioDeTesteEntity cenario = cenarioDeTesteRepository.findById(id.intValue())
                .orElseThrow(() -> new RegraNegocioException("Cenário não encontrado"));

        cenarioDeTesteRepository.delete(cenario);

        return "Cenário deletado com sucesso.";
    }



}
