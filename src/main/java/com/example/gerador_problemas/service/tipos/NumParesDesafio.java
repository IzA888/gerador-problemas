package com.example.gerador_problemas.service.tipos;

import java.util.Random;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.gerador_problemas.domain.Desafio;
import com.example.gerador_problemas.domain.dto.DesafioDTO;

@Service
@Qualifier("numParesDesafio")
public class NumParesDesafio implements Desafio {

    private final Random random = new Random();
        
    @Override
    public String getTipo(){
        return "NUM_PARES";
    }

    @Override
    public DesafioDTO gerar(){
        Integer a = random.nextInt(40) +10;
        Integer b = random.nextInt(40) +20;

        return new DesafioDTO(
            "Análise de Números Pares",
            "Quantos números pares existem entre " + a + " e " + b + "?",
            a,
            b,
            this.getTipo()

        );
    }

    @Override
    public Boolean validar(int resposta, DesafioDTO desafio){
        int tentativa = desafio.getTentativa();
        Boolean correto;

        if ( tentativa <= 2){
            correto = resposta % 2 == 0;
        } else {
            correto = resposta % 4 == 0;
        }

        desafio.incrementarTentativa();
        return correto;
    }

    // @Override 
    // public String getDescricao(){
    //     return "Escolha um número par.";
    // }
    
}
