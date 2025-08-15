package com.example.numero;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText txtNumber;

    private Button btnResolver;

    private TextView respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtNumber = findViewById(R.id.txtNumber);
        btnResolver = findViewById(R.id.btnEnviar);
        respuesta = findViewById(R.id.rptaRaiz);


        btnResolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager ocultarTeclado = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (getCurrentFocus() != null) {
                    ocultarTeclado.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }

                String numero = txtNumber.getText().toString();

                try {
                    Double numeroD = convertirDouble(numero);
                    Double raiz = raizCuadrada(numeroD);
                    respuesta.setText("La raíz es: " + String.valueOf(raiz));
                }catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Ingrese un número válido", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    private Double convertirDouble(String texto) throws NumberFormatException {
        return Double.parseDouble(texto);
    }

    private Double raizCuadrada(Double numero){
        return Math.sqrt(numero);
    }

}