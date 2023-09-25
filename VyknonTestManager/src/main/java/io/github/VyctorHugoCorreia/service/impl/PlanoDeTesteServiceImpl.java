package io.github.VyctorHugoCorreia.service.impl;

import io.github.VyctorHugoCorreia.domain.entity.PlanoDeTesteEntity;
import io.github.VyctorHugoCorreia.domain.entity.ProdutoEntity;
import io.github.VyctorHugoCorreia.domain.entity.TimeEntity;
import io.github.VyctorHugoCorreia.domain.repository.PlanoDeTesteRepository;
import io.github.VyctorHugoCorreia.domain.repository.ProdutoRepository;
import io.github.VyctorHugoCorreia.domain.repository.TimeRepository;
import io.github.VyctorHugoCorreia.exception.RegraNegocioException;
import io.github.VyctorHugoCorreia.rest.dto.PlanoDeTesteDTO;
import io.github.VyctorHugoCorreia.service.PlanoDeTesteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlanoDeTesteServiceImpl implements PlanoDeTesteService {

    private final PlanoDeTesteRepository repository;
    private final ProdutoRepository produtoRepository;
    private final TimeRepository timeRepository;


    @Override
    @Transactional
    public PlanoDeTesteEntity salvar(PlanoDeTesteDTO dto) {
        Integer idTime = dto.getIdTime();
        Integer idProduto = dto.getIdProduto();

        TimeEntity time = timeRepository.findById(idTime)
                .orElseThrow(() -> new RegraNegocioException("Código do time inválido"));

        ProdutoEntity produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RegraNegocioException("Código do produto inválido"));

        validarSeTimeEstaCorreto(produto, time);

        // Crie uma nova instância de PlanoDeTesteEntity
        PlanoDeTesteEntity planoDeTeste = new PlanoDeTesteEntity();
        planoDeTeste.setIdTime(time);
        planoDeTeste.setIdProduto(produto);
        planoDeTeste.setDescPlano(dto.getDescPlano());

        // Salve o PlanoDeTesteEntity no repositório
        repository.save(planoDeTeste);

        return planoDeTeste;
    }

    public void validarSeTimeEstaCorreto(ProdutoEntity produto, TimeEntity time) {
        if (!produto.getIdTime().equals(time)) {
            throw new RegraNegocioException("Time não compativel com o produto");
        }

    }


}
