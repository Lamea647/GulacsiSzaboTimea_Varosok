package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextOrszag;
    private Button felvetelButton, keresesButton, lkButton, szamitButton;
    private DBhelper adatbazis;
    private TextView lkTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        felvetelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentFelvetel = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(intentFelvetel);
                finish();
            }
        });

        keresesButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ellenorzes();
            }
        });

        lkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adatLekerdezes();
            }
        });

        szamitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSzamol = new Intent(MainActivity.this, CalculateActivity.class);
                startActivity(intentSzamol);
                finish();
            }
        });
    }

    public void ellenorzes(){
        String orszagnev = editTextOrszag.getText().toString().trim();
        if (orszagnev.isEmpty()){
            Toast.makeText(this, "A keresési mező kitöltése kötelező! Kérem adjon meg egy országot!", Toast.LENGTH_SHORT).show();
            editTextOrszag.setError("A keresési mező kitöltése kötelező! Kérem adjon meg egy országot!");
        }else{
            Intent intentKereses = new Intent(MainActivity.this, SearchResultActivity.class);
            Bundle b = new Bundle();
            b.putString("OrszagNev", orszagnev);
            intentKereses.putExtras(b);
            startActivity(intentKereses);
            finish();
        }
    }

    public void adatLekerdezes(){
        Cursor adatok = adatbazis.adatLekerdezes();
        StringBuilder builder = new StringBuilder();
        while (adatok.moveToNext()){
            builder.append("ID:" ).append(adatok.getInt(0)).append("\n");
            builder.append("Név:" ).append(adatok.getString(1)).append("\n");
            builder.append("Ország:" ).append(adatok.getString(2)).append("\n");
            builder.append("Lakosság:" ).append(adatok.getInt(3)).append("\n\n");
        }
        lkTextView.setText(builder);
    }

    public void init(){
        keresesButton = findViewById(R.id.keresesButton);
        felvetelButton = findViewById(R.id.felvetelButton);
        editTextOrszag = findViewById(R.id.editTextOrszag);
        szamitButton = findViewById(R.id.szamitButton);
        adatbazis = new DBhelper(MainActivity.this);
        lkButton = findViewById(R.id.lkButton);
        lkTextView = findViewById(R.id.lkTextView);
        lkTextView.setMovementMethod(new ScrollingMovementMethod());
    }

}