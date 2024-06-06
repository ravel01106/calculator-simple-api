package com.kata.arithmetic.controller;

import com.kata.arithmetic.models.ArithmeticResponse;
import com.kata.arithmetic.models.AritmeticRequest;
import com.kata.arithmetic.service.ArithmeticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins="*", allowedHeaders="*")
public class ArithmeticController {
    @Autowired
    ArithmeticService arithmeticService;
    @PostMapping("/calculate")
    public ResponseEntity<ArithmeticResponse> calculate(@RequestBody AritmeticRequest expression){
        ArithmeticResponse arithmeticResponse = new ArithmeticResponse();
      try{
          String result = arithmeticService.calculate(expression.getExpression());

          arithmeticResponse.setResponse(result);
          if (result.equals("Invalid record error")){
              return new ResponseEntity<>(arithmeticResponse, HttpStatus.BAD_REQUEST);
          }
          return new ResponseEntity<>(arithmeticResponse, HttpStatus.OK);
      }catch ( ArrayIndexOutOfBoundsException e) {
          arithmeticResponse.setResponse("Invalid record error");
          return new ResponseEntity<>(arithmeticResponse, HttpStatus.BAD_REQUEST);
      }
    }
}
