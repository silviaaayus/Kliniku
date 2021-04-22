package com.silvia.klinik.KotakSaran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.silvia.klinik.LoginActivity;
import com.silvia.klinik.R;
import com.silvia.klinik.TinyDB;

import org.json.JSONException;
import org.json.JSONObject;

public class KotakSaranActivity extends AppCompatActivity {

    TextView title;
    String[] dokter = {"drg. Riki Agus Candra","drg. Mety Dwi Putri Eszy"};
    String tempDokter;
    Spinner spinDokter;

    ImageView back;
    API api;
    TinyDB tinyDB;
    String id;
    EditText edt_keluhan;
    Button btn_Kirim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kotak_saran);
        AndroidNetworking.initialize(this);
        api  = new API();
        tinyDB = new TinyDB(this);
        id = tinyDB.getString("keyIdUser");

        title = findViewById(R.id.tv_toolbar);
        title.setText("Keluhan dan Saran");

        back = findViewById(R.id.ib_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        spinDokter = findViewById(R.id.spinner);
        edt_keluhan = findViewById(R.id.txtKeluhan);

        if (!tinyDB.getBoolean("keyLogin")){
            Intent intent = new Intent(KotakSaranActivity.this, LoginActivity.class);
            Toast.makeText(this, "Silahkan Login Dulu!", Toast.LENGTH_SHORT).show();
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }


        ArrayAdapter<String> Adokter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,dokter);
        Adokter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDokter.setAdapter(Adokter);

        spinDokter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tempDokter = dokter[i];
                Log.e("spinner",tempDokter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        btn_Kirim = findViewById(R.id.btn_Kirim);
        btn_Kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpanSaran();
            }
        });
    }
    private void simpanSaran() {
        Log.e("saran",api.URL_SARAN);
        AndroidNetworking.post(api.URL_SARAN)
                .addBodyParameter("id_user", id)
                .addBodyParameter("nama_dokter", tempDokter)
                .addBodyParameter("keluhansaran", edt_keluhan.getText().toString())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            if (response.getString("code").equalsIgnoreCase("1")){
                                Toast.makeText(getApplicationContext(), response.getString("response"), Toast.LENGTH_LONG).show();

                                finish();
                            }else {
                                Toast.makeText(getApplicationContext(), response.getString("response"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(), ""+anError, Toast.LENGTH_LONG).show();

                    }
                });
    }
}