package com.example.gerador_problemas.domain;

import com.example.gerador_problemas.domain.dto.DesafioDTO;

public interface Desafio {

    DesafioDTO gerar();
    Boolean validar(int resposta, DesafioDTO desafio);
    // String getDescricao();
    
}
