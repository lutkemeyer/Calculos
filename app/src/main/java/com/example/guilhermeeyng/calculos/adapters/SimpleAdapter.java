package com.example.guilhermeeyng.calculos.adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.guilhermeeyng.calculos.BancoDeDados;
import com.example.guilhermeeyng.calculos.CalcularActivity;
import com.example.guilhermeeyng.calculos.EdicaoActivity;
import com.example.guilhermeeyng.calculos.R;
import com.example.guilhermeeyng.calculos.entidades.Calculo;

import java.util.ArrayList;
import java.util.Arrays;

import library.Expression;

public class SimpleAdapter extends BaseAdapter {

    private ArrayList<Calculo> calculos;
    private Activity activity;

    public SimpleAdapter(Activity activity, ArrayList<Calculo> calculos){
        super();
        this.activity = activity;
        this.calculos = calculos;
    }

    @Override
    public int getCount(){
        return calculos.size();
    }

    @Override
    public Calculo getItem(int position){
        return calculos.get(position);
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup){

        View view = LayoutInflater.from(activity).inflate(R.layout.item_calculo, viewGroup, false);
        TextView lblCalculo = view.findViewById(R.id.lblCalculo);
        TextView lblNome = view.findViewById(R.id.lblNome);
        final ImageButton btnOpcoes = view.findViewById(R.id.btnOpcoes);

        final Calculo calculo = calculos.get(position);

        lblNome.setText(calculo.getNome());
        lblCalculo.setText(calculo.getFormula());

        btnOpcoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(activity, btnOpcoes);
                popupMenu.getMenuInflater().inflate(R.menu.menu_opcoes, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.mn_calcular:
                                Intent itCalcular = new Intent(activity, CalcularActivity.class);
                                Bundle argsCalcular = new Bundle();
                                argsCalcular.putSerializable(activity.getString(R.string.parametro), calculos.get(position));
                                itCalcular.putExtras(argsCalcular);
                                activity.startActivityForResult(itCalcular, 0);
                                break;
                            case R.id.mn_editar:
                                Intent itEditar = new Intent(activity, EdicaoActivity.class);
                                Bundle argsEditar = new Bundle();
                                argsEditar.putSerializable(activity.getString(R.string.parametro), calculos.get(position));
                                itEditar.putExtras(argsEditar);
                                activity.startActivityForResult(itEditar, 0);
                                break;
                            case R.id.mn_remover:
                                BancoDeDados.remover(position);
                                notifyDataSetChanged();
                                break;
                        }
                        return true;
                    }
                });

                popupMenu.show();
            }
        });



        return view;
    }

}
