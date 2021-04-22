package com.silvia.klinik.Profil;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.silvia.klinik.API;
import com.silvia.klinik.MainActivity;
import com.silvia.klinik.R;
import com.silvia.klinik.TinyDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditProfilActivity extends AppCompatActivity {

    EditText edt_namauser,edt_alamatuser,edt_emailuser,
            edt_telpuser,edt_username,edt_pass,edt_ttl ;
    Button btn_edit;
    API api;
    TinyDB tinyDB;
    String iduser;
    TextView title;
    ImageView back;
    Spinner edt_jekel;

    String[] jekel = {"Laki-Laki","Perempuan"};
    String tempJekel;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        api = new API();
        AndroidNetworking.initialize(this);
        tinyDB = new TinyDB(this);
        iduser = tinyDB.getString("keyIdUser");
        Log.e("idnya",iduser);

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        title = findViewById(R.id.tv_toolbar);
        title.setText("Edit Profil");
        back = findViewById(R.id.ib_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edt_jekel = findViewById(R.id.edt_jekel_);
        ArrayAdapter<String> Adokter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,jekel);
        Adokter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edt_jekel.setAdapter(Adokter);

        edt_jekel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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



        edt_namauser = findViewById(R.id.edt_namauser);
        edt_alamatuser = findViewById(R.id.edt_alamatuser);
        edt_telpuser = findViewById(R.id.edt_telpuser);
        edt_emailuser = findViewById(R.id.edt_emailuser);
        edt_username = findViewById(R.id.edt_username);
        edt_pass = findViewById(R.id.edt_pass);
        edt_ttl = findViewById(R.id.edt_ttl);
        edt_ttl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });



        getProfil();

        btn_edit = findViewById(R.id.btn_update);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });
    }

    private void showDateDialog(){

        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                edt_ttl.setText(dateFormatter.format(newDate.getTime()));

            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        datePickerDialog.show();
    }
    public void getProfil(){
        Log.e("salah",api.URL_USER+iduser);
        AndroidNetworking.get(api.URL_USER+iduser)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            Log.d("tampilmenu","response:"+response);
                            JSONArray res = response.getJSONArray("res");
                            JSONObject data = res.getJSONObject(0);
                            String nama_pasien = data.getString("nama_user");
                            String jekel = data.getString("jekel");
                            String alamat = data.getString("alamat");
                            String no_hp = data.getString("no_hp");
                            String email_pasien = data.getString("email_user");
                            String username = data.getString("username");
                            String password = data.getString("password");
                            String ttl = data.getString("ttl");


                            int posSpinner = 0;
                            if (jekel.equalsIgnoreCase("Laki-laki")){
                                posSpinner = 0;
                            }
                            else{
                                posSpinner = 1;
                            }
                            tempJekel = jekel;
                            edt_namauser.setText(nama_pasien);
                            edt_jekel.setSelection(posSpinner);
                            edt_alamatuser.setText(alamat);
                            edt_telpuser.setText(no_hp);
                            edt_emailuser.setText(email_pasien);
                            edt_username.setText(username);
                            edt_pass.setText(password);
                            edt_ttl.setText(ttl);

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

    public void update(){
        Log.e("update",api.URL_UPDATE_PASIEN);
        AndroidNetworking.post(api.URL_UPDATE_PASIEN)
                .addBodyParameter("id_user", iduser)
                .addBodyParameter("nama_user", edt_namauser.getText().toString())
                .addBodyParameter("jekel", tempJekel)
                .addBodyParameter("alamat", edt_alamatuser.getText().toString())
                .addBodyParameter("no_hp", edt_telpuser.getText().toString())
                .addBodyParameter("email_user", edt_emailuser.getText().toString())
                .addBodyParameter("username", edt_username.getText().toString())
                .addBodyParameter("password", edt_pass.getText().toString())
                .addBodyParameter("ttl", edt_ttl.getText().toString())
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response

                        Intent i = new Intent(EditProfilActivity.this, MainActivity.class);
                        startActivity(i);
//                        tinyDB.putString("keyIdUser",iduser);
//                        tinyDB.putString("keyNamaUser",edt_namauser.getText().toString());
//                        tinyDB.putString("keyJekel",tempJekel);
//                        tinyDB.putString("keyAlamat",edt_alamatuser.getText().toString());
//                        tinyDB.putString("keyEmail",edt_emailuser.getText().toString());
//                        tinyDB.putString("keyTelp",edt_telpuser.getText().toString());

                        Toast.makeText(getApplicationContext(), "Data berhasil di update..."
                                ,Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onError(ANError error) {
                        Log.e("salah",""+error);
                        Toast.makeText(getApplicationContext(), "Kesalahan update, Kode 2"
                                ,Toast.LENGTH_LONG).show();
                    }
                });
    }

}