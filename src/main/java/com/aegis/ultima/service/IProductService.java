package com.aegis.ultima.service;

import com.aegis.ultima.dto.ProductRequestDTO;
import com.aegis.ultima.model.Product;
import com.aegis.ultima.util.BaseClassDomain;

import java.util.List;

public interface IProductService {

    BaseClassDomain<ProductRequestDTO> saveProduct(ProductRequestDTO product);

    BaseClassDomain<ProductRequestDTO> editProduct(ProductRequestDTO product, boolean isEdit);

    BaseClassDomain<List<Product>> getAllProduct();

}
