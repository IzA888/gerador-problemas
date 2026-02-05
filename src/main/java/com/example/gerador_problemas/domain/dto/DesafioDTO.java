package com.example.gerador_problemas.domain.dto;

public class DesafioDTO {

    private String titulo;
    private String pergunta;
    private Integer a;
    private Integer b;
    private Integer tentativa = 1;
    private String tipo;

    public DesafioDTO(String titulo, String pergunta, Integer a, Integer b, String tipo) {
        this.titulo = titulo;
        this.pergunta = pergunta;
        this.a = a;
        this.b = b;
        this.tipo = tipo;
    }

    public void incrementarTentativa(){
        this.tentativa ++;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getPergunta() {
        return pergunta;
    }

    public Integer getTentativa() {
        return tentativa;
    }

    public Integer getA() {
        return a;
    }

    public Integer getB() {
        return b;
    }

    public String getTipo() {
        return tipo;
    }

    

}