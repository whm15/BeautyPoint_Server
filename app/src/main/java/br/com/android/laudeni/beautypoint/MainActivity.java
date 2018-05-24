package br.com.android.laudeni.beautypoint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    // Ip(localhost) de acesso ao servidor
    private static final String BASE_URL = "http://10.0.0.109:8882/";
    private List<Salao> saloes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SalaoAdapter adapter = new SalaoAdapter(MainActivity.this);

        ListView listView = (ListView) findViewById(R.id.lista);

        // Captura o evento de clique em um item da lista
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // pega o objeto salao na posicão clicada
                Salao salao = saloes.get(position);

                Intent intent = new Intent(MainActivity.this, DetalheSalao.class);
                // Adiciona o salao clicado à intent
                intent.putExtra("salao", salao);

                // Abre a activity DetalheSalao
                startActivity(intent);
            }
        });

        // Adiciona o adapter ao listview
        listView.setAdapter(adapter);

        // Configuração para acessar o Webservice
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Cria uma instancia do SalaoWebService
        ISalaoWS webService = retrofit.create(ISalaoWS.class);

        // Cria a chamada ao end point /saloes
        Call<List<Salao>> call = webService.getSaloes();
        call.enqueue(new Callback<List<Salao>>() {
            @Override
            public void onResponse(Call<List<Salao>> call, Response<List<Salao>> response) {
                // Pega a lista de saloes na resposta da requisição
                saloes = response.body();

                // adiciona a lista de saloes ao adapter
                adapter.setSaloesList(saloes);
                // notifica o adapter que os dados foram alterados(atualiza o listview)
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Salao>> call, Throwable t) {
                // Imprime um log caso a requisição falhe
                Log.e("MainActivity", t.toString());
            }
        });

    }
}
