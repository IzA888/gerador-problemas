package com.example.gerador_problemas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gerador_problemas.domain.Desafio;
import com.example.gerador_problemas.domain.Feedback;
import com.example.gerador_problemas.domain.dto.DesafioDTO;

import jakarta.annotation.PostConstruct;

@Service
public class DesafioService {

    @Autowired
    private GeradorDesafios gerador;

    private DesafioDTO dto;

    @PostConstruct
    public void init(){
        dto = gerador.gerar();
    }

    public String submit(int resposta) {
        Boolean valido = gerador.getAtual(dto).validar(resposta, dto);

        if(valido){
            return Feedback.sucesso(dto.getTentativa());
        } else {
            return Feedback.falha(dto.getTentativa());
        }
    }

    public DesafioDTO getDesafio() {
        return dto;
    }

    public DesafioDTO novoDesafio(){
       return gerador.gerar();
        
    }
}