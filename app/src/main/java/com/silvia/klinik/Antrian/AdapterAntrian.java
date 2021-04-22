package com.silvia.klinik.Antrian;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.silvia.klinik.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class AdapterAntrian extends RecyclerView.Adapter<AdapterAntrian.ViewHolder> {

    Context context;
    List<ModelAntrian> dataAntrian;

    public AdapterAntrian(List<ModelAntrian> dataAntrian) {
        this.dataAntrian = dataAntrian;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_antrian,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

   
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelAntrian data = dataAntrian.get(position);
        String id_pasien = data.getId_pasien();
        String id_user = data.getCreate_by();


        holder.status.setText(data.getStatus());
        holder.tgl_antrian.setText(data.getTanggal());
        holder.jadwalantrian.setText("Jam "+data.getJam_awal()+" - "+data.getJam_akhir());
        holder.namapasienantrian.setText(data.getNama_pasien());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, NoAntrianActivity.class);
                i.putExtra("tanggal",data.getTanggal());
                i.putExtra("jam_awal",data.getJam_awal());
                i.putExtra("jam_akhir",data.getJam_akhir());
                i.putExtra("id_antrian",data.getId_antrian());
                i.putExtra("nama_pasien",data.getNama_pasien());
                i.putExtra("nohp_pasien",data.getNohp_pasien());
                i.putExtra("email_pasien",data.getEmail_pasien());
                i.putExtra("alamat_pasien",data.getAlamat_pasien());
                i.putExtra("nik",data.getNik());
                i.putExtra("ttl",data.getTtl());
                i.putExtra("jekel",data.getJekel());
                i.putExtra("nama_poli",data.getNama_poli());
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataAntrian.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_antrian;
        TextView tgl_antrian,jadwalantrian,namapasienantrian,status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            img_antrian = itemView.findViewById(R.id.img_antrian);
            tgl_antrian = itemView.findViewById(R.id.tgl_antrian);
            jadwalantrian = itemView.findViewById(R.id.jadwalantrian);
            namapasienantrian= itemView.findViewById(R.id.namapasienantrian);
            status= itemView.findViewById(R.id.status);
        }
    }
}
