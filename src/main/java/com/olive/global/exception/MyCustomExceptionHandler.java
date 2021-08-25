package com.olive.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.olive.custom.exception.ProductException;

@RestControllerAdvice
public class MyCustomExceptionHandler {
	
	/*
	 * Its Return type is ErrorType Class
	 */
	/*
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<ErrorType> handleExp(ProductException exp){
		
		return new ResponseEntity<ErrorType>(
				new ErrorType(exp.getMessage(),
						 "Product Not Found", 
						  "Product Not Exit With Given Id",
						  "Product"),
				HttpStatus.BAD_REQUEST);
	}
	
	*/
	
	/*
	 * Its Return type is String and design like table format 
	 */
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<String> handleExp1(ProductException exp){
		StringBuilder builder = new StringBuilder();
	
		builder.append("<html><body>");
		builder.append("<h1>").append(" SOME THING WENT WRONG ").append("</h1>");
		builder.append("<table border='1'>");
		builder.append("<tr>");
		builder.append("<td>Message :: ").append(exp.getMessage()).append("</tr>");
		builder.append("<tr>");
		builder.append("<td>ERROR :: ").append("PROD NOT FOUND").append("</td>").append("</tr>");
		builder.append("<tr>");
		builder.append("<td>Status code :: ").append(HttpStatus.BAD_REQUEST).append("</td>").append("</tr>");
		builder.append("<tr>");
		builder.append("<td>Class Type :: ").append("Product").append("</td>");
		builder.append("</tr>");
		builder.append("</table>");
		builder.append("</body></html>");
		return new ResponseEntity<String>(
				builder.toString(),
				HttpStatus.BAD_REQUEST);
		
		
	}

}
