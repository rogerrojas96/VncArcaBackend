package com.vncarca.authsys.security.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DataAccessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> handleDataAccessException(DataAccessException e, WebRequest webRequest) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorResponses errorResponse = new ErrorResponses(status, e.getMessage().intern().split(";")[0],
				e.getMostSpecificCause().getMessage());
		return new ResponseEntity<Object>(errorResponse,
				status);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException e, WebRequest webRequest) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorResponses errorResponse = new ErrorResponses(status, e.getMessage().intern().split(";")[0],
				e.getMostSpecificCause().getMessage());
		return new ResponseEntity<Object>(errorResponse,
				status);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<ErrorResponse> handleRecordNotFound(EmptyResultDataAccessException ex) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		log.trace("Record not found: {}", ex.getMessage());
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage()), status);
	}

	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<Object> handleExpiredJwtExceptions(ExpiredJwtException e) {
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		ErrorResponses errorResponse = new ErrorResponses(status, null, e.getMessage());
		return new ResponseEntity<Object>(errorResponse,
				status);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add("El campo '" + error.getField() + "' " + error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		ErrorResponses errorResponse = new ErrorResponses(HttpStatus.BAD_REQUEST, errors);
		return handleExceptionInternal(
				ex, errorResponse, headers, errorResponse.getStatus(), request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			MissingServletRequestParameterException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String error = ex.getParameterName() + " falta el par??metro";

		ErrorResponses errorResponse = new ErrorResponses(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
		return new ResponseEntity<Object>(
				errorResponse, new HttpHeaders(), errorResponse.getStatus());
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(
			ConstraintViolationException ex, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getRootBeanClass().getName() + " " +
					violation.getPropertyPath() + ": " + violation.getMessage());
		}

		ErrorResponses errorResponse = new ErrorResponses(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
		return new ResponseEntity<Object>(
				errorResponse, new HttpHeaders(), errorResponse.getStatus());
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
			MethodArgumentTypeMismatchException ex, WebRequest request) {
		String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

		ErrorResponses errorResponse = new ErrorResponses(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
		return new ResponseEntity<Object>(errorResponse, new HttpHeaders(), errorResponse.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append(
				" method is not supported for this request. Supported methods are ");
		ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

		ErrorResponses errorResponse = new ErrorResponses(HttpStatus.METHOD_NOT_ALLOWED,
				ex.getLocalizedMessage(), builder.toString());
		return new ResponseEntity<Object>(
				errorResponse, new HttpHeaders(), errorResponse.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
			HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));

		ErrorResponses errorResponse = new ErrorResponses(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
				ex.getLocalizedMessage(), builder.substring(0, builder.length() - 2));
		return new ResponseEntity<Object>(
				errorResponse, new HttpHeaders(), errorResponse.getStatus());
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Object> handleBadCredentialsExceptions(Exception e) {
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		return new ResponseEntity<Object>(new ErrorResponse(status, e.getMessage()),
				status);
	}

	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<Object> handleDisabledExceptionExceptions(Exception e) {
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		return new ResponseEntity<Object>(new ErrorResponse(status, e.getMessage()),
				status);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentExceptions(Exception e) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorResponses errorResponse = new ErrorResponses(status, null, e.getMessage());
		return new ResponseEntity<Object>(errorResponse,
				status);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Object> handleNoSuchElementExceptions(Exception e) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorResponses errorResponse = new ErrorResponses(status, null, e.getMessage());
		return new ResponseEntity<Object>(errorResponse,
				status);
	}

	public static List<String> getExceptionMessageChain(Throwable throwable) {
		List<String> result = new ArrayList<String>();
		while (throwable != null) {
			result.add(throwable.getMessage());
			throwable = throwable.getCause();
		}
		return result;
	}
}