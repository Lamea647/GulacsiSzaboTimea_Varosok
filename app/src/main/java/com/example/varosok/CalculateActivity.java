package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalculateActivity extends AppCompatActivity {

    private EditText editTextID;
    private Button buttonszamol, buttonSzamolVissza;
    private TextView szamolTextView;
    private DBhelper adatbazis;
    private int nap;
    private double rata;
    private double fertozottek_alap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        init();

        buttonSzamolVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vissza_fooldal = new Intent(CalculateActivity.this, MainActivity.class);
                startActivity(vissza_fooldal);
                finish();
            }
        });

        buttonszamol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                szamol();
            }
        });
    }

    public void szamol() {
        String id_szoveg = editTextID.getText().toString();
        int id_szam = Integer.parseInt(id_szoveg);
        Cursor eredmeny = adatbazis.adatLekerdezes();
        nap = 0;
        rata = 1.8;
        fertozottek_alap = 100;
        while (eredmeny.moveToNext() && nap == 0){
            if(id_szam == eredmeny.getInt(0)) {
                while (eredmeny.getInt(3) >= fertozottek_alap) {
                    fertozottek_alap = fertozottek_alap * rata;
                    nap++;
                }
                String nap_szoveg = new Integer(nap).toString();
                szamolTextView.setText(nap_szoveg);
            }else{
                szamolTextView.setText("Nem található rekord a következő adattal: " + id_szam);
            }
        }
    }
    public void init(){
        editTextID = findViewById(R.id.editTextID);
        buttonszamol = findViewById(R.id.buttonSzamol);
        buttonSzamolVissza = findViewById(R.id.buttonSzamolVissza);
        szamolTextView = findViewById(R.id.szamolTextView);
        adatbazis = new DBhelper(CalculateActivity.this);
    }


}