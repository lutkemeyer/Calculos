package com.example.guilhermeeyng.calculos.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import library.Expression;

public class Calculo implements Serializable{
    private String nome;
    private Expression expressao;

    public Calculo(String nome, Expression expressao) {
        this.nome = nome;
        this.expressao = expressao;

        this.expressao.setVariavel("nota1", 1);
        this.expressao.setVariavel("nota2", 2);
        this.expressao.setVariavel("nota3", 3);
    }

    public Calculo(String nome, String expressao){
        this(nome, new Expression(expressao));
    }

    public Calculo(String nome) {
        this(nome, new Expression());
    }

    public Calculo() {
    }

    public String getNome(){
        return nome;
    }

    public String getFormula(){
        return expressao.getExpression();
    }

    public double calcular(){
        return expressao.resolve();
    }
    public ArrayList<String> getChaves(){
        ArrayList<String> lista = new ArrayList<>();
        for(Object obj : expressao.getVariables().keySet().toArray()){
            lista.add(obj.toString());
        }
        return lista;
    }

    public HashMap<String, Double> getVariaveis(){
        return expressao.getVariables();
    }

    public void setVariavel(String v, double val){
        expressao.setVariavel(v,val);
    }

    public boolean isValid(){
        try {
            double resultado = expressao.resolve();
            return resultado >= 0;
        }catch (Exception e){
            return false;
        }
    }
}
