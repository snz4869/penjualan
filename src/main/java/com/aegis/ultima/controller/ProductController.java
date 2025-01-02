package com.aegis.ultima.controller;

import com.aegis.ultima.dto.ProductRequestDTO;
import com.aegis.ultima.model.Product;
import com.aegis.ultima.service.IProductService;
import com.aegis.ultima.util.BaseClassDomain;
import com.aegis.ultima.util.CommonFunction;
//import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductService productService;

    @Autowired
    private CommonFunction commonFunction;

    private final static Logger logger = LogManager.getLogger(ProductController.class);

//    Gson json = new Gson();

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public BaseClassDomain<ProductRequestDTO> addProduct(@RequestBody ProductRequestDTO product){
        logger.info("----Request product/add ---->");
        logger.info("add request::" + commonFunction.convertJSONtoString(product));

        BaseClassDomain<ProductRequestDTO> objReturn = new BaseClassDomain<ProductRequestDTO>();
        objReturn = productService.saveProduct(product);

        logger.info("----Response product/add----");
        logger.info("add response::" + commonFunction.convertJSONtoString(objReturn));
        return objReturn;
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    public BaseClassDomain<ProductRequestDTO> editProduct(@RequestBody ProductRequestDTO product){
        logger.info("----Request product/edit ---->");
        logger.info("edit request::" + commonFunction.convertJSONtoString(product));

        BaseClassDomain<ProductRequestDTO> objReturn = new BaseClassDomain<ProductRequestDTO>();
        objReturn = productService.editProduct(product, true);

        logger.info("----Response product/edit----");
        logger.info("edit response::" + commonFunction.convertJSONtoString(objReturn));
        return objReturn;
    }

    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public BaseClassDomain<ProductRequestDTO> deleteProduct(@RequestBody ProductRequestDTO product){
        logger.info("----Request product/delete ---->");
        logger.info("delete request::" + commonFunction.convertJSONtoString(product));

        BaseClassDomain<ProductRequestDTO> objReturn = new BaseClassDomain<ProductRequestDTO>();
        objReturn = productService.editProduct(product, false);

        logger.info("----Response product/delete----");
        logger.info("delete response::" + commonFunction.convertJSONtoString(objReturn));
        return objReturn;
    }

    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    public BaseClassDomain<List<Product>> getProduct(){
        logger.info("----Request product/getAll ---->");

        BaseClassDomain<List<Product>> objReturn = new BaseClassDomain<List<Product>>();
        objReturn = productService.getAllProduct();

        logger.info("----Response product/getAll----");
        logger.info("getAll response::" + commonFunction.convertJSONtoString(objReturn));
        return objReturn;
    }
}
