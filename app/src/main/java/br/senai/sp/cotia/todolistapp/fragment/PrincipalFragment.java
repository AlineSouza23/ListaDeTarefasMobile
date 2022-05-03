package br.senai.sp.cotia.todolistapp.fragment;



import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.adapter.TarefaAdapter;
import br.senai.sp.cotia.todolistapp.database.AppDatabase;
import br.senai.sp.cotia.todolistapp.databinding.FragmentPrincipalBinding;
import br.senai.sp.cotia.todolistapp.model.Tarefa;


public class PrincipalFragment extends Fragment {
private FragmentPrincipalBinding binding;
//variavel para database

    //variável para database
    private AppDatabase database;
    //variável para o Adapter
    private TarefaAdapter adapter;
    //vaável para lista
    private List<Tarefa> tarefas;

@Override
    public  View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    binding = FragmentPrincipalBinding.inflate(inflater, container, false);
    //clique no botão de adicionar tarefa
    binding.btnAddTarefa.setOnClickListener(v ->{
        NavHostFragment.findNavController(PrincipalFragment.this).navigate(R.id.action_fragmentPrincipal_to_fragment);
    });

    //retorna a view raiz do binding
    return binding.getRoot();
}

}