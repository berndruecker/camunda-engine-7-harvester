package io.berndruecker.camunda.harvest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CamundaEngineNotFoundException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;

	public CamundaEngineNotFoundException() {
        super();
    }

    public CamundaEngineNotFoundException(long id) {
        super("Could not find camunda engine with id '"+id+"' in database");
    }

    public CamundaEngineNotFoundException(String message) {
        super(message);
    }

    public CamundaEngineNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
