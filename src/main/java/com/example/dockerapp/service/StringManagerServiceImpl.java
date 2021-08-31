package com.example.dockerapp.service;

import com.example.dockerapp.payload.ReverseReq;
import org.springframework.stereotype.Service;

@Service
public class StringManagerServiceImpl implements StringManagerService {

  @Override
  public String reverse(ReverseReq request) {
    StringBuilder output = new StringBuilder();
    output.append(request.getReverseParameter());
    return output.reverse().toString();
  }
}
