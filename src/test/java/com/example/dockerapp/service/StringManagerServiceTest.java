package com.example.dockerapp.service;

import com.example.dockerapp.payload.ReverseReq;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
public class StringManagerServiceTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  StringManagerService stringManagerService;

  @Test
  @Order(1)
  void contextLoads() {

  }

  @Test
  @Order(2)
  void testReverseString() throws JsonProcessingException {
    ReverseReq reverseReq = ReverseReq.builder()
        .reverseParameter("exercise")
        .build();

    String response = stringManagerService.reverse(reverseReq);
    Assertions.assertEquals(response, "esicrexe");
  }

}
