package com.example.gerador_problemas.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gerador_problemas.domain.dto.DesafioDTO;
import com.example.gerador_problemas.service.DesafioService;

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
    private DesafioService desafio;

    @GetMapping
    public String desafio(Model model) {
        model.addAttribute("desafio", desafio.getDesafio());
        return "desafio";
    }

    @PostMapping("/submit")
    public String submit(@RequestParam int resposta, Model model) {
        String certo = desafio.submit(resposta);

        model.addAttribute("resultado", certo);
        model.addAttribute("desafio", desafio.getDesafio());
        
        return "resultado";
    }
    
    @GetMapping("/novo")
    public DesafioDTO novo() {   
        return desafio.novoDesafio();
    }
    
    
}
