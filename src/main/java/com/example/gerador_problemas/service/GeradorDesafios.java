package com.example.gerador_problemas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

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
        Desafio desafioAtual = valores.get(random.nextInt(valores.size()));
        dto = desafioAtual.gerar();
        return dto;
    }
    
    public Desafio getAtual(DesafioDTO desafio) {
        if (dto == null || dto.getTipo() == null){
            throw new IllegalArgumentException("DTO ou tipo nulo");
        }

        desafioAtual = desafios.get(dto.getTipo());

        if (desafios == null){
            throw new IllegalStateException("Tipo não registrado: " + dto.getTipo());
        }

        return desafioAtual;
    }

    public Boolean validar(int resposta){
        return desafioAtual.validar(resposta, dto);
    }
    
}
