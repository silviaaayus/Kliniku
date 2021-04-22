package com.silvia.klinik.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.silvia.klinik.API;
import com.silvia.klinik.Artikel.AdapterArtikel;
import com.silvia.klinik.Artikel.ModelArtikel;
import com.silvia.klinik.R;
import com.silvia.klinik.TinyDB;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class HomeFragment extends Fragment {

    API api;

    private List<ModelKategori> dataKategori;
    private RecyclerView recycler_kategori;
    TinyDB tinyDB;
    TextView namaUser;

    ShimmerFrameLayout shimmerFrameLayout;
    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayout main;
    RelativeLayout koneksi,kosongArtikel;

    SliderLayout sliderLayout;

    private List<ModelArtikel> dataArtikel;
    private RecyclerView recycler_artikel;

    TextView txt_tanggal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);


        tinyDB = new TinyDB(getContext());

        api = new API();
        AndroidNetworking.initialize(getContext());

        namaUser = view.findViewById(R.id.txt_SelamatDatang);
        namaUser.setText("Selamat Datang, " + tinyDB.getString("keyNamaUser"));



        txt_tanggal = view.findViewById(R.id.txt_tanggal);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EEEE, dd MMMM yyyy  ");
        String formattedDate = df.format(c.getTime());
        txt_tanggal.setText("Hari "+formattedDate);

        kosongArtikel = view.findViewById(R.id.kosong_artikel);

        recycler_kategori = view.findViewById(R.id.recycler_kategori);
        recycler_kategori.setHasFixedSize(true);
        recycler_kategori.setLayoutManager(new GridLayoutManager(getContext(), 4));

        recycler_artikel = view.findViewById(R.id.recycler_artikel);
        recycler_artikel.setHasFixedSize(true);
        recycler_artikel.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        dataArtikel = new ArrayList<>();
        getArtikel();

        sliderLayout = view.findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(SliderLayout.Animations.FILL);
        sliderLayout.setScrollTimeInSec(1);
        setSliderViews();


        swipeRefreshLayout = view.findViewById(R.id.Home);
        shimmerFrameLayout = view.findViewById(R.id.shimmer_beranda);
        koneksi = view.findViewById(R.id.layout_koneksi);
        main = view.findViewById(R.id.mainLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataKategori();
            }
        });

        dataKategori = new ArrayList<>();
        AndroidNetworking.initialize(getContext());
        getDataKategori();


        return  view;
    }
    private void setLoading() {
        swipeRefreshLayout.setRefreshing(true);
        main.setVisibility(View.GONE);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        koneksi.setVisibility(View.GONE);
        shimmerFrameLayout.startShimmer();
    }

    private void setGagal() {
        swipeRefreshLayout.setRefreshing(false);
        shimmerFrameLayout.setVisibility(View.GONE);
        koneksi.setVisibility(View.VISIBLE);
        main.setVisibility(View.GONE);
        shimmerFrameLayout.stopShimmer();
    }

    private void setSukses() {
        swipeRefreshLayout.setRefreshing(false);
        shimmerFrameLayout.setVisibility(View.GONE);
        koneksi.setVisibility(View.GONE);
        main.setVisibility(View.VISIBLE);
        shimmerFrameLayout.stopShimmer();
    }

    public void getDataKategori() {
        setLoading();
        AndroidNetworking.get(api.URL_Kategori)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            Log.d("tampilmenu", "response:" + response);
                            JSONArray res = response.getJSONArray("res");
                            setSukses();
                            dataKategori.clear();
                            Gson gson = new Gson();
                            for (int i = 0; i < res.length(); i++) {
                                JSONObject data = res.getJSONObject(i);
                                ModelKategori Isi = gson.fromJson(data + "", ModelKategori.class);
                                dataKategori.add(Isi);
                            }
                            AdapterKategori adapter = new AdapterKategori(dataKategori);
                            recycler_kategori.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        setGagal();

                        Log.e("tampil menu", "response:" + anError);
                    }
                });
    }

    private void setSliderViews() {
        for (int i = 0; i <= 2; i++) {
            SliderView sliderView = new SliderView(getContext());
            switch (i) {
                case 0:
                    sliderView.setImageUrl(api.URL_SLIDER + "slide1.jpg");
                    break;
                case 1:
                    sliderView.setImageUrl(api.URL_SLIDER + "slide2.png");
                    break;
                case 2:
                    sliderView.setImageUrl(api.URL_SLIDER + "slide3.jpg");
                    break;

            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderLayout.addSliderView(sliderView);
        }
    }

    private void getArtikel() {
        AndroidNetworking.get(api.URL_ARTIKEL)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            Log.d("tampil barang","response:"+response);
                            if (response.getString("status").equalsIgnoreCase("gagal")){
                                kosongArtikel.setVisibility(View.VISIBLE);
                                setSukses();
                            }else {
                                kosongArtikel.setVisibility(View.GONE);
                                setSukses();
                                JSONArray res = response.getJSONArray("res");
                                dataArtikel.clear();
                                Gson gson = new Gson();
                                for (int i=0; i<res.length(); i++){
                                    JSONObject data = res.getJSONObject(i);

                                    ModelArtikel Isi = gson.fromJson(data + "", ModelArtikel.class);
                                    dataArtikel.add(Isi);
                                }
                            }
                            AdapterArtikel adapter = new AdapterArtikel(dataArtikel);
                            recycler_artikel.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        setGagal();
                        Log.e("tampil menu","response:"+anError);
                    }
                });
    }

}