package com.example.guilhermeeyng.calculos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.guilhermeeyng.calculos.adapters.SimpleAdapter;

import library.Expression;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = (ListView) findViewById(R.id.listView);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this);

        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this, "item", Toast.LENGTH_SHORT).show();

                Intent it = new Intent(MainActivity.this, EdicaoActivity.class);
                Expression expression = (Expression) listView.getAdapter().getItem(position);

                startActivity(it);
            }
        });
    }

    public void AdicionaFormula(View view){
        Intent intent = new Intent(this, EdicaoActivity.class);
        startActivity(intent);
    }
}
