package com.example.aula2_formasgeometricas.logic;

import java.util.Locale;

import com.example.aula2_formasgeometricas.ui.OutputInterface;

import static java.lang.String.format;

/**
 * Lógica do sistema concentrado aqui.
 * É uma maneira de simplificar nossas primeiras interações.
 */
public class Logica
        implements InterfaceLogica {
    /**
     * Essa string é usada em Logging (ignore por enquanto).
     */
    public static final String TAG = Logica.class.getName();
    private final OutputInterface mOut;

    /**
     * Construtor dessa classe.
     */
    public Logica(OutputInterface out) {
        mOut = out;
    }

    /**
     * Método que é chamado quando o botão "Calcular" é pressionado
     */
    @Override
    public void process() {
        // Escolhe o cálculo a ser realizado. Não se preocupe com os detalhes por enquanto.
        Formas shapeForCalculations = mOut.getShape();

        // Guarda os valores inseridos pelo usuário..
        double mLength = mOut.getLength();
        double mWidth = mOut.getWidth();
        double mHeight = mOut.getHeight();
        double mRadius = mOut.getRadius();

        // Define qual o cálculo a ser realizado agora.
        switch (shapeForCalculations) {
            case Box:
                mOut.print("Uma caixa de "
                        + mLength
                        + " por "
                        + mWidth
                        + " por "
                        + mHeight
                        + " tem um volume de: ");
                mOut.println(format(Locale.getDefault(), "%.2f", caixaVolume(mLength, mWidth, mHeight)));
                mOut.println();

                mOut.print("Uma caixa de "
                        + mLength
                        + " por "
                        + mWidth
                        + " por "
                        + mHeight
                        + " tem uma área externa de: ");
                mOut.println(format(Locale.getDefault(), "%.2f", caixaAreaSuperficie(mLength, mWidth, mHeight)));
                mOut.println();
            case Rectangle:
                mOut.print("Um retângulo de "
                        + mLength
                        + " por "
                        + mWidth
                        + " tem um perímetro de: ");
                mOut.println(format(Locale.getDefault(), "%.2f", retanguloPerimetro(mLength, mWidth)));
                mOut.println();

                mOut.print("Um retângulo de "
                        + mLength
                        + " por "
                        + mWidth
                        + " tem uma área de: ");
                mOut.println(format(Locale.getDefault(), "%.2f", retanguloArea(mLength, mWidth)));
                mOut.println();
                break;

            case Sphere:
                mOut.print("Uma esfera com raio " + mRadius + " tem um volume de: ");
                mOut.println(format(Locale.getDefault(), "%.2f", esferaVolume(mRadius)));
                mOut.println();

                mOut.print("Uma esfera com raio " + mRadius + " tem um volume de: ");
                mOut.println(format(Locale.getDefault(), "%.2f", esferaSuperficie(mRadius)));
                mOut.println();

            case Circle:
                mOut.print("Um círculo com raio " + mRadius + " tem um perímetro de: ");
                mOut.println(format(Locale.getDefault(), "%.2f", circuloCircunferencia(mRadius)));
                mOut.println();

                mOut.print("Um círculo com raio " + mRadius + " tem uma área de: ");
                mOut.println(format(Locale.getDefault(), "%.2f", circuloArea(mRadius)));
                mOut.println();
                break;
            case Triangle:
                mOut.print("Um triângulo retângulo com base "
                        + mLength
                        + " e altura "
                        + mWidth + " tem um perímetro de: ");
                mOut.println(format(Locale.getDefault(), "%.2f", trianguloRetanguloPerimetro(mLength, mWidth)));
                mOut.println();

                mOut.print("Um triângulo retângulo com base "
                        + mLength
                        + " e altura "
                        + mWidth
                        + " tem uma área de: ");
                mOut.println(format(Locale.getDefault(), "%.2f", trinaguloRetanguloArea(mLength, mWidth)));
                mOut.println();
                break;
            default:
                break;
        }
    }

    // TODO -- seu código aqui


}
