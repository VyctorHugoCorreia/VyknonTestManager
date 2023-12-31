package io.github.vyctorhugocorreia.controller;

import io.github.vyctorhugocorreia.entity.StatusAutomatizadoEntity;
import io.github.vyctorhugocorreia.entity.TimeEntity;
import io.github.vyctorhugocorreia.entity.TipoCenarioEntity;
import io.github.vyctorhugocorreia.entity.TipoPlataformaEntity;
import io.github.vyctorhugocorreia.repository.CenarioDeTesteRepository;
import io.github.vyctorhugocorreia.repository.StatusAutomatizadoRepository;
import io.github.vyctorhugocorreia.repository.TipoPlataformaRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/tipoPlataforma")
@AllArgsConstructor
@CrossOrigin
public class TipoPlataformaController {

    final TipoPlataformaRepository repository;
     final CenarioDeTesteRepository cenarioDeTesteRepository;


    @GetMapping
    public List<TipoPlataformaEntity> getPlataforma(@RequestParam(required = false) TimeEntity idTime
    ) {

        List<TipoPlataformaEntity> tipoPlataformaEntityList = repository.findAll();

        for (TipoPlataformaEntity tipoPlataforma : tipoPlataformaEntityList) {
            int quantidadeCenarios = cenarioDeTesteRepository.countCenariosByPlataformType(tipoPlataforma,idTime);
            tipoPlataforma.setQuantidadeCenarios(quantidadeCenarios);
        }

        return repository.findAll();
    }



}
