package com.silvia.klinik.BookingJadwal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.silvia.klinik.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterSubBookingJadwal extends RecyclerView.Adapter<AdapterSubBookingJadwal.ViewHolder> {
    Context context;
    List<ModelSubBookingJadwal> dataBooking;

    public AdapterSubBookingJadwal(List<ModelSubBookingJadwal> dataBooking) {
        this.dataBooking = dataBooking;
    }

    @NonNull
    @Override
    public AdapterSubBookingJadwal.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_booking_subjadwal,parent,false);
        AdapterSubBookingJadwal.ViewHolder holder = new AdapterSubBookingJadwal.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSubBookingJadwal.ViewHolder holder, int position) {
        ModelSubBookingJadwal data = dataBooking.get(position);
        String tanggal = data.getTanggal();
        String tanggal_sistem = data.getTanggal_sistem();
        String id_jadwal = data.getId_jadwal();
        String jam_awal = data.getJam_awal();
        String jam_akhir = data.getJam_akhir();
        String maksimal = data.getMaksimal();
        String waktu_layanan = data.getWaktu_layanan();
        int sudah_terdaftar = data.getSudah_terdaftar();

        holder.txtJamBerobat.setText("Jam "+jam_awal+" s/d "+jam_akhir);
        holder.txtTerdaftar.setText(""+sudah_terdaftar);


        if (Integer.parseInt(maksimal) == sudah_terdaftar) {
            holder.itemView.setClickable(false);
            holder.habis.setVisibility(View.VISIBLE);

        }
        else {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, AmbilNoAntrianActivity.class);
                    i.putExtra("tanggal", tanggal);
                    i.putExtra("tanggal_sistem", tanggal_sistem);
                    i.putExtra("id_jadwal", id_jadwal);
                    i.putExtra("jam_awal", jam_awal);
                    i.putExtra("jam_akhir", jam_akhir);
                    i.putExtra("waktu_layanan", waktu_layanan);
                    i.putExtra("maksimal", maksimal);
                    context.startActivity(i);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return dataBooking.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView
                txtJamBerobat, txtTerdaftar;
        RelativeLayout habis;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context= itemView.getContext();
            txtJamBerobat = itemView.findViewById(R.id.txtJamBerobat);
            txtTerdaftar = itemView.findViewById(R.id.txtTerdaftar);
            habis = itemView.findViewById(R.id.habisLayout);
        }
    }
}
