package br.pro.appchamada;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome, etRA;
    private Button btnSalvar;
    private Aluno aluno;
    private String acao;
    private static final String INSERIR = "inserir";
    private static final String EDITAR = "editar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = findViewById(R.id.etNome);
        etRA = findViewById(R.id.etRA);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        acao = getIntent().getExtras().getString("acao");
        if( acao.equals( EDITAR ) ){
            carregarFormulario();
        }

    } // fim do onCreate()


    private void carregarFormulario(){
        int idAluno = (int) getIntent().getLongExtra("idAluno", 0);
        Log.i("erroId", "Id: "+ idAluno);
        aluno = AlunoDAO.getAlunoById(this, idAluno );
        etNome.setText( aluno.getNome() );
        etRA.setText( aluno.getRa() );
    }


    private void salvar(){

        String nome = etNome.getText().toString();
        if ( nome.isEmpty() ){
            Toast.makeText(this,
                    "O campo nome deve ser preenchido!" ,
                    Toast.LENGTH_LONG
                    ).show();
        }else {
            if ( acao.equals( INSERIR ) ) {
                aluno = new Aluno();
            }

            aluno.setNome( nome );
            aluno.setRa( etRA.getText().toString() );

            if( acao.equals(EDITAR)){
                AlunoDAO.editar(this, aluno);
                finish();
            }else {
                AlunoDAO.inserir(this, aluno);
                etNome.setText( "" );
                etRA.setText( "" );
                aluno = null;
            }
        }
    }

}










