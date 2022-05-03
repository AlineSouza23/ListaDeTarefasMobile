package br.senai.sp.cotia.todolistapp.fragment;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.database.AppDatabase;
import br.senai.sp.cotia.todolistapp.databinding.FragmentCadTarefaBinding;
import br.senai.sp.cotia.todolistapp.databinding.FragmentCadTarefaBinding;
import br.senai.sp.cotia.todolistapp.model.Tarefa;


public class CadTarefaFragment extends Fragment {

    private FragmentCadTarefaBinding binding;

    DatePickerDialog datePicker;
    //variável para ano, mes e dia
    int year, month, day;
    //variável para data atual
    Calendar dataAtual;
    //variável para a data formatada
    String dataEscolhida = "";
    //variável para acessar a database
    AppDatabase database;

    @Override
    public  View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //instanciar a appda
        database = AppDatabase.getDatabase(getActivity());


        binding = FragmentCadTarefaBinding.inflate(inflater, container, false);

        //descobrir a data atual
        dataAtual = Calendar.getInstance();

        year = dataAtual.get(Calendar.YEAR);
        month = dataAtual.get(Calendar.MONTH);
        day = dataAtual.get(Calendar.DAY_OF_MONTH);



        //instanciar o datepicke
        datePicker = new DatePickerDialog(getContext(), (view, ano, mes, dia) ->{
            //cai aqui toda vez que clica no ok do DatePickerDialog
            //passa para as variaveis globais
            //variaveç roxinha é a global e a branquinha local

            year = ano;
            month = mes;
            day = dia;
            //formata a string da dataEscolhida
            dataEscolhida = String.format("%02d/%02d/%04d", day, month + 1,year);
            // jogar a string no botão
            binding.iddata.setText(dataEscolhida);

        },year,month, day);


        //listener do botão de data
        binding.iddata.setOnClickListener(v -> {
            //abre o datepicker
            datePicker.show();

        });
        //listener do botçao salvar
        binding.idbotao.setOnClickListener(v -> {
            // validar os campos
            //o iddata no dele é editTitulo
            if (binding.titulo.getText().toString().isEmpty()) {
                binding.titulo.setError(getString(R.string.dia));
                Toast.makeText(getContext(), R.string.dia, Toast.LENGTH_SHORT).show();
                binding.titulo.requestFocus();

                }else if (dataEscolhida.isEmpty()){
                binding.titulo.setError(getString(R.string.dia));
                    Toast.makeText(getContext(), R.string.dia, Toast.LENGTH_SHORT).show();
                }else{
                    //criar um objeto tarefa
                    Tarefa tarefa = new Tarefa();
                    //popular a tarefa
                    tarefa.setTitule(binding.titulo.getText().toString());
                    tarefa.setDescricao(binding.descricao.getText().toString());
                    //cria um calendar e popula com a data que foi selecionada
                    Calendar dataRealizacao = Calendar.getInstance();
                    dataRealizacao.set(year,month,day);
                    //comparar com a data atual caso queira.....
                    //passar para a terefa os milessegundos da data
                    tarefa.setDataPrevista(dataRealizacao.getTimeInMillis());
                    //criar um calendar para a data atual
                    Calendar dataAtual = Calendar.getInstance();
                    tarefa.setDataCriacao(dataAtual.getTimeInMillis());
                        //salvar a tarefa no BD

                    new InsertTarefa().execute(tarefa);

                }
        });
        //retorna a view rais do binding
        return binding.getRoot();
    }
    //classe para a Task de Inserir Tarefa
    private class InsertTarefa extends AsyncTask <Tarefa, Void, String> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            Log.w("Passou", "no onpreexecute");
        }
        @Override
        protected  void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
            Log.w("Passou", "no OnProcessUpdade");

            }
            @Override
            protected String doInBackground(Tarefa... tarefas){
              Log.w("Passou", "no doInBackgroud");
              // extrair a tarefa do vetor de tarefas
                Tarefa t = tarefas[0];
                try {
                    //tenta inserir
                    database.getTarefaDao().insert(t);
                    //retorna ok caso tenha passado sem erro
                    return "okaynnnn";
                }catch (Exception e){
                    e.printStackTrace();
                    //retorna a mensagem de erro caso tenha dado erro
                    return e.getMessage();
                }


            }
            @Override
        protected void  onPostExecute(String resultado){
           if(resultado.equals("ok")){
               Log.w("RESULTADO", "iuuupiii");
               //aciona o botão de voltar
               getActivity().onBackPressed();
           }else{
               Log.w("RESULTADO", resultado);
               Toast.makeText(getContext(), "DEU RUIM"+resultado, Toast.LENGTH_SHORT).show();

           }

        }

    }

}
