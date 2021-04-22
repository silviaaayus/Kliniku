package com.silvia.klinik.BookingJadwal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.silvia.klinik.API;
import com.silvia.klinik.Antrian.AntrianActivity;
import com.silvia.klinik.Antrian.NoAntrianActivity;
import com.silvia.klinik.MainActivity;
import com.silvia.klinik.R;
import com.silvia.klinik.TinyDB;
import com.silvia.klinik.databinding.ActivityAmbilNoAntrianBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AmbilNoAntrianActivity extends AppCompatActivity {
    private ActivityAmbilNoAntrianBinding binding;

    API api;
    String[] jekel = {"Laki-Laki","Perempuan"};
    String tempJekel;
    String tanggal,id_jadwal,jam_awal,jam_akhir,maksimal,sudah_terdaftar,ttl,tanggal_sistem;
    String nik;
    String nik_pasien="",nama="", jekel_pasien="",alamat="", no_hp="", email="";
    TinyDB tinyDB;
    String id,id_poli;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityAmbilNoAntrianBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_ambil_no_antrian);
        setContentView(binding.getRoot());

        Intent i = new Intent(getIntent());
        tanggal= i.getStringExtra("tanggal");
        tanggal_sistem= i.getStringExtra("tanggal_sistem");
        id_jadwal = i.getStringExtra("id_jadwal");
        jam_awal = i.getStringExtra("jam_awal");
        jam_akhir = i.getStringExtra("jam_akhir");
        maksimal = i.getStringExtra("maksimal");
        sudah_terdaftar = i.getStringExtra("sudah_terdaftar");


        tinyDB = new TinyDB(this);
        id = tinyDB.getString("keyIdUser");
        id_poli = tinyDB.getString("keyIdPoli");
        Log.e("idpoli", id_poli);

        api = new API();
        AndroidNetworking.initialize(this);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        binding.pendaftaran.formDaftar.setVisibility(View.GONE);
        binding.layoutCari.cariPasien.setVisibility(View.GONE);


        binding.toolbar.ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.toolbar.tvToolbar.setText("Ambil No Antrian");
        binding.tanggalNoantrian.setText(tanggal);
        binding.jamNoantrian.setText("Jam "+jam_awal+"-"+jam_akhir);


//Spinner
        ArrayAdapter<String> AJekel= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,jekel);
        AJekel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.pendaftaran.edtJekelPasien.setAdapter(AJekel);

        binding.pendaftaran.edtJekelPasien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tempJekel = jekel[i];
                Log.e("spinner",tempJekel);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

//RadioButton
        binding.rdPilihPasien.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(binding.rdPasienBaru.isChecked())
                {
                    binding.pendaftaran.formDaftar.setVisibility(View.VISIBLE);
                    binding.layoutCari.cariPasien.setVisibility(View.GONE);
                    resetValue();
                }
                else
                {
                    binding.pendaftaran.formDaftar.setVisibility(View.GONE);
                    binding.layoutCari.cariPasien.setVisibility(View.VISIBLE);
                    binding.layoutCari.detailCariPasien.setVisibility(View.GONE);
                    resetValue();
                    binding.layoutCari.btnCaripasien.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getCariPasien();

                        }
                    });


                }

            }
        });

        binding.layoutCari.cariNik.getText().toString();
        binding.pendaftaran.edtNikPasien.getText().toString();
        binding.pendaftaran.edtNamaPasien.getText().toString();
        binding.pendaftaran.edtAlamatpasien.getText().toString();
        binding.pendaftaran.edtNohp.getText().toString();
        binding.pendaftaran.edtEmail.getText().toString();
        binding.pendaftaran.edtTtlPasien.getText().toString();

        binding.pendaftaran.edtTtlPasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });


        binding.layoutCari.nikCari.getText().toString();
        binding.layoutCari.nama.getText().toString();
        binding.layoutCari.alamatCari.getText().toString();
        binding.layoutCari.nohp.getText().toString();
        binding.layoutCari.emailCari.getText().toString();


