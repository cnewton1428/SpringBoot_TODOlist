package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    TODORepository todoRepository;

    @RequestMapping("/")
    public String listTODO(Model model){
        model.addAttribute("todos", todoRepository.findAll() ); //todos adds the list to list.html
        return "list";
    }
//loop item to list named todoRepository.findAll(), saved under todos, returns to list
    @GetMapping
    public String todoForm(Model model){
        model.addAttribute("todo", new TODO());
        return "todoform";
    }
//new value in string //"to do" adds new item as in enhanced for loop variables to store values
    //for rest g: you;//to do is g

    @PostMapping
    public String processForm(@Valid TODO todo, BindingResult result) {
        if (result.hasErrors()){
            return "todoform";
        }
        todoRepository.save(todo); //saves list so far
        return "redirect:/"; //redirects to @RequestMapping("/")
    }

}
