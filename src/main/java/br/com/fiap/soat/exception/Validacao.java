package br.com.fiap.soat.exception;

import java.time.LocalDate;

/**
 * Classe usada nas validações das entidades de negópcio.
 */
public class Validacao {

  private Validacao() {}

  public static final  LocalDate DATA_MIN = LocalDate.of(2020, 1, 1);

  public static final String DATA_MIN_STR = "01/01/2020";

}
