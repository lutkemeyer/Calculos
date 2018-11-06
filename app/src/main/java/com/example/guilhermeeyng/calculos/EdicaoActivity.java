package com.example.guilhermeeyng.calculos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guilhermeeyng.calculos.entidades.Calculo;

public class EdicaoActivity extends AppCompatActivity {

    private boolean isEditando;

    private Calculo calculo;
    private EditText txtFormula, txtNome;
    private Button btnCalcular, btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao_formula);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        txtNome = findViewById(R.id.txtNome);
        txtFormula = findViewById(R.id.txtFormula);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnSalvar = findViewById(R.id.btnSalvar);

        Bundle args = getIntent().getExtras();
        calculo = (Calculo) args.getSerializable(getString(R.string.parametro));

        if(calculo.getNome().isEmpty()){ // se o nome for vazio, é porque está criando
            isEditando = false;
        }else{ // se ja tiver nome, é porque esta editando
            isEditando = true;
            calculo = (Calculo) args.getSerializable(getString(R.string.parametro));
            txtNome.setText(calculo.getNome());
            txtFormula.setText(calculo.getFormula());
            btnSalvar.setEnabled(true);
            btnCalcular.setEnabled(true);
        }

        txtNome.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculo.setNome(String.valueOf(s));
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) { }
        });
    }
    public void onClickCalcular(View view){
        if(validar()){
            Intent it = new Intent(EdicaoActivity.this, CalcularActivity.class);
            Bundle args = new Bundle();
            args.putSerializable(getString(R.string.parametro), calculo);
            it.putExtras(args);
            startActivityForResult(it, 0);
        }
    }
    public void onClickValidar(View view){
        validar();
    }
    private boolean validar(){
        boolean valido = true;
        if(!txtNome.getText().toString().isEmpty() && !txtFormula.getText().toString().isEmpty()){
            String digitado = txtFormula.getText().toString();
            if(Util.confereFormulaValida(digitado)){
                calculo.setExpressao(digitado);
                btnCalcular.setEnabled(true);
                btnSalvar.setEnabled(true);
                toast(getString(R.string.toast_calculo_valido));
            }else{
                toast(getString(R.string.toast_calculo_nao_valido));
                btnCalcular.setEnabled(false);
                btnSalvar.setEnabled(false);
                valido = false;
            }
        }else{
            valido = false;
            toast(getString(R.string.toast_campos_vazios));
        }
        return valido;
    }
    public void onClickSalvar(View view){
        if(validar()){
            calculo.setExpressao(txtFormula.getText().toString());
            BancoDeDados.CALCULOS.add(calculo);
            toast(getString(R.string.toast_calculo_salvo));
            onBackPressed();
        }
    }
    public void toast(String s){
        Toast.makeText(EdicaoActivity.this, s, Toast.LENGTH_SHORT).show();
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
