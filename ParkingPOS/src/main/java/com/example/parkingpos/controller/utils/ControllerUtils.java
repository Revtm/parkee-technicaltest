package com.example.parkingpos.controller.utils;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ControllerUtils {
    public HttpStatus mappingHttpStatus(String status){
        HttpStatus httpStatus;
        if("CONFLICT".equals(status)){
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        }else if("SUCCESS".equals(status)){
            httpStatus = HttpStatus.OK;
        }else{
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return httpStatus;
    }
}
