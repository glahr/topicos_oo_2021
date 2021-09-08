package com.example.aula2_formasgeometricas.ui;

import com.example.aula2_formasgeometricas.logic.Formas;

/**
 * Define os métodos que a interface [MainActivity] vai
 * implementar. Depois veremos com calma isso.
 */
public interface OutputInterface {
    /**
     * Seleciona a forma.
     */
    Formas getShape();

    /**
     * Seleciona o comprimento.
     */
    double getLength();

    /**
     * Seleciona a largura.
     */
    double getWidth();

    /**
     * Seleciona a altura.
     */
    double getHeight();

    /**
     * Seleciona o raio.
     */
    double getRadius();

    /**
     * Reiniciar o texto de output (EditText box).
     */
    void resetText();

    /**
     * Imprime a string de representação do objeto Java ou o tipo seguido de uma linha
     *
     * @param obj uma String, int, double, float, boolean ou qualquer Java Object.
     */
    default void println(Object obj) {
        print(obj);
        println();
    }


    default void println() {
        print('\n');
    }

    /**
     *
     * @param obj a String, int, double, float, boolean or any Java Object.
     */
    void print(Object obj);
}