package com.wbw1537.handler.exception;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.enums.AppHttpCodeEnum;
import com.wbw1537.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@ControllerAdvice
//@ResponseBody
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ResponseResult> systemExceptionHandler(SystemException e) {
        // Print Error Messages
        log.error("Error Occurred！{}",e.getMsg());
        // Get error message from Exception Object and return
        return new ResponseEntity<>(ResponseResult.errorResult(e.getCode(),e.getMsg()), HttpStatus.valueOf(e.getCode()));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseResult> exceptionHandler(Exception e) {
        log.error("System Error Occurred！{}",e.getMessage());
        return new ResponseEntity<>(ResponseResult.errorResult(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseResult> handleValidationExceptions(IllegalArgumentException illegalArgumentException){
        // required response syntax: {"code":400,"msg":"Username is required"}
        log.error("Illegal Argument Exception Occurred！{}",illegalArgumentException.getMessage());
        return new ResponseEntity<>(ResponseResult.errorResult(HttpStatus.BAD_REQUEST.value(),illegalArgumentException.getMessage()), HttpStatus.BAD_REQUEST);
        //return new ResponseEntity<>(illegalArgumentException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<ResponseResult> handleInsufficientAuthenticationException(InsufficientAuthenticationException insufficientAuthenticationException){
        // required response syntax: {"code":401,"msg":"Unauthorized"}
        log.error("Insufficient Authentication Exception Occurred！{}",insufficientAuthenticationException.getMessage());
        return new ResponseEntity<>(ResponseResult.errorResult(HttpStatus.UNAUTHORIZED.value(),insufficientAuthenticationException.getMessage()), HttpStatus.UNAUTHORIZED);
        //return new ResponseEntity<>(insufficientAuthenticationException.getMessage(), HttpStatus.UNAUTHORIZED);
    }




}
