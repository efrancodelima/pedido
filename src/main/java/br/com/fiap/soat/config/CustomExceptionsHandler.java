package br.com.fiap.soat.config;

import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRuleException;
import br.com.fiap.soat.exception.ErrorResponse;
import br.com.fiap.soat.exception.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Classe CustomExceptionsHandler.
 * Captura as exceções e as mapeia para o tipo ResponseEntity&lt;ErrorResponse&gt;.
 */
@ControllerAdvice
public class CustomExceptionsHandler {

  /**
   * Captura as exceções do tipo ApplicationException.
   *
   * @param ex A exceção capturada.
   * @return A exceção mapeada.
   */
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorResponse> handleApplicationException(BadRequestException ex) {
    return processa(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
  }

  /**
   * Captura as exceções do tipo BusinessRuleException.
   *
   * @param ex A exceção capturada.
   * @return A exceção mapeada.
   */
  @ExceptionHandler(BusinessRuleException.class)
  public ResponseEntity<ErrorResponse> handleBusinessRulesException(BusinessRuleException ex) {
    return processa(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
  }

  /**
   * Captura as exceções do tipo ResourceNotFoundException.
   *
   * @param ex A exceção capturada.
   * @return A exceção mapeada.
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
      ResourceNotFoundException ex) {
    return processa(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  /**
   * Captura as exceções do tipo BadRequestException.
   *
   * @param ex A exceção capturada.
   * @param request A requisição web.
   * @return A exceção mapeada.
   */
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorResponse> handleBadRequestException(
      BadRequestException ex, WebRequest request) {
    return processa(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  /**
   * Captura as exceções do tipo HttpMessageNotReadableException.
   *
   * @param ex A exceção capturada.
   * @param request A requisição web.
   * @return A exceção mapeada.
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException ex, WebRequest request) {
    return processa(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  /**
   * Captura as exceções do tipo InvalidFormatException.
   *
   * @param ex A exceção capturada.
   * @param request A requisição web.
   * @return A exceção mapeada.
   */
  @ExceptionHandler(InvalidFormatException.class)
  public ResponseEntity<ErrorResponse> handleInvalidFormatException(
      InvalidFormatException ex, WebRequest request) {
    return processa(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  /**
   * Captura as exceções do tipo JsonParseException.
   *
   * @param ex A exceção capturada.
   * @param request A requisição web.
   * @return A exceção mapeada.
   */
  @ExceptionHandler(JsonParseException.class)
  public ResponseEntity<ErrorResponse> handleJsonParseException(
      JsonParseException ex, WebRequest request) {
    return processa(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  /**
   * Captura as exceções do tipo JsonMappingException.
   *
   * @param ex A exceção capturada.
   * @param request A requisição web.
   * @return A exceção mapeada.
   */
  @ExceptionHandler(JsonMappingException.class)
  public ResponseEntity<ErrorResponse> handleJsonMappingException(
      JsonMappingException ex, WebRequest request) {
    return processa(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  /**
   * Captura as exceções do tipo Exception.
   *
   * @param ex A exceção capturada.
   * @return A exceção mapeada.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
    return processa(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // Método privado
  private ResponseEntity<ErrorResponse> processa(String msg, HttpStatus httpStatus) {
    var error = new ErrorResponse(httpStatus, msg);
    return new ResponseEntity<>(error, httpStatus);
  }

}
