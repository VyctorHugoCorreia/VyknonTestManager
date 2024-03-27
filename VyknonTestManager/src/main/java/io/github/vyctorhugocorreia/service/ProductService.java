package io.github.vyctorhugocorreia.service;

import io.github.vyctorhugocorreia.entity.ProductEntity;
import io.github.vyctorhugocorreia.dto.ProductDTO;

public interface ProductService {

    ProductEntity save(ProductDTO dto);

    ProductEntity edit(Long id, ProductDTO dto);


    String delete(Long id);


}
