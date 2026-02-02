package com.example.gerador_problemas.domain;

public interface Desafio {

    Boolean validar(int resposta, int tentativa);
    String getDescricao();
    
}
