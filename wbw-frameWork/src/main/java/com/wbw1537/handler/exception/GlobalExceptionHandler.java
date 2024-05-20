package com.wbw1537.handler.exception;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.enums.AppHttpCodeEnum;
import com.wbw1537.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        log.error("System Error Occurred！{}",e.getLogMsg());
        // Get error message from Exception Object and return
        return new ResponseEntity<>(ResponseResult.errorResult(e.getCode(),e.getMsg()), HttpStatus.valueOf(e.getCode()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseResult> handleValidationExceptions(IllegalArgumentException illegalArgumentException){
        log.error("Illegal Argument Exception Occurred！{}",illegalArgumentException.getMessage());
        return new ResponseEntity<>(ResponseResult.errorResult(HttpStatus.BAD_REQUEST.value(),illegalArgumentException.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseResult> handleBadCredentialsException(BadCredentialsException badCredentialsException){
        log.error("Bad Credentials Exception Occurred！{}",badCredentialsException.getMessage());
        return new ResponseEntity<>(ResponseResult.errorResult(HttpStatus.UNAUTHORIZED.value(),badCredentialsException.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<ResponseResult> handleInsufficientAuthenticationException(InsufficientAuthenticationException insufficientAuthenticationException){
        log.error("Insufficient Authentication Exception Occurred！{}",insufficientAuthenticationException.getMessage());
        return new ResponseEntity<>(ResponseResult.errorResult(HttpStatus.UNAUTHORIZED.value(),insufficientAuthenticationException.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseResult> handleHttpMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException){
        log.error("Http Message Not Readable Exception Occurred！{}",httpMessageNotReadableException.getMessage());
        return new ResponseEntity<>(ResponseResult.errorResult(HttpStatus.BAD_REQUEST.value(),httpMessageNotReadableException.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
