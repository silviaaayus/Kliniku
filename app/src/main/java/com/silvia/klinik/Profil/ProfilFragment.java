package com.silvia.klinik.Profil;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silvia.klinik.LoginActivity;
import com.silvia.klinik.MainActivity;
import com.silvia.klinik.R;
import com.silvia.klinik.TinyDB;


public class ProfilFragment extends Fragment {
    TinyDB tinyDB;
    LinearLayout logout,btn_edit_profil;
    TextView txt_username_profil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        tinyDB = new TinyDB(getContext());
        txt_username_profil = view.findViewById(R.id.username_profl);
        txt_username_profil.setText(tinyDB.getString("keyNamaUser"));

        if (!tinyDB.getBoolean("keyLogin")){

            Intent intent = new Intent(getContext(), LoginActivity.class);
            Toast.makeText(getContext(), "Silahkan Login Dulu!", Toast.LENGTH_SHORT).show();
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        btn_edit_profil = view.findViewById(R.id.btn_edit_profil);
        btn_edit_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(),EditProfilActivity.class);
                startActivity(i);
            }
        });

        logout = view.findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tinyDB.clear();
                Intent i = new Intent(getContext(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        return  view;
    }
}