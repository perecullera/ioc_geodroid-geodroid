package com.example.io.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;


public class LoginActivity extends ActionBarActivity {

    Button loginButton, rememberButton;
    EditText email, contrasenya;
    Context context;
    CRUDClass crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;
        crud = new CRUDClass(context);
        try {
            crud.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loginButton = (Button) findViewById(R.id.login_button);
        rememberButton = (Button) findViewById(R.id.remember_button);
        email = (EditText) findViewById(R.id.email_text);
        contrasenya = (EditText) findViewById(R.id.contrasenya_text);
        context = getApplicationContext();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!email.getText().toString().matches("")||!contrasenya.getText().toString().matches("")) {
                    String text = email.getText().toString() + " i " + contrasenya.getText().toString() + " a comprovar a la BBDD";

                    //Crear la funció a CRUDClass
                    int existeixUsuari = 0; //
                    debuging();
                    System.out.println("email= "+email.getText().toString()+" contrasenya = "+contrasenya.getText().toString());
                    Usuari u = crud.loguejaUsuari(email.getText().toString(), contrasenya.getText().toString());

                    Toast.makeText(context, u.id, Toast.LENGTH_SHORT).show();

                    if(existeixUsuari>=0) {
                        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                        intent.putExtra("email", email.getText().toString());
                        intent.putExtra("contrasenya", email.getText().toString());
                        intent.putExtra("tipusUsuari", existeixUsuari);
                        startActivity(intent);
                    }else{
                        Toast.makeText(context, "Email i contrasenya no coincideixen", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(context, "Cal omplir els dos camps per a fer login", Toast.LENGTH_LONG).show();
                }
            }
        });

        rememberButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String text = "A implementar en un futur, envia'ns un correu a hola@geodroid.cat per a mes info";
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void debuging(){
        Usuari u = new Usuari();
        u.setId("12");
        u.setNom("emilio");
        u.setEmail("email@email.com");
        u.setRol(1);
        u.setPwd("password");
        u.setIdEmpresa("2");
        crud.createUsuari(u);
    }
}
