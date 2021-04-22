package com.silvia.klinik.BookingJadwal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.silvia.klinik.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterBookingJadwal extends RecyclerView.Adapter<AdapterBookingJadwal.ViewHolder>{

    Context context;
    List<ModelBookingJadwal> dataBooking;

    public AdapterBookingJadwal(List<ModelBookingJadwal> dataBooking) {
        this.dataBooking = dataBooking;
    }

    @NonNull
    @Override
    public AdapterBookingJadwal.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_booking,parent,false);
        AdapterBookingJadwal.ViewHolder holder = new AdapterBookingJadwal.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBookingJadwal.ViewHolder holder, int position) {

        ModelBookingJadwal data = dataBooking.get(position);
        String tanggal = data.getTanggal();
        String tanggal_sistem = data.getTanggal_sistem();

        holder.tanggal_antrian.setText(tanggal);



        ArrayList<ModelSubBookingJadwal> modelSub;
        modelSub = data.getModelSubBookingJadwal();

        AdapterSubBookingJadwal mAdapterSub = new AdapterSubBookingJadwal(modelSub);
        holder.recyclerSubAntrian.setAdapter(mAdapterSub);

    }

    @Override
    public int getItemCount() {
        return dataBooking.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView
                tanggal_antrian;
        RecyclerView
                recyclerSubAntrian;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context= itemView.getContext();
            tanggal_antrian = itemView.findViewById(R.id.tanggal_antrian);
            recyclerSubAntrian = itemView.findViewById(R.id.recyclerSubAntrian);
        }
    }
}
