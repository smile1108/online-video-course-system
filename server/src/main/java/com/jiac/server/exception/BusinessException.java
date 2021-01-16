package com.jiac.server.exception;

/**
 * FileName: BusinessException
 * Author: Jiac
 * Date: 2021/1/16 9:16
 */
public class BusinessException extends RuntimeException {

    private BusinessExceptionCode code;

    public BusinessException(BusinessExceptionCode code) {
        super(code.getDesc());
        this.code = code;
    }

    public BusinessExceptionCode getCode() {
        return code;
    }

    public void setCode(BusinessExceptionCode code) {
        this.code = code;
    }

    /**
     * 不写入堆栈信息，提高性能
     * @return
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
