package com.aegis.ultima.service;

import com.aegis.ultima.model.Product;
import com.aegis.ultima.util.BaseClassDomain;

import java.util.List;

public interface IProductService {

    BaseClassDomain<Product> saveProduct(Product product);

    BaseClassDomain<Product> editProduct(Product product, boolean isEdit);

    BaseClassDomain<List<Product>> getAllProduct();

}
