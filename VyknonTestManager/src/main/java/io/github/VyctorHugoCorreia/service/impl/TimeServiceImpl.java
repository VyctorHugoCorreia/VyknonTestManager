package io.github.VyctorHugoCorreia.service.impl;

import io.github.VyctorHugoCorreia.domain.entity.ProdutoEntity;
import io.github.VyctorHugoCorreia.domain.entity.TimeEntity;
import io.github.VyctorHugoCorreia.domain.repository.ProdutoRepository;
import io.github.VyctorHugoCorreia.domain.repository.TimeRepository;
import io.github.VyctorHugoCorreia.exception.RegraNegocioException;
import io.github.VyctorHugoCorreia.exception.TimeNaoEncontradoException;
import io.github.VyctorHugoCorreia.rest.dto.TimeDTO;
import io.github.VyctorHugoCorreia.service.TimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeServiceImpl implements TimeService {

    private final TimeRepository timeRepository;
    private final ProdutoRepository produtoRepository;

    @Override
    @Transactional
    public TimeEntity salvar(TimeDTO dto) {
        String nomeTime = dto.getNomeTime();
        validarSeTimeJaEstaCadastrado(nomeTime);

        TimeEntity time = new TimeEntity();
        time.setNomeTime(nomeTime);

        return timeRepository.save(time);
    }

    @Override
    @Transactional
    public TimeEntity editar(Long id, TimeDTO dto) {
        TimeEntity existingTime = obterTimePorId(id);
        String novoNome = dto.getNomeTime();

        if (!novoNome.equals(existingTime.getNomeTime())) {
            validarSeTimeJaEstaCadastrado(novoNome);
        }

        existingTime.setNomeTime(novoNome);

        return timeRepository.save(existingTime);
    }

    @Override
    @Transactional
    public String deletar(Long id) {
        TimeEntity time = obterTimePorId(id);
        verificarProdutosVinculados(time);

        timeRepository.delete(time);

        return "Time deletado com sucesso.";
    }

    private TimeEntity obterTimePorId(Long id) {
        return timeRepository.findById(id.intValue())
                .orElseThrow(TimeNaoEncontradoException::new);
    }

    private void validarSeTimeJaEstaCadastrado(String nomeTime) {
        if (timeRepository.existsByNomeTime(nomeTime)) {
            throw new RegraNegocioException("Já existe um time com o mesmo nome.");
        }
    }

    private void verificarProdutosVinculados(TimeEntity time) {
        List<ProdutoEntity> produtosDoTime = produtoRepository.findByIdTime(time);
        if (!produtosDoTime.isEmpty()) {
            throw new RegraNegocioException("Não é possível excluir o time, pois há produtos vinculados a ele.");
        }
    }
}
