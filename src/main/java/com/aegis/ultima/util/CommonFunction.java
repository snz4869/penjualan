package com.aegis.ultima.util;

import com.aegis.ultima.controller.AuthController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CommonFunction {
    private final static Logger logger = LogManager.getLogger(AuthController.class);

    public String convertJSONtoString(Object obj) {
        String jsonStr = "";
        ObjectMapper Obj = new ObjectMapper();
        //logger.info("object ::" + obj);
        try {
            // get Organisation object as a json string
            jsonStr = Obj.writeValueAsString(obj);

        }catch (IOException e) {
            logger.error("Error: "+e.getStackTrace());
        }
        return jsonStr;
    }
}
