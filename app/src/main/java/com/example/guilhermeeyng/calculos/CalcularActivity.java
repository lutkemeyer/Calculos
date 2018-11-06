package com.example.guilhermeeyng.calculos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    private TextView lblResultado, lblFormula;
    private ArrayList<EditText> campos;
    private Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        llContainer = findViewById(R.id.llContainer);
        lblResultado = findViewById(R.id.lblResultado);
        lblFormula = findViewById(R.id.lblFormula);
        btnCalcular = findViewById(R.id.btnCalcular);
        campos = new ArrayList<>();

        Bundle args = getIntent().getExtras();

        if(args != null){
            calculo = (Calculo)args.getSerializable("parametro-calculo");

            lblFormula.setText(calculo.getFormula());

            ArrayList<String> chaves = calculo.getChaves();

            TextWatcher listenerDigitacao = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    podeCalcular();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            };

            for(int i=0; i < chaves.size(); i++){

                TextView lblNomeVariavel = new TextView(CalcularActivity.this);
                EditText txtValorVariavel = new EditText(CalcularActivity.this);

                lblNomeVariavel.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                txtValorVariavel.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                txtValorVariavel.setInputType(InputType.TYPE_CLASS_NUMBER);

                txtValorVariavel.addTextChangedListener(listenerDigitacao);

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

    public void onClickCalcular(View view){
        if(podeCalcular()){

            ArrayList<String> chaves = calculo.getChaves();

            for(int i = 0; i < campos.size(); i++){
                Double valor = Double.valueOf(campos.get(i).getText().toString());
                calculo.setVariavel(chaves.get(i), valor);
            }

            lblResultado.setText(calculo.calcular() + "");

        }else{
            lblResultado.setText("-");
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

        btnCalcular.setEnabled(podeCalcular);
        return podeCalcular;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
