package com.example.aula4;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // método chamado quando o botão Pedido é clicado
    public void submitOrder(View view){
        int numeroDeCafes = 1;
        display(numeroDeCafes);
    }

    // método mostra quantidade no texto pela id
    private void display(int numero){
        TextView quantidadeTextView = (TextView) findViewById(R.id.quantidade_text_view);
        quantidadeTextView.setText("" + numero);
    }
}