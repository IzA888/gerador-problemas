package com.example.gerador_problemas.service.tipos;

import java.util.Random;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.gerador_problemas.domain.Desafio;
import com.example.gerador_problemas.domain.dto.DesafioDTO;

@Service
@Qualifier("sequenciaDesafio")
public class SequenciaDesafio implements Desafio{

    private final Random random = new Random();

    @Override
    public String getTipo(){
        return "SEQUENCIA";
    }

    @Override
    public DesafioDTO gerar(){
        Integer a = random.nextInt(40) +10;
        Integer b = random.nextInt(40) +20;

        return new DesafioDTO(
            "Sequência Lógica",
            "Qual o próximo número da sequência: " + a + ", " + b + "?",
            a,
            b
        );
    }

    @Override
    public Boolean validar(int resposta, DesafioDTO desafio){
        int tentativa = desafio.getTentativa();
        Boolean correto;

        if ( tentativa <= 2){
            correto = resposta == desafio.getB() + 1;
        } else {
            correto = resposta == desafio.getA() + 1;
        }

        desafio.incrementarTentativa();
        return correto;
    }
    
}
