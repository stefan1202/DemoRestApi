package com.example.demorestapi.controller;

import com.example.demorestapi.exceptions.GenericException;
import com.example.demorestapi.exceptions.SdaException;
import com.example.demorestapi.model.StrangerRequest;
import com.example.demorestapi.model.StrangerResponse;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/controller")
public class HelloWorldController {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    @ResponseBody
    public String helloWorld(){
        return "Hello World";
    }

    @RequestMapping("{name}")
    public ResponseEntity helloStranger(@PathVariable String name){
        StrangerResponse response = new StrangerResponse();
        response.setName(name);
        response.setSalutation("Hello Stranger");
//        return ResponseEntity.ok(hello);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("create")
    public void strangeCreate(@RequestBody @Valid StrangerRequest request, @Valid @Length(min=2,max=7) @RequestParam String paramName ){
        if (paramName!=null){
            System.out.println("Request Param values is:" + paramName);
        }
        System.out.println(request.getName());
    }

    @GetMapping("exeptionExemple")
    public String testExceptions(){
        throw new SdaException("Test");
    }

    @GetMapping("generalException")
    public String testGeneralException(){
        throw new GenericException("Test");
    }

    @GetMapping("runtimeException")
    public String testRuntimeException(){
        throw new RuntimeException("Runtime");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SdaException.class)
    public String handleException(SdaException exception){
        return "There was an exception:" + exception.getMessage();
    }
}
