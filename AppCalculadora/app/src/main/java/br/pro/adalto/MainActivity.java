package br.pro.adalto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etValor;
    private Button btnCalcular;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etValor = (EditText) findViewById(R.id.etValor);
        tvResultado = findViewById(R.id.tvResultado);
        btnCalcular = findViewById(R.id.btnMultiplicar);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcular();
            }
        });
    }
    private void calcular(){
        String sValor = etValor.getText().toString();
        if( sValor.isEmpty() ){
            // erro
        }else{
            double valor = Double.valueOf( sValor );
            double resultado = valor * 2 ;
            tvResultado.setText( String.valueOf(resultado) );
        }
    }


}