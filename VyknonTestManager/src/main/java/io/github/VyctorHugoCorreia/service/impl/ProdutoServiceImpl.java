package io.github.VyctorHugoCorreia.service.impl;


import io.github.VyctorHugoCorreia.domain.entity.ProdutoEntity;
import io.github.VyctorHugoCorreia.domain.entity.TimeEntity;
import io.github.VyctorHugoCorreia.domain.repository.ProdutoRepository;
import io.github.VyctorHugoCorreia.domain.repository.TimeRepository;
import io.github.VyctorHugoCorreia.exception.RegraNegocioException;
import io.github.VyctorHugoCorreia.rest.dto.ProdutoDTO;
import io.github.VyctorHugoCorreia.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;
    private final TimeRepository timeRepository;


    @Override
    @Transactional
    public ProdutoEntity salvar(ProdutoDTO dto) {
        Integer idTime = dto.getIdTime();
        TimeEntity time = timeRepository
                .findById(idTime)
                .orElseThrow(() -> new RegraNegocioException("Código do time inválido"));



        ProdutoEntity produto = new ProdutoEntity();
        produto.setIdTime(time);
        produto.setDescProduto(dto.getDescProduto());
        repository.save(produto);

        return produto;
    }
}
