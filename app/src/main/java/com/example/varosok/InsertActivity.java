package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    private Button buttonFelvetel, buttonRogzitVissza;
    private EditText editTextBevitelOrszag, editTextNev, editTextLakossag;
    private DBhelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        init();

        buttonFelvetel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adatRogzites();
            }
        });

        buttonRogzitVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vissza_fooldal = new Intent(InsertActivity.this, MainActivity.class);
                startActivity(vissza_fooldal);
                finish();
            }
        });
    }

    private void adatRogzites() {
        String nev = editTextNev.getText().toString().trim();
        String orszag = editTextBevitelOrszag.getText().toString().trim();
        String lakossag = editTextLakossag.getText().toString().trim();
        if (nev.isEmpty()){
            Toast.makeText(this, "Név megadása kötelező!", Toast.LENGTH_SHORT).show();
            editTextNev.setError("Név megadása kötelező!");
            return;
        }
        if (orszag.isEmpty()){
            Toast.makeText(this, "Ország megadása kötelező!", Toast.LENGTH_SHORT).show();
            editTextBevitelOrszag.setError("Ország megadása kötelező!");
            return;
        }
        if (lakossag.isEmpty()){
            Toast.makeText(this, "Lakosság megadása kötelező!", Toast.LENGTH_SHORT).show();
            editTextLakossag.setError("Lakosság megadása kötelező!");
            return;
        }
        if (adatbazis.adatRogzites(nev, orszag, lakossag)){
            Toast.makeText(this, "Sikeres adatrögzítés!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Sikertelen adatrögzítés!", Toast.LENGTH_SHORT).show();
        }
    }

    public void init(){
        editTextNev = findViewById(R.id.editTextNev);
        editTextBevitelOrszag = findViewById(R.id.editTextBevitelOrszag);
        editTextLakossag = findViewById(R.id.editTextLakossag);
        buttonFelvetel = findViewById(R.id.buttonFelvetel);
        buttonRogzitVissza = findViewById(R.id.buttonRogzitVissza);
        adatbazis = new DBhelper(InsertActivity.this);
    }
}