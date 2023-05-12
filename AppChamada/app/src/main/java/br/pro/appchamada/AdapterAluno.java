package br.pro.appchamada;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterAluno extends BaseAdapter {

    private Context contexto;
    private List<Aluno> listaAlunos;
    private LayoutInflater inflater;

    public AdapterAluno(Context context, List<Aluno> alunos){
        this.contexto = context;
        this.listaAlunos = alunos;
        this.inflater = LayoutInflater.from( context );
    }

    @Override
    public int getCount() {
        return listaAlunos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaAlunos.get( position );
    }

    @Override
    public long getItemId( int postion ) {
        return listaAlunos.get( postion ).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemSuporte item;
        if( convertView == null ){
            convertView = inflater.inflate(R.layout.layout_item, null);
            item = new ItemSuporte();
            item.tvNome = convertView.findViewById(R.id.item_tvNome);
            item.tvRA = convertView.findViewById(R.id.item_tvRA);
            item.layout = convertView.findViewById(R.id.item_layout);
            convertView.setTag( item );
        }else {
            item = (ItemSuporte) convertView.getTag();
        }

        Aluno aluno = listaAlunos.get(position);
        item.tvNome.setText( aluno.getNome() );
        item.tvRA.setText( aluno.getRa() );

        item.layout.setBackgroundColor(Color.WHITE);
        if( position % 2 == 0){
            item.layout.setBackgroundColor(Color.rgb(240, 240, 240));
        }

        return convertView;
    }


    private class ItemSuporte{
        TextView tvNome, tvRA;
        LinearLayout layout;
    }

}
