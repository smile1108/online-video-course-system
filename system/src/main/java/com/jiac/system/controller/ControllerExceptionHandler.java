package com.jiac.system.controller;

import com.jiac.server.dto.ResponseDto;
import com.jiac.server.exception.BusinessException;
import com.jiac.server.exception.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResponseDto businessExceptionHandler(BusinessException e){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(false);
        LOG.error("业务异常: {}", e.getCode().getDesc());
        responseDto.setMessage(e.getCode().getDesc());
        return responseDto;
    }
}
