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
           String image="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";
           response.addObject("image", image);
           response.setViewName("displayValues");
       }
        return response;
    }
}
