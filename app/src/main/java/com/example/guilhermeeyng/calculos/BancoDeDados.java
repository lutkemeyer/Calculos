package com.example.guilhermeeyng.calculos;

import com.example.guilhermeeyng.calculos.entidades.Calculo;

import java.util.ArrayList;

public class BancoDeDados {

    private static ArrayList<Calculo> CALCULOS = new ArrayList<>();

    public BancoDeDados(){
        CALCULOS.add( new Calculo("Matematica", "(nota1+nota2+nota3)/3"));
        CALCULOS.add( new Calculo("Portugues", "(nota1+nota2+nota3)/3"));
        CALCULOS.add( new Calculo("Ingles", "(nota1+nota2+nota3)/3"));
    }

    public static void adicionar(Calculo calculo){
        CALCULOS.add(calculo);
    }

    public static void atualizar(Calculo calculo){
        int atualizado = 0;
        for(int i=0; i < CALCULOS.size(); i++){
            if(calculo.getNome().equals(CALCULOS.get(i).getNome())){
                atualizado = i;
                break;
            }
        }

        CALCULOS.remove(atualizado);
        adicionar(calculo);

    }

    public static void remover(int posicao){
        CALCULOS.remove(posicao);
    }

    public static ArrayList<Calculo> getCALCULOS() {
        return CALCULOS;
    }
}
