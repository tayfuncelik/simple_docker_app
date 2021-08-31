package com.example.dockerapp.controller;

import com.example.dockerapp.payload.ReverseReq;
import com.example.dockerapp.service.StringManagerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Slf4j
@RequestMapping("/api")
@RestController
public class StringManagerController {

  private final StringManagerService stringManagerService;

  @PostMapping("/reverse")
  public ResponseEntity<?> reverseString(@RequestBody final ReverseReq request) {

    try {
      return new ResponseEntity<>(stringManagerService.reverse(request), HttpStatus.OK);
    } catch (Exception e) {
      log.error("Exception while converting:", e);
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}

