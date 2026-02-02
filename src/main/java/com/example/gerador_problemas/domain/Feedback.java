package com.example.gerador_problemas.domain;

public class Feedback {

    public static String sucesso(int tentativa){
        if(tentativa > 2){
            return "Curioso... isso ainda funciona";
        }
        return "Correto";
    }

    public static String falha(int tentativa){
        if(tentativa > 2){
            return "Estranho. Antes isso funcionaria.";
        }
        return "Resposta incorreta.";
    }
}
