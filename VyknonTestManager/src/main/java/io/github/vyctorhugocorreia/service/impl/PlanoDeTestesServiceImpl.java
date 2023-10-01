package io.github.vyctorhugocorreia.service.impl;


import io.github.vyctorhugocorreia.dto.PlanoDeTestesDTO;
import io.github.vyctorhugocorreia.entity.PlanoDeTesteEntity;
import io.github.vyctorhugocorreia.entity.ProdutoEntity;
import io.github.vyctorhugocorreia.entity.TimeEntity;
import io.github.vyctorhugocorreia.exception.*;
import io.github.vyctorhugocorreia.repository.PlanoDeTestesRepository;
import io.github.vyctorhugocorreia.repository.ProdutoRepository;
import io.github.vyctorhugocorreia.repository.TimeRepository;
import io.github.vyctorhugocorreia.service.PlanoDeTestesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PlanoDeTestesServiceImpl implements PlanoDeTestesService {

    private final ProdutoRepository produtoRepository;
    private final TimeRepository timeRepository;

    private final PlanoDeTestesRepository planoDeTestesRepository;

    @Override
    @Transactional
    public PlanoDeTesteEntity salvar(PlanoDeTestesDTO dto) {
        Long idTime = dto.getIdTime();
        TimeEntity time = timeRepository
                .findById(idTime.intValue())
                .orElseThrow(TimeNaoEncontradoException::new);

        Long idProduto = dto.getIdTproduto();
        ProdutoEntity produto = produtoRepository
                .findById(idProduto.intValue())
                .filter(p -> p.getIdTime().getIdTime().equals(idTime))
                .orElseThrow(ProdutoNaoEncontradoException::new);


        String descPlano = dto.getDescPlano();

        validarSePlanoExisteParaProduto(descPlano, produto);

        PlanoDeTesteEntity plano = PlanoDeTesteEntity.builder()
                .descPlano(descPlano)
                .idTime(time)
                .idTproduto(produto)
                .build();
        return planoDeTestesRepository.save(plano);
    }


    public PlanoDeTesteEntity editar(Long planoId, PlanoDeTestesDTO dto) {
        PlanoDeTesteEntity planoExistente = planoDeTestesRepository
                .findById(planoId.intValue())
                .orElseThrow(PlanoDeTestesNaoEncontradaException::new);

        Long idTime = dto.getIdTime();
        TimeEntity time = timeRepository
                .findById(idTime.intValue())
                .orElseThrow(TimeNaoEncontradoException::new);

        Long idProduto = dto.getIdTproduto();
        ProdutoEntity produto = produtoRepository
                .findById(idProduto.intValue())
                .filter(p -> p.getIdTime().getIdTime().equals(idTime))
                .orElseThrow(ProdutoNaoEncontradoException::new);

        String descPlano = dto.getDescPlano();

        validarSePlanoExisteParaProdutoEOutras(descPlano, produto, planoExistente);

        // Atualize os atributos do planoExistente com os novos valores
        planoExistente.setDescPlano(dto.getDescPlano());
        planoExistente.setIdTime(time);
        planoExistente.setIdTproduto(produto);


        return planoDeTestesRepository.save(planoExistente);
    }

    @Override
    @Transactional
    public String deletar(Long id) {
        planoDeTestesRepository.delete(getExistingPlano(id));
        return "Plano de testes deletado com sucesso.";
    }

    private PlanoDeTesteEntity getExistingPlano(Long id) {
        return planoDeTestesRepository.findById(id.intValue())
                .orElseThrow(PlanoDeTestesNaoEncontradaException::new);
    }


    void validarSePlanoExisteParaProduto(String descPlano, ProdutoEntity produto) {
        if (planoDeTestesRepository.existsByDescPlanoAndIdTproduto(descPlano, produto)) {
            throw new RegraNegocioException("Já existe um plano com o mesmo nome para este produto.");
        }
    }

    void validarSePlanoExisteParaProdutoEOutras(String descPlano, ProdutoEntity produto, PlanoDeTesteEntity planoDeTestesExistente) {
        if (planoDeTestesRepository.existsByDescPlanoAndIdTprodutoAndIdPlanoNot(descPlano, produto, planoDeTestesExistente.getIdPlano())) {
            throw new RegraNegocioException("Já existe um plano de testes com o mesmo nome para este produto.");
        }
    }
}
