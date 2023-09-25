package io.github.VyctorHugoCorreia.service.impl;

import io.github.VyctorHugoCorreia.domain.entity.PlanoDeTesteEntity;
import io.github.VyctorHugoCorreia.domain.entity.ProdutoEntity;
import io.github.VyctorHugoCorreia.domain.entity.SuiteDeTesteEntity;
import io.github.VyctorHugoCorreia.domain.entity.TimeEntity;
import io.github.VyctorHugoCorreia.domain.repository.PlanoDeTesteRepository;
import io.github.VyctorHugoCorreia.domain.repository.ProdutoRepository;
import io.github.VyctorHugoCorreia.domain.repository.SuiteDeTestesRepository;
import io.github.VyctorHugoCorreia.domain.repository.TimeRepository;
import io.github.VyctorHugoCorreia.exception.RegraNegocioException;
import io.github.VyctorHugoCorreia.rest.dto.SuiteDeTesteDTO;
import io.github.VyctorHugoCorreia.service.SuiteDeTesteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SuiteDeTesteServiceImpl implements SuiteDeTesteService {

    private final SuiteDeTestesRepository repository;
    private final PlanoDeTesteRepository planoDeTesterepository;
    private final ProdutoRepository produtoRepository;
    private final TimeRepository timeRepository;


    @Override
    @Transactional
    public SuiteDeTesteEntity salvar(SuiteDeTesteDTO dto) {
        Integer idTime = dto.getIdTime();
        Integer idProduto = dto.getIdProduto();
        Integer idPlano = dto.getIdPlano();

        TimeEntity time = timeRepository.findById(idTime)
                .orElseThrow(() -> new RegraNegocioException("Código do time inválido"));

        ProdutoEntity produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RegraNegocioException("Código do produto inválido"));

        PlanoDeTesteEntity plano = planoDeTesterepository.findById(idPlano)
                .orElseThrow(() -> new RegraNegocioException("Código do plano inválido"));


        SuiteDeTesteEntity suiteDeTeste = new SuiteDeTesteEntity();
        suiteDeTeste.setIdTime(time);
        suiteDeTeste.setIdProduto(produto);
        suiteDeTeste.setIdPlano(plano);
        suiteDeTeste.setDescSuite(dto.getDescSuite());

        repository.save(suiteDeTeste);

        return suiteDeTeste;
    }

}
