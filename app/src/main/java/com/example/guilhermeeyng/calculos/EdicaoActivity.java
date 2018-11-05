package com.example.guilhermeeyng.calculos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guilhermeeyng.calculos.entidades.Calculo;

import library.Expression;

public class EdicaoActivity extends AppCompatActivity {

    private boolean isEditando = false;

    private Calculo calculo;
    private EditText edtFormula, edtNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao_formula);
        edtNome = findViewById(R.id.edtNome);
        edtFormula = findViewById(R.id.edtFormula);

        Bundle args = getIntent().getExtras();

        if(args == null){ // se for nulo, está criando um novo
            calculo = new Calculo();
        }else{ // se nao for nulo, é porque está editando
            isEditando = true;
            calculo = (Calculo) args.getSerializable("parametro-calculo");
            edtNome.setText(calculo.getNome());
            edtFormula.setText(calculo.getFormula());
        }

    }

    public void onClickCalcular(View view){
        if(calculo.isValid()){
            Intent it = new Intent(EdicaoActivity.this, CalcularActivity.class);
            Bundle args = new Bundle();
            args.putSerializable("parametro-calculo", calculo);
            it.putExtras(args);
            startActivity(it);
        }else{
            mostra("A formula precisa ser válida");
        }
    }
    public void onClickVoltar(View view){
        onBackPressed();
    }
    public void onClickValidar(View view){
        if(!edtNome.getText().toString().isEmpty() || !edtFormula.getText().toString().isEmpty()){
            String digitado = edtFormula.getText().toString();
            try {
                Expression expression = new Expression(digitado);
                double resultado = expression.resolve();
                if(resultado >= 0){
                    mostra("Calculo validado");
                }else{
                    mostra("Calculo inválido");
                }
            }catch (Exception e){
                mostra("Calculo inválido");
            }
        }else{
            mostra("Campos vazios");
        }
    }

    public void mostra(String s){
        Toast.makeText(EdicaoActivity.this, s, Toast.LENGTH_SHORT).show();
    }
}
