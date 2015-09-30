package com.example.tiago.androidinvestimentoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Detalhes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        //pegando os valores passados pela activity anterior
        Double investimento = getIntent().getDoubleExtra("investimento", 0);
        Integer anos = getIntent().getIntExtra("anos", 0);
        Double rentabilidade = getIntent().getDoubleExtra("rentabilidade", 0);

        //iniciando a listview, adapter e etc
        ArrayList<String> dados = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, dados);
        ListView list = (ListView)findViewById(R.id.lstAnos);

        list.setAdapter(adapter);

        //calculando os paranaues
        double temp = investimento;
        double total =0;
        for (int i = 0; i < anos; i++) {
            temp = investimento * (rentabilidade/100) * i;
            total = temp+investimento;
            dados.add(i + "º ano - $" + total);
        }

        //atualiza o cmapo do total
        ((TextView)findViewById(R.id.txtTotal)).setText("" + total);

        //notifocando modificação pra atualziar a listview
        adapter.notifyDataSetChanged();

    }
}
