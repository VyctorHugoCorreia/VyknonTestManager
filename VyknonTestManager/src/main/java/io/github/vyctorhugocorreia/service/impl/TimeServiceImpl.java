package io.github.vyctorhugocorreia.service.impl;

import io.github.vyctorhugocorreia.entity.TimeEntity;
import io.github.vyctorhugocorreia.repository.ProdutoRepository;
import io.github.vyctorhugocorreia.repository.TimeRepository;
import io.github.vyctorhugocorreia.exception.RegraNegocioException;
import io.github.vyctorhugocorreia.exception.TimeNaoEncontradoException;
import io.github.vyctorhugocorreia.dto.TimeDTO;
import io.github.vyctorhugocorreia.service.TimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class TimeServiceImpl implements TimeService {

    private final TimeRepository timeRepository;
    private final ProdutoRepository produtoRepository;

    @Override
    @Transactional
    public TimeEntity salvar(TimeDTO dto) {
        String nomeTime = dto.getNomeTime();
        if (dto.getNomeTime().trim().isEmpty()) {
            throw new RegraNegocioException("Preencha um nome válido");
        }
        if (dto.getNomeTime().trim().length() > 100) {
            throw new RegraNegocioException("O nome do time deve ter no máximo 100 caracteres");
        }
        validarSeTimeJaEstaCadastrado(nomeTime);

        TimeEntity time = TimeEntity.builder()
                .nomeTime(nomeTime)
                .build();
        return timeRepository.save(time);
    }

    @Override
    @Transactional
    public TimeEntity editar(Long id, TimeDTO dto) {
        TimeEntity existingTime = obterTimePorId(id);
        String novoNome = dto.getNomeTime();
        if (novoNome.trim().isEmpty()) {
            throw new RegraNegocioException("Preencha um nome válido");
        }
        if (novoNome.trim().length() > 100) {
            throw new RegraNegocioException("O nome do time deve ter no máximo 100 caracteres");
        }
        if (!novoNome.toLowerCase(Locale.ROOT).equals(existingTime.getNomeTime().toLowerCase(Locale.ROOT))) {
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
        if (!produtoRepository.findByIdTime(time).isEmpty()) {
            throw new RegraNegocioException("Não é possível excluir o time, pois há produtos vinculados a ele.");
        }
    }
}