//
        detailAntrian();
        aksiTambah();
    }
    private void resetValue(){
        nik_pasien = "";
        nama = "";
        jekel_pasien = "";
        alamat = "";
        no_hp = "";
        email = "";
    }
    private void showDateDialog(){

        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                binding.pendaftaran.edtTtlPasien.setText(dateFormatter.format(newDate.getTime()));

            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        datePickerDialog.show();
    }



    public void aksiTambah(){
        binding.btnAntrian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.rdPasienBaru.isChecked()) {
                    String niks = binding.pendaftaran.edtNikPasien.getText().toString(); //mengambil Value etNim menjadi string
                    String jekel_user = tempJekel; //mengambil Value etNim menjadi string
                    String nama_pasien = binding.pendaftaran.edtNamaPasien.getText().toString(); //mengambil Value etNim menjadi string
                    String alamat = binding.pendaftaran.edtAlamatpasien.getText().toString(); //mengambil Value etNim menjadi string
                    String nohp = binding.pendaftaran.edtNohp.getText().toString(); //mengambil Value etNim menjadi string
                    String email = binding.pendaftaran.edtAlamatpasien.getText().toString(); //mengambil Value etNim menjadi string
                    String ttl = binding.pendaftaran.edtTtlPasien.getText().toString(); //mengambil Value etNim menjadi string

                    if (niks.equals("") || jekel_user.equals("") || nama_pasien.equals("") || alamat.equals("") || nohp.equals("")
                            || email.equals("")||ttl.equals("")) {
                        Toast.makeText(getApplicationContext(), "Semua data harus diisi", Toast.LENGTH_SHORT).show();
                        //memunculkan toast saat form masih ada yang kosong
                    } else {
                        getNoAntrianBaru(niks, nama_pasien, alamat, nohp, email,ttl);
                        binding.pendaftaran.edtNikPasien.setText("");
                        binding.pendaftaran.edtNamaPasien.setText("");
                        binding.pendaftaran.edtAlamatpasien.setText("");
                        binding.pendaftaran.edtNohp.setText("");
                        binding.pendaftaran.edtEmail.setText("");
                        binding.pendaftaran.edtTtlPasien.setText("");
                    }

                }else{

                    String niks = binding.layoutCari.nikCari.getText().toString();
                    String jekel_user =binding.layoutCari.jekelCari.getText().toString();
                    String nama_pasien = binding.layoutCari.nama.getText().toString();
                    String alamat = binding.layoutCari.alamatCari.getText().toString();
                    String nohp = binding.layoutCari.nohp.getText().toString();
                    String email = binding.layoutCari.emailCari.getText().toString();

                    getNoAntrianLama(niks, nama_pasien, alamat, nohp, email, jekel_user);


                }
            }
        });

    }
    public void getNoAntrianBaru(String niks,String nama, String alamat, String nohp, String email, String ttl){
        Log.e("update",api.URL_GET_ANTRIAN);
        AndroidNetworking.post(api.URL_GET_ANTRIAN)
                .addBodyParameter("nik", niks )
                .addBodyParameter("id_poli",id_poli)
                .addBodyParameter("nama_pasien", nama)
                .addBodyParameter("jekel",tempJekel)
                .addBodyParameter("id_jadwal",id_jadwal)
                .addBodyParameter("tanggal",tanggal_sistem)
                .addBodyParameter("alamat_pasien",alamat)
                .addBodyParameter("nohp_pasien",nohp)
                .addBodyParameter("email_pasien",email)
                .addBodyParameter("create_by",id)
                .addBodyParameter("ttl",ttl)

                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response

                        try {
                            String message = null;
                            message = response.getString("status");
                            Toast.makeText(getApplicationContext(), message
                                    ,Toast.LENGTH_LONG).show();
                            Intent i = new Intent(AmbilNoAntrianActivity.this, AntrianActivity.class);
                            startActivity(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    @Override
                    public void onError(ANError error) {
                        Log.e("salah",""+error);
                        Toast.makeText(getApplicationContext(), "Kesalahan tambah, Kode 2"
                                ,Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void getNoAntrianLama(String niks,String nama, String alamat, String nohp, String email, String jekel_pasien){
        Log.e("update",api.URL_GET_ANTRIAN);
        AndroidNetworking.post(api.URL_GET_ANTRIAN)
                .addBodyParameter("nik", niks )
                .addBodyParameter("id_poli",id_poli)
                .addBodyParameter("nama_pasien", nama)
                .addBodyParameter("jekel",jekel_pasien)
                .addBodyParameter("id_jadwal",id_jadwal)
                .addBodyParameter("tanggal",tanggal_sistem)
                .addBodyParameter("alamat_pasien",alamat)
                .addBodyParameter("nohp_pasien",nohp)
                .addBodyParameter("email_pasien",email)
                .addBodyParameter("create_by",id)

                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try{
                            String message = null;
                            message = response.getString("status");
                            Toast.makeText(getApplicationContext(),message
                                    ,Toast.LENGTH_LONG).show();
                            Intent i = new Intent(AmbilNoAntrianActivity.this, AntrianActivity.class);
                            startActivity(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    @Override
                    public void onError(ANError error) {
                        Log.e("salah",""+error);
                        Toast.makeText(getApplicationContext(), "Kesalahan"
                                ,Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void detailAntrian(){
        Log.e("url",api.URL_DETAIL_JADWAL+tanggal_sistem+"&id_jadwal="+id_jadwal);
        AndroidNetworking.get(api.URL_DETAIL_JADWAL+tanggal_sistem+"&id_jadwal="+id_jadwal)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            if (response.getString("status").equalsIgnoreCase("sukses")) {

                                JSONArray res = response.getJSONArray("res");
                                JSONObject data = res.getJSONObject(0);

                                int menunggu = data.getInt("menunggu");
                                int terpanggil = data.getInt("terpanggil");
                                int total = data.getInt("total");


                                binding.antrian.setText(""+menunggu);
                                binding.terpanggil.setText(""+terpanggil);
                                binding.total.setText(""+total);

                            }else {
                                Toast.makeText(AmbilNoAntrianActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e("tampil menu","response:"+anError);
                    }
                });
    }

    public void getCariPasien(){
        Log.e("url",api.URL_PASIEN_LAMA+binding.layoutCari.cariNik.getText().toString());
        AndroidNetworking.get(api.URL_PASIEN_LAMA+ binding.layoutCari.cariNik.getText().toString())
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            if (response.getString("status").equalsIgnoreCase("sukses")) {

                                JSONArray res = response.getJSONArray("res");
                                JSONObject data = res.getJSONObject(0);

                                String nama_pasien = data.getString("nama_pasien");
                                String nik = data.getString("nik");
                                String jekel = data.getString("jekel");
                                String alamat_pasien = data.getString("alamat_pasien");
                                String nohp = data.getString("nohp_pasien");
                                String email_pasien = data.getString("email_pasien");

                                binding.layoutCari.nama.setText(nama_pasien);
                                binding.layoutCari.nikCari.setText(nik);
                                binding.layoutCari.jekelCari.setText(jekel);
                                binding.layoutCari.alamatCari.setText(alamat_pasien);
                                binding.layoutCari.nohp.setText(nohp);
                                binding.layoutCari.emailCari.setText(email_pasien);

                                nama = nama_pasien;
                                nik_pasien = nik;
                                jekel_pasien = jekel;
                                alamat = alamat_pasien;
                                no_hp = nohp;
                                email = email_pasien;
                                binding.layoutCari.detailCariPasien.setVisibility(View.VISIBLE);
                            }else {
                                binding.layoutCari.detailCariPasien.setVisibility(View.GONE);
                                Toast.makeText(AmbilNoAntrianActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("tampil menu","response:"+anError);
                    }
                });
    }


}