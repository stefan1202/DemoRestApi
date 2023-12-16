package com.example.demorestapi.controller;

import com.example.demorestapi.model.FormObject;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/ui")
public class ThymeleafController {
   @GetMapping
    public String helloWorld(Model model){
       model.addAttribute("text", "Ana are mere, dar vrea pere!");
       model.addAttribute("formObject", new FormObject());
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

    @PostMapping
    public ModelAndView processForm(@Valid @ModelAttribute FormObject form, Errors validationErrors){
        ModelAndView response = new ModelAndView();
       if (validationErrors.hasErrors()){
           response.addObject("formObject",form);
           response.setViewName("helloWorld");
       }else {
           response.addObject("object", form);
           response.addObject("listOfPeople", List.of("Ana", "Maria", "Elena","Stefan","Darius"));
           response.addObject("listOfAges", List.of(39, 45, 30));
           response.setViewName("displayValues");
       }
        return response;
    }
}
