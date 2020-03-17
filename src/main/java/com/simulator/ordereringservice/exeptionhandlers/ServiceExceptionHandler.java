package com.simulator.ordereringservice.exeptionhandlers;

import com.simulator.ordereringservice.exceptions.ServiceException;
import com.simulator.ordereringservice.models.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    protected ResponseEntity<Object> handleServiceException(ServiceException exception, WebRequest request){
        Response errorResponse = new Response(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
        return handleExceptionInternal(exception,errorResponse,null, HttpStatus.INTERNAL_SERVER_ERROR,request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Response errorResponse = new Response(exception.getMessage(),HttpStatus.BAD_REQUEST.value());
        return handleExceptionInternal(exception,errorResponse,null,HttpStatus.BAD_REQUEST,request);
    }
}
