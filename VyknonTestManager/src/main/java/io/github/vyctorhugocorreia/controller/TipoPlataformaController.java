package io.github.vyctorhugocorreia.controller;

import io.github.vyctorhugocorreia.entity.TeamEntity;
import io.github.vyctorhugocorreia.entity.PlatformTypeEntity;
import io.github.vyctorhugocorreia.repository.CenarioDeTesteRepository;
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
    public List<PlatformTypeEntity> getPlataforma(@RequestParam(required = false) TeamEntity idTime
    ) {

        List<PlatformTypeEntity> tipoPlataformaEntityList = repository.findAll();

        for (PlatformTypeEntity tipoPlataforma : tipoPlataformaEntityList) {
            int quantidadeCenarios = cenarioDeTesteRepository.countCenariosByPlataformType(tipoPlataforma,idTime);
            tipoPlataforma.setQuantidadeCenarios(quantidadeCenarios);
        }

        return repository.findAll();
    }



}
