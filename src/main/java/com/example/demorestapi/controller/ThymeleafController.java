package com.example.demorestapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/ui")
public class ThymeleafController {
   @GetMapping
    public String helloWorld(Model model){
       model.addAttribute("text", "Ana are mere, dar vrea pere!");
        return "helloWorld";
    }

    @GetMapping("1")
    public ModelAndView helloWorldModelAndView(){
        ModelAndView response = new ModelAndView();
        response.addObject("text", "Ana are mere, dar vrea pere!");
        response.setViewName("helloWorld");
        return response;
    }

    @GetMapping("2")
    public RedirectView helloWorldRedirect(){
        return new RedirectView("/ui");
    }
}
