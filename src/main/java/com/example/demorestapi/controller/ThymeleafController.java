package com.example.demorestapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
public class ThymeleafController {
   @GetMapping
    public String helloWorld(Model model){
       model.addAttribute("text", "Ana are mere, dar vrea pere!");
        return "helloWorld";
    }
}
