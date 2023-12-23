package io.github.vyctorhugocorreia.service.impl;

import io.github.vyctorhugocorreia.dto.TagCenarioDTO;
import io.github.vyctorhugocorreia.entity.CenarioDeTesteEntity;
import io.github.vyctorhugocorreia.entity.TagCenarioEntity;
import io.github.vyctorhugocorreia.entity.TimeEntity;
import io.github.vyctorhugocorreia.exception.RegraNegocioException;
import io.github.vyctorhugocorreia.repository.CenarioDeTesteRepository;
import io.github.vyctorhugocorreia.repository.TagCenarioRepository;
import io.github.vyctorhugocorreia.service.TagCenarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagCenarioServiceImpl implements TagCenarioService {

    private final TagCenarioRepository tagCenarioRepository;
    private final CenarioDeTesteRepository cenarioDeTesteRepository;

    @Override
    @Transactional
    public TagCenarioEntity salvar(TagCenarioDTO dto) {
        validarCenarioExistente(dto.getIdCenario());
        validarTagCenario(dto.getDescTag(), dto.getIdCenario());
        return tagCenarioRepository.save(TagCenarioEntity.builder()
                .idCenario(getCenarioById(dto.getIdCenario()))
                .descTag(dto.getDescTag())
                .build());
    }

    @Override
    public TagCenarioEntity editar(Long id, TagCenarioDTO dto) {
        TagCenarioEntity existingTag = getExistingTag(id);
        validarCenarioExistente(dto.getIdCenario());
        String novoTagDesc = dto.getDescTag().trim();
        validarDescricaoTag(novoTagDesc);
        if (!existingTag.getDescTag().equalsIgnoreCase(novoTagDesc)) {
            validarExistenciaTagParaCenario(novoTagDesc, dto.getIdCenario(), existingTag.getIdTag());
            existingTag.setDescTag(novoTagDesc);
            return tagCenarioRepository.save(existingTag);
        }
        return existingTag;
    }

    @Override
    @Transactional
    public String deletar(Long id) {
        TagCenarioEntity tag = getExistingTag(id);

        tagCenarioRepository.delete(tag);

        return "tag deletada com sucesso.";
    }

    private CenarioDeTesteEntity getCenarioById(Long idCenario) {
        return cenarioDeTesteRepository.findById(idCenario.intValue())
                .orElseThrow(() -> new RegraNegocioException("Cenário não encontrado"));
    }

    private void validarCenarioExistente(Long idCenario) {
        getCenarioById(idCenario);
    }

    private void validarTagCenario(String descTag, Long idCenario) {
        if (descTag.trim().isEmpty()) {
            throw new RegraNegocioException("Preencha um nome válido");
        }
        if (descTag.trim().length() > 100) {
            throw new RegraNegocioException("O nome da tag deve ter no máximo 100 caracteres");
        }
        if (tagCenarioRepository.existsByDescTagAndIdCenario(descTag, getCenarioById(idCenario))) {
            throw new RegraNegocioException("Já existe uma tag com o mesmo nome para este cenário.");
        }
    }

    private TagCenarioEntity getExistingTag(Long id) {
        return tagCenarioRepository.findById(id.intValue())
                .orElseThrow(() -> new RegraNegocioException("Tag não encontrada"));
    }

    private void validarDescricaoTag(String descTag) {
        if (descTag.isEmpty()) {
            throw new RegraNegocioException("Preencha um nome válido");
        }
        if (descTag.length() > 100) {
            throw new RegraNegocioException("A tag deve ter no máximo 100 caracteres");
        }
    }

    private void validarExistenciaTagParaCenario(String descTag, Long idCenario, Long tagId) {
        TagCenarioEntity existingCenarioIdWithTagDesc = tagCenarioRepository.findIdCenarioByDescTagIgnoreCase(descTag);
        if (existingCenarioIdWithTagDesc != null &&
                existingCenarioIdWithTagDesc.getIdCenario().getIdCenario() == idCenario &&
                !existingCenarioIdWithTagDesc.getIdTag().equals(tagId.intValue())) {
            throw new RegraNegocioException("Já existe uma tag com o mesmo nome para este cenário.");
        }
    }

}
