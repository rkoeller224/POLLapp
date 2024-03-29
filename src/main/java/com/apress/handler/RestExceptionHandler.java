package com.apress.handler;


import com.apress.dto.error.ErrorDetail;
import com.apress.dto.error.ValidationError;
import com.apress.exception.ResourceNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import sun.util.calendar.LocalGregorianCalendar;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    @Inject
    private MessageSource messageSource;
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorDetail handleValidationError(MethodArgumentNotValidException
                                                           manve, HttpServletRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        //populate errordetail instance
        errorDetail.setTimestamp(new  Date().getTime());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestPath == null) {
            requestPath = request.getRequestURI();
        }
        errorDetail.setTitle("Validation Failed");
        errorDetail.setDetail("Input validation failed");
        errorDetail.setDeveloperMessage(manve.getClass().getName());

        //Create ValidationError instances
        List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
        for (FieldError fe : fieldErrors) {
            List<ValidationError> validationErrorList = errorDetail.getErrors().get(fe.getField());
            if (validationErrorList == null) {
                validationErrorList = new ArrayList<ValidationError>();
                errorDetail.getErrors().put(fe.getField(), validationErrorList );
            }
            ValidationError validationError = new ValidationError();
            validationError.setCode(fe.getCode());
            validationError.setMessage(messageSource.getMessage(fe, null));
            validationErrorList.add(validationError);
        }
        return errorDetail;

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<?> handleResourceNotFoundException
//            (ResourceNotFoundException rnfe, HttpServletRequest request) {
//        ErrorDetail errorDetail = new ErrorDetail();
//        errorDetail.setTimestamp(new Date().getTime());
//        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
//        errorDetail.setTitle("Resource Not Found");
//        errorDetail.setDetail(rnfe.getMessage());
//        errorDetail.setDeveloperMessage(rnfe.getClass().getName());
//
//        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
//    }
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException
//                                                   manve, HttpServletRequest request) {
//        ErrorDetail errorDetail = new ErrorDetail();
//        //it populates error detail instance
//        errorDetail.setTimestamp(new  Date().getTime());
//        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
//        String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri");
//        if (requestPath == null) {
//            requestPath = request.getRequestURI();
//        }
//        errorDetail.setTitle("Validation Failed");
//        errorDetail.setDetail("Input validation failed");
//        errorDetail.setDeveloperMessage(manve.getClass().getName());
//
//
//        //Create ValidationError instance
//        List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
//        for (FieldError fe : fieldErrors) {
//            List<ValidationError> validationErrorList = errorDetail.getErrors().get(fe.getField());
//            if (validationErrorList == null) {
//                validationErrorList = new ArrayList<ValidationError>();
//                errorDetail.getErrors().put(fe.getField(),validationErrorList);
//            }
//            ValidationError validationError = new ValidationError();
//            validationError.setCode(fe.getCode());
//            validationError.setMessage(fe.getDefaultMessage());
//            validationErrorList.add(validationError);
//        }
//        return new ResponseEntity<>(errorDetail,null, HttpStatus. BAD_REQUEST);



    }

}
