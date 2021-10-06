package com.example.aula4;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int numeroDeCafes = 0;
    int precoUnidade = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // método chamado quando o botão Pedido é clicado
    public void enviarPedido(View view){
        displayPreco(precoUnidade * numeroDeCafes);
    }

    // método mostra quantidade no texto pela id
    private void display(int numero){
        TextView quantidadeTextView = (TextView) findViewById(R.id.quantidade_text_view);
        quantidadeTextView.setText("" + numero);
    }

    /**
     * Atualiza o preço.
     */
    private void displayPreco(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.preco_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    public void incremento(View view){
        numeroDeCafes++;
        display(numeroDeCafes);
    }

    public void decremento(View view){
        numeroDeCafes--;
        display(numeroDeCafes);
    }
}