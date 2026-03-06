package com.alura.api_rest_foro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello_world")
public class PruebaController {

    @GetMapping
    public String saludar(){
        return "Hola :)";
    }
}
