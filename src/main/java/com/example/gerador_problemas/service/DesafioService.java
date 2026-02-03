package com.example.gerador_problemas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gerador_problemas.domain.Feedback;
import com.example.gerador_problemas.domain.dto.DesafioDTO;

import jakarta.annotation.PostConstruct;

@Service
public class DesafioService {
    
    @Autowired
    private NumParesDesafio desafio;

    private DesafioDTO desafioAtual;

    @PostConstruct
    public void init(){
        this.desafioAtual = desafio.gerar();
    }

    public String submit(int resposta) {

        Boolean valido = desafio.validar(resposta, desafioAtual);

        if(valido){
            return Feedback.sucesso(desafioAtual.getTentativa());
        } else {
            return Feedback.falha(desafioAtual.getTentativa());
        }
    }

    // public String getDescricao() {
    //     return desafioAtual.getDescricao();
    // }

    public void novoDesafio(){
        this.desafioAtual = desafio.gerar();
    }
}