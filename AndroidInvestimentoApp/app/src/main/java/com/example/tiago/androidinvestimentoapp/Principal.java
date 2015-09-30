package com.example.tiago.androidinvestimentoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void BtnCalcular(View view) {
        //buscando os valores das editText's
        EditText edtInvestimento = (EditText) findViewById(R.id.edtInvestimento);
        EditText edtAnos = (EditText) findViewById(R.id.edtAnos);
        EditText edtRentabilidade = (EditText) findViewById(R.id.edtRentabilidade);

        //validando os campos
        boolean checkInvestimento = validarCampoDouble(edtInvestimento, "Investimento");
        boolean checkAnos = validarCampoDouble(edtAnos, "Anos");
        boolean checkRentabilidade = validarCampoDouble(edtRentabilidade, "Rentabilidade");

        //se as 3 variaves são true ok, ja estao todas validadas e pode passar pra outra activity
        if(checkInvestimento && checkAnos && checkRentabilidade)
        {
            //pegando os valores
            Double investimento     = Double.parseDouble(edtInvestimento.getText().toString());
            Integer anos            = (int)Double.parseDouble(edtAnos.getText().toString());
            Double rentabilidade    = Double.parseDouble(edtRentabilidade.getText().toString());

            //intenção de abrir a nova activity
            Intent intent = new Intent(this,Detalhes.class);

            //dados a serem passados para a nova activity
            intent.putExtra("investimento", investimento);
            intent.putExtra("anos", anos);
            intent.putExtra("rentabilidade", rentabilidade);

            //abre nova activity
            startActivity(intent);
        }
    }

    // Verifica se é possivel fazer a conversão; Caso nao seja lança o Toast
    public boolean validarCampoDouble(EditText editText, String nomeCampo) {
        boolean resultado;

        //se tiver algum valor muito estanho, ou vazio, cai no catch
        try {
            Double valor = Double.parseDouble(editText.getText().toString());
            //se for um valor invalido como menor que zero, cai nesse toast
            if(valor <= 0 )
            {
                Toast.makeText(this, "Valor do " + nomeCampo + " é inferior a zero", Toast.LENGTH_SHORT).show();
                resultado = false;
            }
            else {
                resultado = true;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Valor inválido no campo de " + nomeCampo, Toast.LENGTH_SHORT).show();
            resultado = false;
        }

        return resultado;
    }
}
