package com.silvia.klinik;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import dmax.dialog.SpotsDialog;

public class RegisterActivity extends AppCompatActivity {

    API api;
    private static final String TAG = "MainActivity";
    EditText edt_namauser_register, edt_jekel_register,
            edt_alamatuser_register,edt_emailuser_register,
            edt_telpuser_register, edt_username_register, edt_passuser_register,edt_nik;
    Button btn_daftar;
    TextView txtLogin;
    AlertDialog alertDialog;

    String[] jekel = {"Laki-Laki","Perempuan"};
    String tempJekel;
    Spinner spinJekel;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    EditText edt_datepicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        alertDialog =new SpotsDialog.Builder().setContext(this).setMessage("Sedang Mengirim Data ....").setCancelable(false).build();
        Log.d(TAG, "onCreate: inisialisasi");
        api = new API();

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);


        edt_namauser_register = findViewById(R.id.edt_namauser_register);
        spinJekel = findViewById(R.id.edt_jekel_register);
        edt_alamatuser_register = findViewById(R.id.edt_alamatuser_register);
        edt_emailuser_register = findViewById(R.id.edt_emailuser_register);
        edt_telpuser_register = findViewById(R.id.edt_telpuser_register);
        edt_username_register = findViewById(R.id.edt_username_register);
        edt_passuser_register = findViewById(R.id.edt_pass_register);
        edt_nik = findViewById(R.id.edt_nik);
        edt_datepicker = findViewById(R.id.datepicker);
        edt_datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        txtLogin = findViewById(R.id.txt_Login);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ArrayAdapter<String> Adokter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,jekel);
        Adokter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinJekel.setAdapter(Adokter);

        spinJekel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


        btn_daftar = findViewById(R.id.btn_daftar);
        AndroidNetworking.initialize(getApplicationContext());
        aksiTambah();
    }

    private void showDateDialog(){

        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                edt_datepicker.setText(dateFormatter.format(newDate.getTime()));

            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        datePickerDialog.show();
    }

    public void aksiTambah(){
        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama_user = edt_namauser_register.getText().toString();
                String jekel_user = tempJekel;
                String alamat_user = edt_alamatuser_register.getText().toString();
                String telp_user = edt_telpuser_register.getText().toString();
                String email_user = edt_emailuser_register.getText().toString();
                String username = edt_username_register.getText().toString();
                String pass = edt_passuser_register.getText().toString();
                String nik = edt_nik.getText().toString();
                String ttl = edt_datepicker.getText().toString();
                if (nama_user.equals("")||jekel_user.equals("")||alamat_user.equals("")||telp_user.equals("")
                        ||email_user.equals("")||username.equals("")||pass.equals("")
                        ||nik.equals("")||ttl.equals("")
                ){
                    Toast.makeText(getApplicationContext(),"Semua data harus diisi" , Toast.LENGTH_SHORT).show();

                } else {
                    tambahData(nama_user,alamat_user,telp_user,email_user,username,pass,nik,ttl);

                    edt_namauser_register.setText("");
                    edt_alamatuser_register.setText("");
                    edt_telpuser_register.setText("");
                    edt_emailuser_register.setText("");
                    edt_username_register.setText("");
                    edt_passuser_register.setText("");
                    edt_nik.setText("");
                    edt_datepicker.setText("");

                }
            }
        });

    }

    public void tambahData(String nama_user,String alamat_user, String telp_user, String email_user, String username, String pass, String nik, String ttl){

        AndroidNetworking.post(api.URL_REGISTER)
                .addBodyParameter("nama_user", nama_user)
                .addBodyParameter("jekel", tempJekel)
                .addBodyParameter("alamat",alamat_user)
                .addBodyParameter("no_hp", telp_user)
                .addBodyParameter("email_user", email_user)
                .addBodyParameter("username", username)
                .addBodyParameter("password", pass)
                .addBodyParameter("nik", nik)
                .addBodyParameter("ttl",ttl)
                .addBodyParameter("nama_pasien", nama_user)
                .addBodyParameter("alamat_pasien", alamat_user)
                .addBodyParameter("nohp_pasien", telp_user)
                .addBodyParameter("email_pasien", email_user)

                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Toast.makeText(getApplicationContext(),"Data berhasil ditambahkan" , Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(i);

                    }
                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(getApplicationContext(),"Data gagal ditambahkan", Toast.LENGTH_SHORT).show();

                    }
                });
    }

}