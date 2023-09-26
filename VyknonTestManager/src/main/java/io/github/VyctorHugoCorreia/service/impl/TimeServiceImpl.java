package io.github.VyctorHugoCorreia.service.impl;


import io.github.VyctorHugoCorreia.domain.entity.TimeEntity;
import io.github.VyctorHugoCorreia.domain.repository.TimeRepository;
import io.github.VyctorHugoCorreia.exception.RegraNegocioException;
import io.github.VyctorHugoCorreia.exception.TimeNaoEncontradoException;
import io.github.VyctorHugoCorreia.rest.dto.TimeDTO;
import io.github.VyctorHugoCorreia.service.TimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class TimeServiceImpl implements TimeService {

    private final TimeRepository timeRepository;


    @Override
    @Transactional
    public TimeEntity salvar(TimeDTO dto) {
        TimeEntity time = new TimeEntity();
        String nomeTime = dto.getNomeTime();
        validarSeTimeJaEstaCadastrado(nomeTime);
        time.setNomeTime(nomeTime);

        return timeRepository.save(time);
    }

    @Override
    @Transactional
    public TimeEntity editar(Long id, TimeDTO dto) {
        TimeEntity existingTime = timeRepository.findById(id.intValue())
                .orElseThrow(TimeNaoEncontradoException::new);

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
        TimeEntity time = timeRepository.findById(id.intValue())
                .orElseThrow(TimeNaoEncontradoException::new);

        timeRepository.delete(time);

        return "Time deletado com sucesso.";
    }

    private void validarSeTimeJaEstaCadastrado(String nomeTime) {
        if (timeRepository.existsByNomeTime(nomeTime)) {
            throw new RegraNegocioException("Já existe um time com o mesmo nome.");
        }
    }

}
