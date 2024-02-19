package com.Orders.SpringRestfulAPI.Exception;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import lombok.Getter;


@RestController
@Component
public class GlobalExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	public Map<Integer, Object> handleInvalidRequest(IllegalArgumentException exception) {
		Map <Integer, Object> response = new HashMap();
		response.put(200,  new Error (100, exception.getMessage()));
		return response;
	}	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	public Map<Integer, Set<String>> handleInvaliOrder(MethodArgumentNotValidException exception) {
		Map<Integer, Set<String>> response = new HashMap<>();
		Set<String> errors = exception.getAllErrors()
									  .stream().map(error -> error.getDefaultMessage())
									  .collect(Collectors.toSet());
		response.put(200, errors);
		return response;
	}
}


@AllArgsConstructor
@Getter
class Error{
	private int id;
	private String message;
}