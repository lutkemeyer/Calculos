package com.example.guilhermeeyng.calculos;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.guilhermeeyng.calculos.adapters.SimpleAdapter;
import com.example.guilhermeeyng.calculos.entidades.Calculo;

import library.Expression;

public class MainActivity extends AppCompatActivity {

    private ListView listaCalculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaCalculos = findViewById(R.id.listView);
        new BancoDeDados();
        listaCalculos.setAdapter(new SimpleAdapter(MainActivity.this, BancoDeDados.getCALCULOS()));
    }
    public void adicionaFormula(View view){
        Intent intent = new Intent(this, EdicaoActivity.class);
        Bundle args = new Bundle();
        Calculo calculo = new Calculo();
        args.putSerializable(getString(R.string.parametro), calculo);
        intent.putExtras(args);
        startActivityForResult(intent, 0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        ((SimpleAdapter)listaCalculos.getAdapter()).notifyDataSetChanged();
    }
}
