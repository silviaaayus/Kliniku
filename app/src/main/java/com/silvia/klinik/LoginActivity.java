package com.silvia.klinik;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity {

    API api;
    Button btnlogin;
    EditText edtpass, edtusername;
    TextView txt_register;
    ImageView back;

    TinyDB tinyDB;

    AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tinyDB = new TinyDB(this);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        api = new API();

        AndroidNetworking.initialize(this);
        alertDialog = new SpotsDialog.Builder().setContext(this).setMessage("Sedang Mencoba Masuk ....").setCancelable(false).build();


        edtpass = findViewById(R.id.edtpass);
        edtusername = findViewById(R.id.edtusername);
        txt_register = findViewById(R.id.txt_register);
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });


        btnlogin = findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLogin();
            }
        });


    }


    private void getLogin() {
//        alertDialog.show();
        Log.e("api", api.URL_LOGIN);
        AndroidNetworking.post(api.URL_LOGIN)
                .addBodyParameter("username", edtusername.getText().toString())
                .addBodyParameter("password", edtpass.getText().toString())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
//                            alertDialog.hide();
                            int res = response.getInt("response");
                            String status = response.getString("status");
                            Log.d("sukses", "code" + response);
                            if (res == 1) {

                                JSONObject data = response.getJSONObject("res");
                                String id_user = data.getString("id_user");
                                String id_pasien = data.getString("id_pasien");
                                String nama_user = data.getString("nama_user");
                                String jekel = data.getString("jekel");
                                String no_hp = data.getString("no_hp");
                                String alamat = data.getString("alamat");
                                String email_user = data.getString("email_user");
                                String ttl = data.getString("ttl");

                                tinyDB.putString("keyIdUser", id_user);
                                tinyDB.putString("keyIdPasien", id_pasien);
                                tinyDB.putString("keyNamaUser", nama_user);
                                tinyDB.putString("keyJekel", jekel);
                                tinyDB.putString("keyTelp", no_hp);
                                tinyDB.putString("keyAlamat", alamat);
                                tinyDB.putString("keyEmail", email_user);
                                tinyDB.putString("keyTtl", ttl);

                                tinyDB.putBoolean("keyLogin", true);
                                Log.e("salah", id_user);
                                Log.e("pasien", id_pasien);

                                Toast.makeText(LoginActivity.this, "Login Sukses", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, status, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        alertDialog.hide();
                        Log.d("eror", "code :" + anError);
                        Toast.makeText(LoginActivity.this, "" + anError, Toast.LENGTH_SHORT).show();
                    }
                });
    }


}