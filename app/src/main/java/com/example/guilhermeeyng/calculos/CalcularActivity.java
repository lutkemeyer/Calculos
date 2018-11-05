package com.example.guilhermeeyng.calculos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guilhermeeyng.calculos.entidades.Calculo;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import library.Expression;

public class CalcularActivity extends AppCompatActivity {

    private Calculo calculo;
    private LinearLayout llContainer;
    private TextView lblResultado;
    private ArrayList<EditText> campos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular);

        llContainer = findViewById(R.id.llContainer);
        lblResultado = findViewById(R.id.lblResultado);
        campos = new ArrayList<>();

        Bundle args = getIntent().getExtras();

        if(args != null){
            calculo = (Calculo)args.getSerializable("parametro-calculo");

            ArrayList<String> chaves = calculo.getChaves();

            for(int i=0; i < chaves.size(); i++){

                TextView lblNomeVariavel = new TextView(CalcularActivity.this);
                EditText txtValorVariavel = new EditText(CalcularActivity.this);

                lblNomeVariavel.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                txtValorVariavel.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                txtValorVariavel.setInputType(InputType.TYPE_CLASS_NUMBER);

                campos.add(txtValorVariavel);
                lblNomeVariavel.setText(chaves.get(i));

                llContainer.addView(lblNomeVariavel);
                llContainer.addView(txtValorVariavel);

            }
            mostra("veio parametro");
        }else{
            mostra("nao veio parametro");
        }



    }

    public void onClickVoltar(View view){
        onBackPressed();
    }

    public void onClickCalcular(View view){
        if(podeCalcular()){

            ArrayList<String> chaves = calculo.getChaves();

            for(int i = 0; i < campos.size(); i++){
                Double valor = Double.valueOf(campos.get(i).getText().toString());
                calculo.setVariavel(chaves.get(i), valor);
            }

            lblResultado.setText(calculo.calcular() + "");

        }else{
            mostra("Preencha todos os campos");
        }
    }

    public void mostra(String s){
        Toast.makeText(CalcularActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    public boolean podeCalcular(){
        boolean podeCalcular = true;
        for(EditText campo : campos){
            if(campo.getText().toString().isEmpty()){
                podeCalcular = false;
            }
        }
        return podeCalcular;
    }


}
