package com.example.io.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MenuActivity extends ActionBarActivity {
    Button mapaButton, editaButton, logoutButton;
    TextView menu_title;
    Bundle dades;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mapaButton = (Button) findViewById(R.id.mapa_button);
        editaButton = (Button) findViewById(R.id.flota_button);
        logoutButton = (Button) findViewById(R.id.logout_button);
        menu_title = (TextView) findViewById(R.id.menu_title);

        dades = getIntent().getExtras();

        if(dades.getInt("tipusUsuari")==0){
            editaButton.setVisibility(View.INVISIBLE);
        }

        menu_title.setText("Hola, "+dades.getString("email"));

        mapaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MapsActivity.class);
                Toast.makeText(getApplicationContext(), "Falta GooglePlayServices a Genymotion", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        editaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, LlistaFlotaActivity.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
