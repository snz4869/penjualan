package com.aegis.ultima.controller;

import com.aegis.ultima.model.*;
import com.aegis.ultima.security.TokenProvider;
import com.aegis.ultima.service.impl.UserService;
import com.aegis.ultima.util.BaseClassDomain;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    private final static Logger logger = LogManager.getLogger(ProductController.class);

    Gson json = new Gson();

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public BaseClassDomain<UserDto> saveUser(@RequestBody UserDto user){
        logger.info("----Request users/register ---->");
        logger.info(json.toJson(user));

        BaseClassDomain<UserDto> objReturn = new BaseClassDomain<UserDto>();
        objReturn = userService.save(user);

        logger.info("----Response product/register----");
        logger.info(json.toJson(objReturn));

        return objReturn;

    }

    @RequestMapping(value="/active", method = RequestMethod.POST)
    public BaseClassDomain<UserDto> userActivation(@RequestBody UserDto user){
        logger.info("----Request users/active ---->");
        logger.info(json.toJson(user));

        BaseClassDomain<UserDto> objReturn = new BaseClassDomain<UserDto>();
        objReturn = userService.activateUser(user);

        logger.info("----Response product/active----");
        logger.info(json.toJson(objReturn));

        return objReturn;
    }

}
