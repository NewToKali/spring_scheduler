package com.example.scheduler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String statusCode;
    private String statusMsg;
    private HashMap<String, String> cmdOutput;

}