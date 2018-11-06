package com.example.guilhermeeyng.calculos;

import android.util.Log;

import com.example.guilhermeeyng.calculos.entidades.Calculo;

import java.util.regex.Pattern;

public class Util {

    public static boolean confereFormulaValida(String f){
        return confereParenteses(f) && temCaracteresValidos(f);
    }

    private static boolean confereParenteses(String f) {
        int abreParenteses = 0, fechaParenteses = 0;
        for (char c : f.toCharArray()) {
            if (c == '(') {
                abreParenteses++;
            } else if (c == ')') {
                fechaParenteses++;
            }
        }
        return abreParenteses == fechaParenteses;
    }

    private static boolean temCaracteresValidos(String f){
        return !Pattern.compile("[^a-z A-Z 0-9]").matcher(f
                .replace("*", "")
                .replace("/", "")
                .replace("+", "")
                .replace("-", "")
                .replace(".", "")
                .replace("(", "")
                .replace(")", "")
        ).find();
    }
}
