package com.example.p4;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etNumber1, etNumber2;
    private Spinner operation;
    private TextView tvResult;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber1 = findViewById(R.id.editTextText);
        etNumber2 = findViewById(R.id.editTextText2);
        operation = findViewById(R.id.spinner);
        tvResult = findViewById(R.id.textView5);
        btnCalculate = findViewById(R.id.button); // Captura el botón

        // Opciones para el Spinner
        String[] opciones = {"+", "-", "*", "/"};

        // Configuración del adaptador del Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, opciones);
        adapter.setDropDownViewResource(R.layout.spinner_item); // Utiliza el mismo diseño para el desplegable
        operation.setAdapter(adapter);

        // Configura el listener para el botón
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtiene el operador seleccionado
                String selectedOperation = (String) operation.getSelectedItem();
                char operator = selectedOperation.charAt(0);

                // Llama a la función calculate() solo cuando el botón es presionado
                calculate(operator);
            }
        });
    }

    private void calculate(final char operator) {
        // Verifica si los campos están vacíos
        if (etNumber1.getText().toString().isEmpty() || etNumber2.getText().toString().isEmpty()) {
            tvResult.setText("Por favor, ingresa ambos números.");
            return;
        }

        final int number1 = Integer.parseInt(etNumber1.getText().toString());
        final int number2 = Integer.parseInt(etNumber2.getText().toString());

        // Realiza el cálculo en un nuevo hilo
        new Thread(new Runnable() {
            @Override
            public void run() {
                final int result = performCalculation(number1, number2, operator);

                // Actualiza el resultado en el UI thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvResult.setText("Resultado: " + result);
                    }
                });
            }
        }).start();
    }

    private int performCalculation(int number1, int number2, char operator) {
        int result = 0;
        switch (operator) {
            case '+':
                result = number1 + number2;
                break;
            case '-':
                result = number1 - number2;
                break;
            case '*':
                result = number1 * number2;
                break;
            case '/':
                if (number2 != 0) {
                    result = number1 / number2;
                } else {
                    // Actualiza el mensaje de error en la interfaz de usuario
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvResult.setText("Error: División por cero");
                        }
                    });
                    result = 0;
                }
                break;
        }
        return result;
    }
}