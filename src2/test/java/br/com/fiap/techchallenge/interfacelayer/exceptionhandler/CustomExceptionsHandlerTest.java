package br.com.fiap.techchallenge.interfacelayer.exceptionhandler;

import static org.junit.Assert.assertEquals;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class CustomExceptionsHandlerTest {

  private CustomExceptionsHandler handler = new CustomExceptionsHandler();
  
  @Test
  void deveCapturarApplicationException() {
    
    var msg = "ABC";
    var exception = new ApplicationException(msg);

    var response = handler.handleApplicationException(exception);

    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    assertEquals(msg, response.getBody().getMessage());
  }

  @Test
  void deveCapturarBusinessRuleException() {
    
    var msg = "ABC";
    var exception = new BusinessRuleException(msg);

    var response = handler.handleBusinessRulesException(exception);

    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    assertEquals(msg, response.getBody().getMessage());
  }

  @Test
  void deveCapturarResourceNotFoundException() {
    
    var msg = "ABC";
    var exception = new ResourceNotFoundException(msg);

    var response = handler.handleResourceNotFoundException(exception);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals(msg, response.getBody().getMessage());
  }

}
