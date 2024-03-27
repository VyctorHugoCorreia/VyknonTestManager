package io.github.vyctorhugocorreia.service;

import io.github.vyctorhugocorreia.dto.AccessProfileDTO;
import io.github.vyctorhugocorreia.entity.AccessProfileEntity;

public interface AccessProfileService {

    AccessProfileEntity save(AccessProfileDTO dto);

}
