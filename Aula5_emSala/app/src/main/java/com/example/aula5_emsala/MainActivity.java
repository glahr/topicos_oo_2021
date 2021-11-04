package com.example.aula5_emsala;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int numeroDeCafes = 0;
    double precoUnidadeCafe = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // método chamado quando o botão Pedido é clicado
    public void fazerPedido(View view){
        String[] emails = {"gustavo.lahr@docente.unip.br"};
        String tituloEmail = "Pedido #0";

        EditText nomeEditText = (EditText) findViewById(R.id.nome_edit_text);
        String nome = nomeEditText.getText().toString();

        CheckBox boxChantily = (CheckBox) findViewById(R.id.checkBox_chantily);
        String temChantily = "Não";
        double precoUnidadeChantily = 0;
        if (boxChantily.isChecked()){
            temChantily = "Sim";
            precoUnidadeChantily = 1;
        }

        CheckBox boxRaspasChoc = (CheckBox) findViewById(R.id.checkBox_raspas_chocolate);
        String temRaspasChoc = "Não";
        double precoUnidadeRaspasChoc = 0;
        if (boxRaspasChoc.isChecked()){
            temRaspasChoc = "Sim";
            precoUnidadeRaspasChoc = 2;
        }

        double precoTotal = (precoUnidadeCafe + precoUnidadeChantily + precoUnidadeRaspasChoc) * numeroDeCafes;

        //resumo do pedido
        String resumo = displayResumoPedido(precoTotal, numeroDeCafes, nome, temChantily, temRaspasChoc);
        //mostrar a imagem
        ImageView enviadoImageView = (ImageView) findViewById(R.id.enviado_pedido_image_view);
        enviadoImageView.setImageResource(R.drawable.img_pedido_enviado);

        enviarResumoPorEmail(emails, tituloEmail, resumo);
    }

    public void incremento(View view){
        limparImagem();
        limparTextoResumo();
        numeroDeCafes++;
        displayQuantidade(numeroDeCafes);
    }

    public void decremento(View view){
        limparImagem();
        limparTextoResumo();
        numeroDeCafes--;
        if (numeroDeCafes < 0)
            numeroDeCafes = 0;
        displayQuantidade(numeroDeCafes);
    }

    private void limparImagem(){
        ImageView enviadoImageView = (ImageView) findViewById(R.id.enviado_pedido_image_view);
        if (enviadoImageView.getDrawable() != null){
            enviadoImageView.setImageResource(0);
        }
    }

    private void limparTextoResumo(){
        TextView resumoTextView = (TextView) findViewById(R.id.resumo_text_view);
        resumoTextView.setText("");
    }

    // método mostra quantidade de cafés no texto pela id da quantidade
    private void displayQuantidade(int numero){
        TextView quantidadeTextView = (TextView) findViewById(R.id.quantidade_text_view);
        quantidadeTextView.setText("" + numero);
    }

    /**
     * Atualiza o preço.
     */
    private String displayResumoPedido(double precoTotal, int quantidade, String nome,
                                     String temChantily, String temRaspasChoc) {
        TextView resumoTextView = (TextView) findViewById(R.id.resumo_text_view);
        String resumo = "Nome: " + nome + "\n" +
                        "Quantidade: " + quantidade + "\n" +
                        "Adicionar Chantily: " + temChantily + "\n" +
                        "Adicionar Raspas de Chocolate: " + temRaspasChoc + "\n" +
                        "Total: $" + precoTotal + "\n" +
                        "Obrigado!";
        resumoTextView.setText(resumo);
        return resumo;
    }

    public void enviarResumoPorEmail(String[] addresses, String subject, String resumo) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, resumo);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}