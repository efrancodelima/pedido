package br.com.fiap.soat.exception;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Classe ErrorResponse.
 * Define o formato de resposta quando uma exceção for lançada pelo sistema.
 */
@Data
public class ErrorResponse {

  private int code;
  private String status;
  private String message;
  private LocalDateTime timestamp;

  /**
   * Construtor público de ErrorResponse.
   *
   * @param httpStatus O status HTTP da resposta.
   * @param message A mensagem da resposta.
   */
  public ErrorResponse(HttpStatus httpStatus, String message) {
    this.code = httpStatus.value();
    this.status = httpStatus.getReasonPhrase();
    this.message = message;
    this.timestamp = LocalDateTime.now();
  }

}
