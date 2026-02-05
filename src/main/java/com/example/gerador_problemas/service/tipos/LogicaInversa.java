package com.example.gerador_problemas.service.tipos;

import java.util.Random;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.gerador_problemas.domain.Desafio;
import com.example.gerador_problemas.domain.dto.DesafioDTO;

@Service
@Qualifier("logicaInversaDesafio")
public class LogicaInversa implements Desafio{

    private final Random random = new Random();

    @Override
    public String getTipo(){
        return "LOGICA_INVERSA";
    }

    @Override
    public DesafioDTO gerar(){
        Integer a = random.nextInt(40) +10;
        Integer b = random.nextInt(40) +20;

        return new DesafioDTO(
            "Lógica Inversa",
            "Digite um número Ímpar",
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
            correto = resposta % 2 != 0;
        } else {
            correto = resposta % 3 != 0;
        }

        desafio.incrementarTentativa();
        return correto;
    }
    
}
