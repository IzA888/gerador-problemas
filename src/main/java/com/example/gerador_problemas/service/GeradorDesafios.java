package com.example.gerador_problemas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gerador_problemas.domain.Desafio;
import com.example.gerador_problemas.domain.dto.DesafioDTO;

@Service
public class GeradorDesafios {

    private final Map<String, Desafio> desafios;

    private final Random random = new Random();

    private Desafio desafioAtual;

    private DesafioDTO dto;

    public GeradorDesafios(List<Desafio> lista) {
        this.desafios = lista.stream().collect(Collectors.toMap(Desafio::getTipo, d -> d));
    }


    public DesafioDTO gerar() {
        List<Desafio> valores = new ArrayList<>(desafios.values());
        Desafio tipo = valores.get(random.nextInt(valores.size()));
        dto = tipo.gerar();
        return dto;
    }
    
    public Desafio getAtual(DesafioDTO desafio) {
        desafioAtual = desafios.get(desafio.getTipo());
        if(desafioAtual != null){
            return desafioAtual;
        } else {
            return desafioAtual = desafios.get(this.dto.getTipo());
        }
    }

    public Boolean validar(int resposta){
        return desafioAtual.validar(resposta, dto);
    }
    
}
