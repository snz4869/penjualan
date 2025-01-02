package com.aegis.ultima.service.impl;

import com.aegis.ultima.dto.ProductRequestDTO;
import com.aegis.ultima.model.Product;
import com.aegis.ultima.repository.ProductRepository;
import com.aegis.ultima.service.IProductService;
import com.aegis.ultima.util.BaseClassDomain;
import com.aegis.ultima.util.CommonFunction;
import com.aegis.ultima.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private CommonFunction commonFunction;

    @Override
    public BaseClassDomain<ProductRequestDTO> saveProduct(ProductRequestDTO product) {
        BaseClassDomain<ProductRequestDTO> returnValue = new BaseClassDomain<ProductRequestDTO>();
        String loginUsername = commonFunction.getLoggedInUsername();
        try {
            if (product.getCode() != null){
                if (productRepository.findProductByCode(product.getCode()) != null){
                    returnValue.setResponseCode("99");
                    returnValue.setDescErrorCode("product already exist");
                    return returnValue;
                }
            } else {
                Product dtProduct = new Product();
                dtProduct.setCode(product.getCode());
                dtProduct.setName(product.getName());
                dtProduct.setStock(product.getStock());
                dtProduct.setPrice(product.getPrice());
                dtProduct.setDescription(product.getDescription());
                dtProduct.setActive(true);
                dtProduct.setCreatedAt(DateUtils.getCurrentDate());
                dtProduct.setCreatedBy(loginUsername);

                productRepository.save(dtProduct);
                returnValue.setResponseSucceed(product);
            }

        } catch (Exception e){
            returnValue.setResponseException();
        }

        return returnValue;
    }

    @Override
    public BaseClassDomain<ProductRequestDTO> editProduct(ProductRequestDTO product, boolean isEdit) {
        BaseClassDomain<ProductRequestDTO> returnValue = new BaseClassDomain<ProductRequestDTO>();
        Product dtProduct = new Product();
        String loginUsername = commonFunction.getLoggedInUsername();
        try {
            if (product.getCode() != null) {
                dtProduct = productRepository.findProductByCode(product.getCode());
                if (isEdit){
                    dtProduct.setName(product.getName());
                    dtProduct.setCode(product.getCode());
                    dtProduct.setPrice(product.getPrice());
                    dtProduct.setStock(product.getStock());
                    dtProduct.setDescription(product.getDescription());
                    if (product.getActive() != null) {
                        dtProduct.setActive(product.getActive());
                    }
                    dtProduct.setUpdatedAt(DateUtils.getCurrentDate());
                    dtProduct.setUpdatedBy(loginUsername);
                } else {
                    dtProduct.setActive(false);
                    dtProduct.setDeletedAt(DateUtils.getCurrentDate());
                    dtProduct.setDeletedBy(loginUsername);
                }
                productRepository.save(dtProduct);
                returnValue.setResponseSucceed(product);
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
