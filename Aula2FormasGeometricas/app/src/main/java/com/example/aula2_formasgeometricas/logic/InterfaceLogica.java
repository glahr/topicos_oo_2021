package com.example.aula2_formasgeometricas.logic;

/**
 * Essa interface existe para simplificar as interações entre a
 * UI (User Interface: [MainActivity.java] aqui)
 * e a classe de Logica [Logica.java].
 *
 * Essa interface define o método 'void process()' que
 * [Logic.java] irá implementar. Isso permite que
 * [MainActivity.java] guarde uma instância de [Logic.java] dentro da
 * variável 'InterfaceLogica' (mLogic dentro de [MainActivity.java])
 */
public interface InterfaceLogica {
    /**
     * Esse método é chamado (indiretamente por
     * [MainActivity.java]) quando o botão 'Calcular' é
     * pressionado.
     */
    void process();
}
