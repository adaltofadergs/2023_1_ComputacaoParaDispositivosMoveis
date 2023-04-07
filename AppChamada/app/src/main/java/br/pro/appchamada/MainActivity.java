package br.pro.appchamada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnAdicionar;
    private ListView lvAlunos;
    private List<Aluno> listAlunos;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvAlunos = findViewById(R.id.lvAlunos);

        btnAdicionar = findViewById( R.id.btnAdd );

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,
                        FormularioActivity.class);
                i.putExtra("acao", "inserir" );
                startActivity( i );
            }
        });

    }

}





