package com.example.dockerapp.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.dockerapp.payload.ReverseReq;
import com.example.dockerapp.service.StringManagerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StringManagerControllerTest {

  @Autowired
  MockMvc mockMvc;
  @MockBean  //To make integration test remote mock
  StringManagerService stringManagerService;

  @Test
  @Order(1)
  void contextLoads() {

  }

  @Test
  @Order(2)
  public void reverseString() throws Exception {

    ReverseReq reverseReq = ReverseReq.builder()
        .reverseParameter("exercise")
        .build();
    ObjectMapper Obj = new ObjectMapper();
    String content = Obj.writeValueAsString(reverseReq);
    Mockito.when(stringManagerService.reverse(Mockito.any())).thenReturn("esicrexe");

    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/api/reverse")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);
    mockMvc.perform(requestBuilder).andDo(print())
        .andExpect(MockMvcResultMatchers.jsonPath("$").value("esicrexe"));

  }


  @Test
  @Order(3)
  public void reverseStringError() throws Exception {

    ObjectMapper Obj = new ObjectMapper();
    String content = Obj.writeValueAsString(null);
    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/api/reverse")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);
    mockMvc.perform(requestBuilder).andDo(print())
        .andExpect(status().is4xxClientError());
  }

  @Test
  @Order(4)
  public void reverseStringUnavailable() throws Exception {

    ObjectMapper Obj = new ObjectMapper();
    String content = Obj.writeValueAsString("exercise");
    Mockito.when(stringManagerService.reverse(Mockito.any())).thenThrow(NullPointerException.class);

    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/api/reverse")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);
    mockMvc.perform(requestBuilder).andDo(print())
        .andExpect(status().is5xxServerError());
  }
}
