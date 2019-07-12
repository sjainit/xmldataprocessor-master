package com.companyname.ManageCustomerData.data.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.companyname.ManageCustomerData.data.dto.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> catchCustomException(Exception ex, WebRequest request) {
		ErrorDetails erroDetails = new ErrorDetails();
		erroDetails.setTimestamp(new Date());
		erroDetails.setErrorMessage("XML Not In Valid Format");
		erroDetails.setErrorDesc(ex.getMessage());

		return new ResponseEntity<>(erroDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
