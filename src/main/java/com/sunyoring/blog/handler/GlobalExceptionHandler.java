package com.sunyoring.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice  //어디에서든 Exception이 발생하면 이 클래스로 들어온다. 
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value=IllegalArgumentException.class)   // IllegalArgumentException 에 대한 핸들러  
	public String handleArgumentException(IllegalArgumentException e) {  //모든 Exception을 받으려면 Exception e 로 받으면 된다.
		
		return "<h1>" + e.getMessage() +"</h1>";
		
	}

}
