package com.silvia.klinik.Home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.silvia.klinik.API;
import com.silvia.klinik.Artikel.ArtikelActivity;
import com.silvia.klinik.KotakSaran.KotakSaranActivity;
import com.silvia.klinik.Layanan.LayananActivity;
import com.silvia.klinik.Poli.PoliActivity;
import com.silvia.klinik.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterKategori extends RecyclerView.Adapter<AdapterKategori.ViewHolder> {

    Context context;
    List<ModelKategori> dataKategori;

    public AdapterKategori(List<ModelKategori> dataKategori) {
        this.dataKategori = dataKategori;
    }

    @NonNull
    @Override
    public AdapterKategori.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_kategori,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKategori.ViewHolder holder, int position) {
        API api = new API();
        ModelKategori data =  dataKategori.get(position);
        String id_kategori =  data.getId_kategori();
        holder.txt_kategori.setText(data.getKategori());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id_kategori.equalsIgnoreCase("1")){
                    Intent i = new Intent(context, PoliActivity.class);
                    context.startActivity(i);
                }else if(id_kategori.equalsIgnoreCase("2")){
                    Intent i = new Intent(context, LayananActivity.class);
                    context.startActivity(i);
                }else if (id_kategori.equalsIgnoreCase("3")){
                    Intent i = new Intent(context, ArtikelActivity.class);
                    context.startActivity(i);
                }else if (id_kategori.equalsIgnoreCase("4")){
                    Intent i = new Intent(context, KotakSaranActivity.class);
                    context.startActivity(i);
                }
            }
        });
        Picasso.get().load(api.URL_GAMBAR + data.getGambar()).into(holder.gambar);

    }

    @Override
    public int getItemCount() {
        return dataKategori.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_kategori;
        ImageView gambar;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            txt_kategori =itemView.findViewById(R.id.txt_kategori);
            gambar = itemView.findViewById(R.id.img_kategori);
            cardView = itemView.findViewById(R.id.cardview_menu);
        }
    }
}
