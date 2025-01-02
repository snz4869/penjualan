package com.aegis.ultima.controller;

import com.aegis.ultima.dto.LoginRequestDTO;
import com.aegis.ultima.security.TokenProvider;
import com.aegis.ultima.util.BaseClassDomain;
//import com.google.gson.Gson;
import com.aegis.ultima.util.CommonFunction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private CommonFunction commonFunction;

    private final static Logger logger = LogManager.getLogger(AuthController.class);

//    Gson json = new Gson();

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public BaseClassDomain<String> generateToken(@RequestBody LoginRequestDTO loginRequestDTO) throws AuthenticationException {
        logger.info("----Request auth token ---->");
        logger.info("username: "+ loginRequestDTO.getUsername());
        BaseClassDomain<String> objReturn = new BaseClassDomain<String>();
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getUsername(),
                            loginRequestDTO.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = jwtTokenUtil.generateToken(authentication);
            objReturn.setResponseSucceed(token);

        } catch (AuthenticationException e) {
            objReturn.setDescErrorCode("401");
            objReturn.setDescErrorCode("Invalid username or password");
        }

        logger.info("----Response auth token----");
        logger.info("token response::" + commonFunction.convertJSONtoString(objReturn));
        return objReturn;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseClassDomain<String> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        logger.info("----Request auth login ---->");
        logger.info("username: "+ loginRequestDTO.getUsername());
        BaseClassDomain<String> objReturn = new BaseClassDomain<String>();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getUsername(),
                            loginRequestDTO.getPassword()
                    )
            );
            objReturn.setResponseSucceed("Login successful");
        } catch (AuthenticationException e) {
            objReturn.setDescErrorCode("401");
            objReturn.setDescErrorCode("Invalid username or password");
        }
        logger.info("----Response auth login----");
        logger.info("login response::" + commonFunction.convertJSONtoString(objReturn));
        return objReturn;
    }


}
