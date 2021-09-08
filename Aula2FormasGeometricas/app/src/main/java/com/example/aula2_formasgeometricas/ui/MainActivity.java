package com.example.aula2_formasgeometricas.ui;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.aula2_formasgeometricas.R;
import com.example.aula2_formasgeometricas.logic.Logica;
import com.example.aula2_formasgeometricas.logic.InterfaceLogica;
import com.example.aula2_formasgeometricas.logic.Formas;

import static android.content.Context.MODE_PRIVATE;


/**
 * MainActivity desse app.
 */
public class MainActivity
        extends AppCompatActivity
        implements OutputInterface {
    /**
     * String para logging.
     */
    private final static String LOG_TAG =
            MainActivity.class.getCanonicalName();

    /**
     * Chaves de seleção.
     */
    private final static String KEY_LENGTH = "length";
    private final static String KEY_WIDTH = "width";
    private final static String KEY_HEIGHT = "height";
    private final static String KEY_RADIUS = "radius";
    private final static String KEY_SHAPE = "shape";

    /**
     * Instância de Logica.
     */
    private InterfaceLogica mLogic;

    /**
     * Componentes UI.
     */
    private TextView mOutput;

    /**
     * Editor de textos.
     */
    private EditText mLength;
    private EditText mWidth;
    private EditText mHeight;
    private EditText mRadius;

    /**
     * Spinner é o seletor usado para escolher as formas
     */
    private Spinner mShapesSpinner;

    /**
     * Chamado quando a atividade está começando.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // required
        super.onCreate(savedInstanceState);

        // create a new 'Logic' instance.
        mLogic = new Logica(this);

        // setup the UI.
        initializeUI();

        if (savedInstanceState != null) {
            mLength.setText(savedInstanceState.getString(KEY_LENGTH, "10"));
            mWidth.setText(savedInstanceState.getString(KEY_WIDTH, "10"));
            mHeight.setText(savedInstanceState.getString(KEY_HEIGHT, "10"));
            mRadius.setText(savedInstanceState.getString(KEY_RADIUS, "10"));
            mShapesSpinner.setSelection(savedInstanceState.getInt(KEY_SHAPE, 0));
        } else {
            SharedPreferences prefs = getPreferences(MODE_PRIVATE);
            mLength.setText(prefs.getString(KEY_LENGTH, "10"));
            mWidth.setText(prefs.getString(KEY_WIDTH, "10"));
            mHeight.setText(prefs.getString(KEY_HEIGHT, "10"));
            mRadius.setText(prefs.getString(KEY_RADIUS, "10"));
            mShapesSpinner.setSelection(prefs.getInt(KEY_SHAPE, 0));
        }
    }

    /**
     * Chamado para salvar a instância antes que a atividade seja destruída.
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(KEY_LENGTH, mLength.getText().toString());
        outState.putString(KEY_WIDTH, mWidth.getText().toString());
        outState.putString(KEY_HEIGHT, mHeight.getText().toString());
        outState.putString(KEY_RADIUS, mRadius.getText().toString());
        outState.putInt(KEY_SHAPE, mShapesSpinner.getSelectedItemPosition());

        super.onSaveInstanceState(outState);
    }

    /**
     * Chamado quando o app é pausado e é recomendado tempo para salvar o input do usuário.
     */
    @Override
    protected void onPause() {
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString(KEY_LENGTH, mLength.getText().toString());
        edit.putString(KEY_WIDTH, mWidth.getText().toString());
        edit.putString(KEY_HEIGHT, mHeight.getText().toString());
        edit.putString(KEY_RADIUS, mRadius.getText().toString());
        edit.putInt(KEY_SHAPE, mShapesSpinner.getSelectedItemPosition());
        edit.apply();

        super.onPause();
    }

    /**
     * Define/seleciona os componentes de UI.
     */
    private void initializeUI() {
        // Define layout
        setContentView(R.layout.activity_main);

        // Guarda referências para várias vistas.
        mOutput = findViewById(R.id.outputET);
        mLength = findViewById(R.id.lengthET);
        mWidth = findViewById(R.id.widthET);
        mHeight = findViewById(R.id.heightET);
        mRadius = findViewById(R.id.radiusET);

        // Guarda referências para mShapesSpinner.
        mShapesSpinner = findViewById(R.id.spinner);

        // Inicializa o adaptador que é usado para juntar data ao mMathSpinner widget.
        ArrayAdapter<CharSequence> mAdapter = ArrayAdapter.createFromResource(this,
                R.array.shapes,
                R.layout.spinner_item);
        mAdapter.setDropDownViewResource(R.layout.spinner_item);

        // Associa o ArrayAdapter com o Spinner.
        mShapesSpinner.setAdapter(mAdapter);
    }

    /**
     * Calculado quando o botão é pressionado.
     */
    public void buttonPressed(View buttonPress) {
        resetText();
        mLogic.process();
    }

    /**
     * Adiciona a @a string para o EditText.
     */
    @SuppressLint("SetTextI18n")
    private void addToEditText(String string) {
        mOutput.setText("" + mOutput.getText() + string);
    }

    /**
     * Pega a forma especificada pelo usuário.
     */
    @Override
    public Formas getShape() {
        final String spinnerString =
                mShapesSpinner.getSelectedItem().toString();
        // .valueOf(String) is an automatically generated method of
        // all Enum(s).  It returns an instance of the enum if one
        // matches the string provided.
        return Formas.valueOf(spinnerString);
    }

    /**
     * Esse método pega o valor do objeto EditText e converte para um double.
     */
    private double getDoubleValueOfEditText(EditText editText) {
        // Preventivo para garantir que EditText não seja nulo.
        if (editText != null) {
            Editable valueEditable = editText.getText();
            //  Preventivo para garantir que o edtiável retonardo não seja nulo.
            if (valueEditable != null) {
                String valueString = valueEditable.toString();
                // Verifica se EditText não está vazio.
                if (!valueString.isEmpty()) {
                    try {
                        double value = Double.parseDouble(valueString);
                        if (value > 0) {
                            return value;
                        } else {
                            return 0;
                        }
                    } catch (NumberFormatException ex) {
                        // Logging do erro se EditText não contém um double formatado
                        Log.e(LOG_TAG, "NumberFormatException thrown when trying to " +
                                "convert to EditText's value to Double: " + ex);
                    }
                }
            }
        }
        // Se algum check falhou, retorna 0.
        return 0;
    }

    /**
     * Seleciona o comprimento.
     */
    @Override
    public double getLength() {
        return getDoubleValueOfEditText(mLength);
    }

    /**
     * Seleciona a largura.
     */
    @Override
    public double getWidth() {
        return getDoubleValueOfEditText(mWidth);
    }

    /**
     * Seleciona a altura.
     */
    @Override
    public double getHeight() {
        return getDoubleValueOfEditText(mHeight);
    }

    /**
     * Seleciona o raio.
     */
    @Override
    public double getRadius() {
        return getDoubleValueOfEditText(mRadius);
    }

    /**
     * Reseta o edit box.
     */
    @Override
    public void resetText() {
        mOutput.setText("");
    }

    @Override
    public void print(Object obj) {
        String text = (obj != null ? obj.toString() : "null");
        addToEditText(text);
        String debug = text.replace("\n", "\\n");
        Log.d(LOG_TAG, "print(" + debug + ")");
    }
}
