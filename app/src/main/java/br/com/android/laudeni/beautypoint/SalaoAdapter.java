package br.com.android.laudeni.beautypoint;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SalaoAdapter extends BaseAdapter {

    private Context context;
    private List<Salao> saloes;

    public SalaoAdapter(Context context) {
        this.context = context;
        this.saloes = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return saloes.size();
    }

    @Override
    public Object getItem(int position) {
        return this.saloes.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        // Cria uma instancia do objeto LayoutInflater
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Transforma um XML em um objeto do tipo View
        View v = layoutInflater.inflate(R.layout.activity_opcao_salao,null);

        // Pega o Teatro na posição atual
        Salao salao = this.saloes.get(position);

        TextView textViewNome = v.findViewById(R.id.nome_salao);
        textViewNome.setText(salao.getNome());

        TextView textViewDesc = v.findViewById(R.id.desc_salao);
        textViewDesc.setText(salao.getDescricao());

        TextView textViewEndereco = v.findViewById(R.id.end_salao);
        textViewEndereco.setText(salao.getLogradouro() + ", " + salao.getBairro());

        ImageView image = v.findViewById(R.id.img_salao);

        String imgPath = salao.getImgUrl();
        if(imgPath != null && !imgPath.isEmpty()) {
            Picasso.get().load(imgPath).into(image);
        }


        return v;
    }

    public void setSaloesList(List<Salao> saloes) {
        this.saloes = saloes;
    }
}
