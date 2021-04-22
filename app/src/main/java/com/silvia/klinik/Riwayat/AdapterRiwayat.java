package com.silvia.klinik.Riwayat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.silvia.klinik.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterRiwayat extends RecyclerView.Adapter<AdapterRiwayat.ViewHolder> {

    Context context;
    List<ModelRiwayat> dataRiwayat;

    public AdapterRiwayat(List<ModelRiwayat> dataRiwayat) {
        this.dataRiwayat = dataRiwayat;
    }

    @NonNull
    @Override
    public AdapterRiwayat.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_riwayat,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRiwayat.ViewHolder holder, int position) {

        ModelRiwayat data = dataRiwayat.get(position);
        String id_pasien = data.getId_pasien();
        String id_rekam = data.getId_rekam_medis();


        Locale localeId = new Locale("in", "ID");
        final NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeId);

        holder.tanggal_pemeriksaan.setText(data.getTanggal_pemeriksaan());
        holder.nama_pasien.setText(data.getNama_pasien());
        holder.dokter.setText(data.getNama_dokter());
        holder.biaya.setText(formatRupiah.format((double)Integer.valueOf(data.getTotal())));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailRiwayatActivity.class);
                i.putExtra("id_pasien", ""+id_pasien);
                i.putExtra("id_rekam_medis", ""+id_rekam);
                i.putExtra("nama_pasien",data.getNama_pasien());
                i.putExtra("nik",data.getNik());
                i.putExtra("ttl",data.getTtl());
                i.putExtra("jekel",data.getJekel());
                i.putExtra("alamat_pasien",data.getAlamat_pasien());
                i.putExtra("nohp_pasien",data.getNohp_pasien());
                i.putExtra("email_pasien",data.getEmail_pasien());
                i.putExtra("det_rm",data.getDet_rm());
                i.putExtra("nama_dokter",data.getNama_dokter());
                i.putExtra("tanggal_pemeriksaan",data.getTanggal_pemeriksaan());
                i.putExtra("keluhan",data.getKeluhan());
                i.putExtra("gigi",data.getGigi());

                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataRiwayat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tanggal_pemeriksaan,nama_pasien,dokter,biaya;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context= itemView.getContext();
            tanggal_pemeriksaan = itemView.findViewById(R.id.tanggal_pemeriksaan);
            nama_pasien = itemView.findViewById(R.id.nama_pasien);
            dokter = itemView.findViewById(R.id.dokter);
            biaya = itemView.findViewById(R.id.total);
        }
    }
}
