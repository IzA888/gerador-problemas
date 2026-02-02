package com.example.gerador_problemas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gerador_problemas.domain.Feedback;

@Service
public class DesafioLogica {
    
    @Autowired
    private NumParesDesafio desafio;

    private int tentativa = 1;

    public String submit(int resposta) {
        tentativa++;

        Boolean valido = desafio.validar(resposta, tentativa);

        if(valido){
            return Feedback.sucesso(tentativa);
        } else {
            return Feedback.falha(tentativa);
        }
    }

    public String getDescricao() {
        return desafio.getDescricao();
    }
}