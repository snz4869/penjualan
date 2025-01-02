package com.aegis.ultima.controller;

import com.aegis.ultima.dto.UserRequestDTO;
import com.aegis.ultima.security.TokenProvider;
import com.aegis.ultima.service.impl.UserService;
import com.aegis.ultima.util.BaseClassDomain;
import com.aegis.ultima.util.CommonFunction;
//import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private CommonFunction commonFunction;

    private final static Logger logger = LogManager.getLogger(ProductController.class);

//    Gson json = new Gson();

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public BaseClassDomain<UserRequestDTO> saveUser(@RequestBody UserRequestDTO user){
        logger.info("----Request users/register ---->");
        logger.info("register request::" + commonFunction.convertJSONtoString(user));

        BaseClassDomain<UserRequestDTO> objReturn = new BaseClassDomain<UserRequestDTO>();
        objReturn = userService.save(user);

        logger.info("----Response product/register----");
        logger.info("register response::" + commonFunction.convertJSONtoString(objReturn));

        return objReturn;

    }

    @RequestMapping(value="/active", method = RequestMethod.POST)
    public BaseClassDomain<UserRequestDTO> userActivation(@RequestBody UserRequestDTO user){
        logger.info("----Request users/active ---->");
        logger.info("active request::" + commonFunction.convertJSONtoString(user));

        BaseClassDomain<UserRequestDTO> objReturn = new BaseClassDomain<UserRequestDTO>();
        objReturn = userService.activateUser(user);

        logger.info("----Response product/active----");
        logger.info("active response::" + commonFunction.convertJSONtoString(objReturn));

        return objReturn;
    }

}
