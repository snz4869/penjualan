package com.aegis.ultima.controller;

import com.aegis.ultima.model.Product;
import com.aegis.ultima.model.User;
import com.aegis.ultima.model.UserDto;
import com.aegis.ultima.service.IProductService;
import com.aegis.ultima.util.BaseClassDomain;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductService productService;

    private final static Logger logger = LogManager.getLogger(ProductController.class);

    Gson json = new Gson();

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public BaseClassDomain<Product> addProduct(@RequestBody Product product){
        logger.info("----Request product/add ---->");
        logger.info(json.toJson(product));

        BaseClassDomain<Product> objReturn = new BaseClassDomain<Product>();
        objReturn = productService.saveProduct(product);

        logger.info("----Response product/add----");
        logger.info(json.toJson(objReturn));
        return objReturn;
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    public BaseClassDomain<Product> editProduct(@RequestBody Product product){
        logger.info("----Request product/edit ---->");
        logger.info(json.toJson(product));

        BaseClassDomain<Product> objReturn = new BaseClassDomain<Product>();
        objReturn = productService.editProduct(product, true);

        logger.info("----Response product/edit----");
        logger.info(json.toJson(objReturn));
        return objReturn;
    }

    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public BaseClassDomain<Product> deleteProduct(@RequestBody Product product){
        logger.info("----Request product/delete ---->");
        logger.info(json.toJson(product));

        BaseClassDomain<Product> objReturn = new BaseClassDomain<Product>();
        objReturn = productService.editProduct(product, false);

        logger.info("----Response product/delete----");
        logger.info(json.toJson(objReturn));
        return objReturn;
    }

    @RequestMapping(value="/getAll", method = RequestMethod.POST)
    public BaseClassDomain<List<Product>> getProduct(){
        logger.info("----Request product/getAll ---->");

        BaseClassDomain<List<Product>> objReturn = new BaseClassDomain<List<Product>>();
        objReturn = productService.getAllProduct();

        logger.info("----Response product/getAll----");
        logger.info(json.toJson(objReturn));
        return objReturn;
    }
}
