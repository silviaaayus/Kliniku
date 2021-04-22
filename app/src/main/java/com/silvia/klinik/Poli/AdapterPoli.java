package com.silvia.klinik.Poli;

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
import com.silvia.klinik.BookingJadwal.BookingJadwalActivity;
import com.silvia.klinik.R;
import com.silvia.klinik.TinyDB;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterPoli extends RecyclerView.Adapter<AdapterPoli.ViewHolder> {

    Context context;
    List<ModelPoli> dataPoli;

    public AdapterPoli(List<ModelPoli> dataPoli) {
        this.dataPoli = dataPoli;
    }

    @NonNull
    @Override
    public AdapterPoli.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_poli,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPoli.ViewHolder holder, int position) {
        API api = new API();
        TinyDB tinyDB = new TinyDB(context);
        ModelPoli data = dataPoli.get(position);
        String id_poli = data.getId_poli();
        tinyDB.putString("keyIdPoli",id_poli);
        holder.nama_poli.setText(data.getNama_poli());
        holder.jam_operasional.setText("Jam Operasional :"+data.getJam_awal()+"-"+data.getJam_akhir());
        Picasso.get().load(api.URL_GAMBAR_POLI + data.getGambar()).into(holder.img_poli);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, BookingJadwalActivity.class);
                context.startActivity(i);


            }

        });

    }

    @Override
    public int getItemCount() {
        return dataPoli.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_poli;
        TextView nama_poli,jam_operasional;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            img_poli = itemView.findViewById(R.id.img_poli);
            nama_poli = itemView.findViewById(R.id.nama_poli);
            jam_operasional = itemView.findViewById(R.id.jamoperasional);
        }
    }
}
