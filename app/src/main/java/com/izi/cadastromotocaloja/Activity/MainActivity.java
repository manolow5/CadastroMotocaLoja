package com.izi.cadastromotocaloja.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.izi.cadastromotocaloja.R;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button btnMotoca;
    private Button btnLoja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponentes();

        btnMotoca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent motoca = new Intent(MainActivity.this, CadastroMotocaActivity.class);
              //  startActivity(motoca);

                Map<String, Object> note = new HashMap<>();
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("Teste").document("Nada").set(note);

                db.collection("Teste")
                        .document("Nada")
                        .collection("tudo")
                        .document("Tudinho")
                        .set(note);




            }
        });

        btnLoja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent loja = new Intent(MainActivity.this, CadastroLojaActivity.class);
                startActivity(loja);
            }
        });
    }

    private void inicializarComponentes(){
        btnLoja = findViewById(R.id.btnCadastroLoja);
        btnMotoca = findViewById(R.id.btnCadastroMotoca);
    }
}
