package com.example.dockerapp.service;

import com.example.dockerapp.payload.ReverseReq;

public interface StringManagerService {

  String reverse(ReverseReq request);
}
