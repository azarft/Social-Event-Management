package com.alatoo.socialEventManagement.exceptionTest;

import com.alatoo.socialEventManagement.controllers.exceptions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    public void testHandleNotFoundException() {
        // Arrange
        NotFoundException exception = new NotFoundException("Resource not found");
        WebRequest request = Mockito.mock(WebRequest.class);

        // Act
        ResponseEntity<Object> responseEntity = exceptionHandler.handleResourceNotFoundException(exception, request);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Resource not found", ((CustomErrorResponse) responseEntity.getBody()).getMessage());
    }

    @Test
    public void testHandleInvalidRequestException() {
        // Arrange
        InvalidRequestException exception = new InvalidRequestException("Invalid request");
        WebRequest request = Mockito.mock(WebRequest.class);

        // Act
        ResponseEntity<Object> responseEntity = exceptionHandler.handleInvalidRequestException(exception, request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid request", ((CustomErrorResponse) responseEntity.getBody()).getMessage());
    }

    @Test
    public void testHandleInternalServerErrorException() {
        // Arrange
        InternalServerErrorException exception = new InternalServerErrorException("Internal server error");
        WebRequest request = Mockito.mock(WebRequest.class);

        // Act
        ResponseEntity<Object> responseEntity = exceptionHandler.handleInternalServerErrorException(exception, request);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Internal server error", ((CustomErrorResponse) responseEntity.getBody()).getMessage());
    }

}
