package com.silvia.klinik.Layanan;

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

public class AdapterLayanan extends RecyclerView.Adapter<AdapterLayanan.ViewHolder> {

    Context context;
    List<ModelLayanan> dataLayanan;

    public AdapterLayanan(List<ModelLayanan> dataLayanan) {
        this.dataLayanan = dataLayanan;
    }

    @NonNull
    @Override
    public AdapterLayanan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layanan,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLayanan.ViewHolder holder, int position) {
        API api = new API();
        ModelLayanan data = dataLayanan.get(position);
        String id_layanan = data.getId_layanan();
        holder.txt_layanan.setText(data.getLayanan());
//        Locale localeId = new Locale("in", "ID");
//        final NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeId);
//      holder.tarif_layanan.setText(formatRupiah.format((double)Integer.valueOf(data.getTarif_layanan())));
        holder.tarif_layanan.setText("Rp."+data.getTarif_layanan());

        holder.img_layanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,DetailLayananActivity.class);
                i.putExtra("id_layanan",data.getId_layanan());
                i.putExtra("layanan",data.getLayanan());
                i.putExtra("deskripsi",data.getDeskripsi());
                i.putExtra("tarif_layanan",data.getTarif_layanan());
                i.putExtra("img_layanan",data.getImg_layanan());
                context.startActivity(i);
            }
        });

        Picasso.get().load(api.URL_GAMBAR_LAYANAN + data.getImg_layanan()).into(holder.img_layanan);

    }

    @Override
    public int getItemCount() {
        return dataLayanan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_layanan, tarif_layanan;

        ImageView img_layanan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();
            txt_layanan = itemView.findViewById(R.id.namaLayanan);
            tarif_layanan = itemView.findViewById(R.id.tarifLayanan);
            img_layanan = itemView.findViewById(R.id.img_layanan);
        }
    }
}
