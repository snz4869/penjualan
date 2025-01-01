package com.aegis.ultima.service.impl;

import com.aegis.ultima.model.Product;
import com.aegis.ultima.model.User;
import com.aegis.ultima.model.UserDto;
import com.aegis.ultima.repository.ProductRepository;
import com.aegis.ultima.service.IProductService;
import com.aegis.ultima.util.BaseClassDomain;
import com.aegis.ultima.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public BaseClassDomain<Product> saveProduct(Product product) {
        BaseClassDomain<Product> returnValue = new BaseClassDomain<Product>();

        try {
            if (product.getId() != null){
                if (productRepository.findProductById(product.getId()) != null ||
                        productRepository.findProductByCode(product.getCode()) != null){
                    returnValue.setResponseCode("99");
                    returnValue.setDescErrorCode("product already exist");
                    return returnValue;
                }
            } else {
                product.setActive(true);
                product.setCreatedAt(DateUtils.getCurrentDate());
                product.setCreatedBy("admin");

                productRepository.save(product);
                returnValue.setResponseSucceed(product);
            }

        } catch (Exception e){
            returnValue.setResponseException();
        }

        return returnValue;
    }

    @Override
    public BaseClassDomain<Product> editProduct(Product product, boolean isEdit) {
        BaseClassDomain<Product> returnValue = new BaseClassDomain<Product>();
        Product dtProduct = new Product();

        try {
            if (product.getId() != null) {
                dtProduct = productRepository.findProductById(product.getId());
                if (isEdit){
                    dtProduct.setName(product.getName());
                    dtProduct.setPrice(product.getPrice());
                    dtProduct.setStock(product.getStock());
                    dtProduct.setDescription(product.getDescription());
                    dtProduct.setActive(product.getActive());
                    dtProduct.setUpdatedAt(DateUtils.getCurrentDate());
                    dtProduct.setUpdatedBy("admin");
                } else {
                    dtProduct.setActive(false);
                    dtProduct.setDeletedAt(DateUtils.getCurrentDate());
                    dtProduct.setDeletedBy("admin");
                }
                productRepository.save(dtProduct);
                returnValue.setResponseSucceed(dtProduct);
            } else {
                returnValue.setResponseCode("99");
                returnValue.setDescErrorCode("product not exist");
            }

        } catch (Exception e){
            returnValue.setResponseException();
        }

        return returnValue;
    }

    @Override
    public BaseClassDomain<List<Product>> getAllProduct(){
        BaseClassDomain<List<Product>> returnValue = new BaseClassDomain<List<Product>>();
        try{
            returnValue.setResponseSucceed(productRepository.findAll());
        } catch (Exception e){
            returnValue.setResponseException();
        }
        return returnValue;
    }
}
