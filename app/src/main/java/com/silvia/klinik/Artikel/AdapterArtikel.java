package com.silvia.klinik.Artikel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.silvia.klinik.API;
import com.silvia.klinik.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterArtikel extends RecyclerView.Adapter<AdapterArtikel.ViewHolder> {

    Context context;
    List<ModelArtikel> dataArtikel;

    public AdapterArtikel(List<ModelArtikel> dataArtikel) {
        this.dataArtikel = dataArtikel;
    }

    @NonNull
    @Override
    public AdapterArtikel.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_artikel,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterArtikel.ViewHolder holder, int position) {
        API api = new API();
        ModelArtikel data = dataArtikel.get(position);
        String id_artikel = data.getId_artikel();
        holder.judulArtikel.setText(data.getJudul_artikel());
        holder.penulis.setText(data.getPenulis());
        holder.tanggalArtikel.setText(data.getTanggal_artikel());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,DetailArtikelActivity.class);
                i.putExtra("judul_artikel",data.getJudul_artikel());
                i.putExtra("penulis",data.getPenulis());
                i.putExtra("isi_artikel",data.getIsi_artikel());
                i.putExtra("tanggal_artikel",data.getTanggal_artikel());
                i.putExtra("img_artikel",data.getImg_artikel());
                context.startActivity(i);
            }
        });
        Picasso.get().load(api.URL_GAMBAR_ARTIKEL + data.getImg_artikel()).into(holder.img_artikel);

    }

    @Override
    public int getItemCount() {
        return dataArtikel.size()
                ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_artikel;
        TextView judulArtikel,penulis,tanggalArtikel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            img_artikel = itemView.findViewById(R.id.img_artikel);
            judulArtikel = itemView.findViewById(R.id.judulArtikel);
            penulis = itemView.findViewById(R.id.penulis);
            tanggalArtikel = itemView.findViewById(R.id.tanggalArtikel);
        }
    }
}
