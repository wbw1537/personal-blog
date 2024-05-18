package com.wbw1537.exception;

import com.wbw1537.enums.AppHttpCodeEnum;

public class SystemException extends RuntimeException{
    private int code;
    private String msg;
    private String logMsg;

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
    public String getLogMsg() {
        return logMsg;
    }


    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
        this.logMsg = httpCodeEnum.getMsg();
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum, String msg) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
        this.logMsg = msg;
    }
}
