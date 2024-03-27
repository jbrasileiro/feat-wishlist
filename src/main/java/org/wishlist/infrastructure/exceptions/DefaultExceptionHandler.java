package org.wishlist.infrastructure.exceptions;

import java.util.Collections;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.wishlist.domain.exceptions.ApplicationRuntimeException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatusCode statusCode, WebRequest request) {
		log.error("Unexpected exception!", ex);
		return super.handleExceptionInternal(ex, body, headers, statusCode, request);
	}

	@ExceptionHandler(ApplicationRuntimeException.class)
	public ResponseEntity<Object> handleDefaultException(ApplicationRuntimeException ex, WebRequest request) {
		return handleCustomException(ex, HttpStatus.UNPROCESSABLE_ENTITY, request, ex.getLocalizedMessage());
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		return handleCustomException(ex, status, request, "URL not found");
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		return handleCustomException(ex, HttpStatus.BAD_REQUEST, request);
	}

	private ResponseEntity<Object> handleCustomException(Exception exception, HttpStatusCode httpStatusCode,
			WebRequest request) {
		return handleCustomException(exception, httpStatusCode, request, null);
	}
	
	private ResponseEntity<Object> handleCustomException(Exception exception, HttpStatusCode httpStatusCode,
			WebRequest request, String details) {
	    int httpStatusCodeValue = httpStatusCode.value();
	    String path = ((ServletWebRequest) request).getRequest().getRequestURI();

		ExceptionAPIResponse body = ExceptionAPIResponse
				.builder()
				.path(path)
				.status(httpStatusCodeValue)
				.description(exception.getLocalizedMessage())
				.detail(Collections.emptyList())
				.build();
		return handleExceptionInternal(exception, body, new HttpHeaders(), httpStatusCode, request);
	}

}
