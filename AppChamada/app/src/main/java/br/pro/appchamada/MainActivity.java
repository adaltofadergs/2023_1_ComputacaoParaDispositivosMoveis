package br.pro.appchamada;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnAdicionar;
    private ListView lvAlunos;
    private List<Aluno> listAlunos;

    //private ArrayAdapter adapter;
    private AdapterAluno adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvAlunos = findViewById(R.id.lvAlunos);
        btnAdicionar = findViewById( R.id.btnAdd );

        lvAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                deletar( position );
                return true;
            }
        });

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,
                        FormularioActivity.class);
                i.putExtra("acao", "inserir" );
                startActivity( i );
            }
        });

        lvAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("acao", "editar");
            //    int idAluno = listAlunos.get( position ).getId();
                Log.i("erroId", "Id antes: " + id);
                intent.putExtra("idAluno", id);
                startActivity( intent );

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        carregarAlunos();
    }

    private void carregarAlunos(){
        listAlunos = AlunoDAO.getAlunos(this);
        if( listAlunos.isEmpty() ){
            lvAlunos.setEnabled(false);
            //String[] listaVazia = {"Lista Vazia!"};
            //adapter = new  ArrayAdapter(this, android.R.layout.simple_list_item_1, listaVazia);
            Aluno fake = new Aluno(0, "Lista Vazia", "");
            listAlunos.add( fake );
            adapter = new AdapterAluno(this, listAlunos);
        }else {
            lvAlunos.setEnabled(true);
            //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listAlunos);
            adapter = new AdapterAluno(this, listAlunos);
        }
        lvAlunos.setAdapter( adapter );
    }


    private void deletar(int posicao){
        Aluno aluno = listAlunos.get( posicao );
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir");
        alerta.setIcon(android.R.drawable.ic_dialog_alert);
        alerta.setMessage("Confirma a exclusão de " + aluno.getNome()+"? ");
        alerta.setNeutralButton("Não", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AlunoDAO.excluir(MainActivity.this, aluno.getId() );
                carregarAlunos();
            }
        });
        alerta.show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Ligar...");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if( item.toString().equals("Ligar...") ){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setTitle("Ligar");

            EditText etFone = new EditText(this);
            etFone.setHint("Digite o telefone:");
            etFone.setTextColor(Color.BLUE);
            etFone.setInputType( InputType.TYPE_CLASS_PHONE );

            alerta.setView( etFone );

            alerta.setNeutralButton("Ligar Direto", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Uri uri = Uri.parse("tel: " + etFone.getText().toString());
                    Intent intent = new Intent(Intent.ACTION_CALL, uri );
                    startActivity( intent );
                }
            });

            alerta.setPositiveButton("Discagem", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Uri uri = Uri.parse("tel: " + etFone.getText().toString());
                    Intent intent = new Intent(Intent.ACTION_DIAL, uri );
                    startActivity( intent );
                }
            });
            alerta.show();
        }
        return super.onOptionsItemSelected(item);
    }
}















