package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SearchResultActivity extends AppCompatActivity {

    private Button keresesVisszaButton;
    private TextView textViewAdatok;
    private DBhelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        init();
        keresesiEredmeny();

        keresesVisszaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vissza_fooldal = new Intent(SearchResultActivity.this, MainActivity.class);
                startActivity(vissza_fooldal);
                finish();
            }
        });
    }

    public void keresesiEredmeny(){
        Bundle extras = getIntent().getExtras();
        String orszagmezo = extras.getString("OrszagNev");
        Cursor eredmenyek = adatbazis.adatLekerdezes();
        String eredmeny = "";
        while (eredmenyek.moveToNext()){
            if (eredmenyek.getString(2).contains(orszagmezo)){
                eredmeny += eredmenyek.getString(1).toString() + "\n";
            }
        }
        if (!eredmeny.equals("")){
            textViewAdatok.setText(eredmeny);
        }else{
            textViewAdatok.setText("Nem található rekord a következő adattal: " + orszagmezo);
        }

    }

    public void init(){
        textViewAdatok  = findViewById(R.id.textViewAdatok);
        textViewAdatok.setMovementMethod(new ScrollingMovementMethod());
        keresesVisszaButton = findViewById(R.id.keresesVisszaButton);
        adatbazis = new DBhelper(SearchResultActivity.this);
    }
}