package com.turkcell.gyt.managementService.core.utilitiy.exceptions.handlers;


;
import com.turkcell.gyt.managementService.core.utilitiy.exceptions.problemDetails.BusinessProblemDetails;
import com.turkcell.gyt.managementService.core.utilitiy.exceptions.problemDetails.ValidationProblemDetails;
import com.turkcell.gyt.managementService.core.utilitiy.exceptions.types.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BusinessProblemDetails handleBusinessException(BusinessException exception) {

        BusinessProblemDetails businessProblemDetails = new BusinessProblemDetails();
        businessProblemDetails.setDetail(exception.getMessage());
        return businessProblemDetails;
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationProblemDetails handleValidationException(MethodArgumentNotValidException exception) {

        Map<String,String> validationErrors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(fieldError ->{
                validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());}
        );


        ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
        validationProblemDetails.setErrors(validationErrors);
        return validationProblemDetails;
    }
}
