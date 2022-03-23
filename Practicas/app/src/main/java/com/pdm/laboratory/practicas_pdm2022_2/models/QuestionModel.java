package com.pdm.laboratory.practicas_pdm2022_2.models;

public class QuestionModel {

    private String question;
    private Boolean answer;


    /**
     * Clase que modela una pregunta de tipo True o False
     * @param question Pregunta de Verdadero y Falso
     * @param answer Respuesta, ya sea True o False
     */
    public QuestionModel(String question, Boolean answer){
        this.question = question;
        this.answer = answer;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public void setAnswer(Boolean answer){
        this.answer = answer;
    }

    public String getQuestion(){
        return this.question;
    }

    public Boolean getAnswer(){
        return this.answer;
    }

    public String toString(){
        return "Pregunta : "+ question + "\nRespuesta : "+ answer;
    }
}
