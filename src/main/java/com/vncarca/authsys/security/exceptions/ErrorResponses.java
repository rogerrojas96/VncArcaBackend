package com.vncarca.authsys.security.exceptions;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ErrorResponses {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss",timezone = "GMT-5")
	private Date timestamp;
	private int code;
	private HttpStatus status;
	private String message;
	private List<String> errors;

	public ErrorResponses() {
		timestamp = new Date();
	}

	public ErrorResponses(HttpStatus status, String localizedMessage, List<String> errors) {
		this();
		this.code = status.value();
		this.status = status;
		this.message = localizedMessage;
		this.errors = errors;
	}

	public ErrorResponses(HttpStatus status, String localizedMessage, String error) {
		this();
		this.code = status.value();
		this.status = status;
		this.message = localizedMessage;
		errors = Arrays.asList(error);
	}

	public ErrorResponses(HttpStatus httpStatus, List<String> errors) {
		this();
		this.code = httpStatus.value();
		this.status = httpStatus;
		this.errors = errors;
	}

	public ErrorResponses(HttpStatus httpStatus) {
		this();
		this.code = httpStatus.value();
		this.status = httpStatus;
	}

}
