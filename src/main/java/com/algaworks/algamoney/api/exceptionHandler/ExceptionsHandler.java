package com.algaworks.algamoney.api.exceptionHandler;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.algaworks.algamoney.api.model.Error;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	//Handle errors for fields that not exists
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String userMessage = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String developerMessage = ex.getCause().toString();
		List<Error> errorList = new ArrayList<>();
		errorList.add(new Error(userMessage, developerMessage));
		return handleExceptionInternal(ex, errorList, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	//Handle errors for invalid field values
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Error> errorList = createErrorList(ex.getBindingResult());
		return handleExceptionInternal(ex, errorList, headers, HttpStatus.BAD_REQUEST, request);
	}
	 
	//Handle not found at the data base 
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, 
																				WebRequest request) {
		String userMessage = messageSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());
		String developerMessage = ex.toString();
		List<Error> error = new ArrayList<>();
		error.add(new Error(userMessage, developerMessage));
		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({DataIntegrityViolationException.class})
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, 
																				WebRequest request){
		String userMessage = messageSource.getMessage("resource.bad-request", null, LocaleContextHolder.getLocale());
		String developerMessage = ExceptionUtils.getRootCauseMessage(ex);
		List<Error> error = new ArrayList<>();
		error.add(new Error(userMessage, developerMessage));
		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	//Method for creating chained errors in fields validation
	private List<Error> createErrorList(BindingResult bindingResult){
		List<Error> errorList = new ArrayList<>();
		
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
			String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String developerMessage = fieldError.toString();
			errorList.add(new Error(userMessage, developerMessage));
		}
		return errorList;
	}

	
}
