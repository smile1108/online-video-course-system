package com.jiac.business.controller;

import com.jiac.server.dto.ResponseDto;
import com.jiac.server.exception.ValidatorException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * FileName: ControllerExceptionHandler
 * Author: Jiac
 * Date: 2021/1/9 15:46
 */
@ControllerAdvice
// @ControllerAdvice是Controller增强器，可以对Controller做统一的处理，如异常处理，数据处理等
public class ControllerExceptionHandler {

    @ExceptionHandler(value = ValidatorException.class)
    @ResponseBody
    public ResponseDto validatorExceptionHandler(ValidatorException e){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(false);
        responseDto.setMessage("请求参数异常!");
        return responseDto;
    }
}
