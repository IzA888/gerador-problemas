package com.example.gerador_problemas.domain;

import org.springframework.stereotype.Component;

import com.example.gerador_problemas.domain.dto.DesafioDTO;

@Component
public interface Desafio {

    String getTipo();
    DesafioDTO gerar();
    Boolean validar(int resposta, DesafioDTO desafio);
    // String getDescricao();
    
}
