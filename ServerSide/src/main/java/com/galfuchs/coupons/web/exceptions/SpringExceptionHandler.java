package com.galfuchs.coupons.web.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.galfuchs.coupons.core.beans.ErrorBean;
import com.galfuchs.coupons.core.exceptions.ApplicationException;

@ControllerAdvice
public class SpringExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorBean> toResponse(Throwable exception) 
	{
		if (exception instanceof ApplicationException){
			ApplicationException e = (ApplicationException) exception;

			int internalErrorCode = e.getErrorType().getInternalErrorCode();
			String internalMessage = e.getMessage();									
			String externalMessage = e.getErrorType().getInternalMessage();									
			ErrorBean errorBean = new ErrorBean(internalErrorCode, internalMessage, externalMessage);
			return ResponseEntity.status(internalErrorCode).body(errorBean);
		}

		exception.printStackTrace();
		String internalMessage = exception.getMessage();
		ErrorBean errorBean = new ErrorBean(601, internalMessage, "General error");
		return ResponseEntity.status(601).body(errorBean);
	}
}