package br.com.fiap.techchallenge.externallayer.apis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

@Component
class HomeTest {

  @Test
  void deveRetornarUmaString() {
    Home home = new Home();
    var response = home.showHome();
    assertEquals(false, response.isEmpty());
  }
  
}
