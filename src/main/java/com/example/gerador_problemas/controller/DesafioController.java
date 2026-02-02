package com.example.gerador_problemas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import com.example.gerador_problemas.service.DesafioLogica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/desafio")
public class DesafioController {

    @Autowired
    private DesafioLogica desafioLogica;

    @GetMapping
    public String desafio() {
        return "desafio";
    }

    @PostMapping("/submit")
    public String submit(@RequestParam int resposta, Model model) {
        String certo = desafioLogica.submit(resposta);

        model.addAttribute("resultado", certo);
        
        return "resultado";
    }
    
    
    
}
