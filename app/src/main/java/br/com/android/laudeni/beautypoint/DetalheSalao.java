package br.com.android.laudeni.beautypoint;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetalheSalao extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhe_salao);


        Salao salao = (Salao) getIntent().getSerializableExtra("salao");

        TextView textNome = findViewById(R.id.dt_nome_salao);
        TextView txtEnd = findViewById(R.id.dt_end_salao);
        TextView txtDesc = findViewById(R.id.dt_desc_salao);
        TextView textViewTelefone = findViewById(R.id.dt_telefone_salao);

        // Adiciona o nome do Salao ao textview
        textNome.setText(salao.getNome());
        txtEnd.setText(salao.getLogradouro() + ", " + salao.getBairro());
        txtDesc.setText(salao.getDescricao());
        textViewTelefone.setText(salao.getTelefone());




        ImageView image = findViewById(R.id.dt_img_salao);

        String imgPath = salao.getImgUrl();
        if(imgPath != null && !imgPath.isEmpty()) {
            Picasso.get().load(imgPath).into(image);
        }

    }
}
