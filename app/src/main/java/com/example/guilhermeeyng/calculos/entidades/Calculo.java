package com.example.guilhermeeyng.calculos.entidades;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;

import library.Expression;

public class Calculo implements Serializable{
    private String nome;
    private Expression expressao;

    public Calculo(String nome, Expression expressao) {
        this.nome = nome;
        this.expressao = expressao;
    }

    public Calculo(String nome, String expressao){
        this(nome);
        setExpressao(expressao);
    }

    public Calculo(String nome) {
        this(nome, new Expression());
    }

    public Calculo() {
        this("");
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

    public void setExpressao(String s){
        this.expressao.getVariables().clear();
        for(String string :  s.replaceAll("[()/*-+.]", " ").split(" ")){
            if(Pattern.compile("[a-zA-Z]{1,}").matcher(string).find()){
                expressao.setVariavel(string, 0);
            }
        }
        expressao.setExpression(s);
    }

    public String getExpressao(){
        return expressao.getExpression();
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setVariavel(String v, double val){
        expressao.setVariavel(v,val);
    }

    @Override
    public String toString() {
        return "Calculo{" +
                "nome='" + nome + '\'' +
                ", expressao=" + expressao +
                '}';
    }

}
