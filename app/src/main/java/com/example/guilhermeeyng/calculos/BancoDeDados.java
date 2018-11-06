package com.example.guilhermeeyng.calculos;

import com.example.guilhermeeyng.calculos.entidades.Calculo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class BancoDeDados {

    public static ArrayList<Calculo> CALCULOS = new ArrayList<>();

    public BancoDeDados(){
        CALCULOS.add( new Calculo("Matematica", "(nota1+nota2+nota3)/3"));
        CALCULOS.add( new Calculo("Portugues", "(nota1+nota2+nota3)/3"));
        CALCULOS.add( new Calculo("Ingles", "(nota1+nota2+nota3)/3"));
    }

}
