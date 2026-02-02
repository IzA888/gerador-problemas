package com.example.gerador_problemas.service;

import org.springframework.stereotype.Service;

import com.example.gerador_problemas.domain.Desafio;

@Service
public class NumParesDesafio implements Desafio {

    @Override
    public Boolean validar(int resposta, int tentativa){
        if ( tentativa <= 2){
            return resposta % 2 == 0;
        }

        return resposta % 4 == 0;
    }

    @Override 
    public String getDescricao(){
        return "Escolha um número par.";
    }
    
}
