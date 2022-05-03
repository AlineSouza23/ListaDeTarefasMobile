package br.senai.sp.cotia.todolistapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.model.Tarefa;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>{
//Lista de tarefas
    private List<Tarefa> tarefas;

    //variável para o context, porque iremos fazer algumas coisas relacionadas ao content
    private Context context;

    //construtor para receber os valores
    public TarefaAdapter(List<Tarefa> lista, Context contexto){
        this.tarefas = lista;
        this.context = contexto;
    }


    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_tarefas, parent, false);

        //retorna um novo viewHolder com a view
        return new TarefaViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
    //obtem a taerfa pela position
        Tarefa t = tarefas.get(position);
        //exibe o titulo da tarefa no text view
        holder.tituloMsg.setText(t.getTitule());
        //se estiver concluida, local tambem se fazer se ela esta realizada ou não
        if(t.isConcluida()){
            holder.statusMsg.setText("Concluida");
            holder.statusMsg.setBackgroundColor(context.getResources().getColor(R.color.finalizada));
        }else{
            holder.statusMsg.setText("Aberta");
            holder.statusMsg.setBackgroundColor(context.getResources().getColor(R.color.amarelo));

        }

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        holder.dataMsg.setText(formatador.format(t.getDataPrevista()));


    }

    @Override
    //retona a quantidade de elementos a serem exibidos na lista
    public int getItemCount() {
        if(tarefas != null) {
            return tarefas.size();
        }
        return 0;
    }

    //classe viweHolder dentro do adapter, é uma classe inerna
    class TarefaViewHolder extends RecyclerView.ViewHolder{


        //variaveis para representar os componentes do xml
        TextView tituloMsg, dataMsg, statusMsg;

        public TarefaViewHolder(View view){
            //chama o construtor da superclasse

            super(view);
            //passar para as variaveis, os componentes do xml
            tituloMsg = view.findViewById(R.id.titulomsg);
            dataMsg = view.findViewById(R.id.datamsg);
            statusMsg = view.findViewById(R.id.status);


        }
    }
}
