package com.aegis.ultima.controller;

import com.aegis.ultima.model.AuthToken;
import com.aegis.ultima.model.LoginUser;
import com.aegis.ultima.security.TokenProvider;
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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    private final static Logger logger = LogManager.getLogger(AuthController.class);

    Gson json = new Gson();

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public BaseClassDomain<String> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {
        logger.info("----Request auth token ---->");
        logger.info("username: "+ loginUser.getUsername());
        BaseClassDomain<String> objReturn = new BaseClassDomain<String>();
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUser.getUsername(),
                            loginUser.getPassword()
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
        logger.info(json.toJson(objReturn));
        return objReturn;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseClassDomain<String> login(@RequestBody LoginUser loginUser) {
        logger.info("----Request auth login ---->");
        logger.info("username: "+ loginUser.getUsername());
        BaseClassDomain<String> objReturn = new BaseClassDomain<String>();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUser.getUsername(),
                            loginUser.getPassword()
                    )
            );
            objReturn.setResponseSucceed("Login successful");
        } catch (AuthenticationException e) {
            objReturn.setDescErrorCode("401");
            objReturn.setDescErrorCode("Invalid username or password");
        }
        logger.info("----Response auth token----");
        logger.info(json.toJson(objReturn));
        return objReturn;
    }


}
