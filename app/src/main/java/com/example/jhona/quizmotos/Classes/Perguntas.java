package com.example.jhona.quizmotos.Classes;

/**
 * Created by jhona on 13/06/2016.
 */
public class Perguntas {

    private int ID;
    private  String Pergunta;
    private  String OpcaoA;
    private  String OpcaoB;
    private  String OpcaoC;
    private  String OpcaoD;
    private long Pontos;

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public long getPontos() {
        return Pontos;
    }

    public void setPontos(long pontos) {
        Pontos = pontos;
    }

    private String Nome;


    public String getResposta() {
        return Resposta;
    }

    public void setResposta(String resposta) {
        Resposta = resposta;
    }

    public String getPergunta() {
        return Pergunta;
    }

    public void setPergunta(String pergunta) {
        Pergunta = pergunta;
    }

    public String getOpcaoA() {
        return OpcaoA;
    }

    public void setOpcaoA(String opcaoA) {
        OpcaoA = opcaoA;
    }

    public String getOpcaoB() {
        return OpcaoB;
    }

    public void setOpcaoB(String opcaoB) {
        OpcaoB = opcaoB;
    }

    public String getOpcaoC() {
        return OpcaoC;
    }

    public void setOpcaoC(String opcaoC) {
        OpcaoC = opcaoC;
    }

    public String getOpcaoD() {
        return OpcaoD;
    }

    public void setOpcaoD(String opcaoD) {
        OpcaoD = opcaoD;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    private  String Resposta;

    public Perguntas(){
        ID=0;
        Pergunta="";
        OpcaoA="";
        OpcaoB="";
        OpcaoC="";
        OpcaoD="";
        Resposta="";
    }

    public Perguntas(String pergunta, String opcaoB, String opcaoA, String opcaoC, String opcaoD, String resposta) {
        Pergunta = pergunta;
        OpcaoB = opcaoB;
        OpcaoA = opcaoA;
        OpcaoC = opcaoC;
        OpcaoD = opcaoD;
        Resposta = resposta;
    }



}
