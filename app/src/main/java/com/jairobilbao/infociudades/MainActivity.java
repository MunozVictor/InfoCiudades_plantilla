package com.jairobilbao.infociudades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String NOM_CIUDAD="NOMCIUDAD";
    EditText nomCiudadLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nomCiudadLabel = (EditText) findViewById(R.id.nomCiudadLabel);
    }

   public void startInsertarCiudad(View v){
       Intent intent = new Intent(this,InsertarCiudad.class);
       startActivity(intent);
   }
    public void startVerCiudad(View v){
        String ciudad =nomCiudadLabel.getText().toString();
        if (ciudad.equals("")){
            Toast.makeText(this,"Introduce nombre para la ciudad",Toast.LENGTH_LONG).show();
        }else {
            Intent intent = new Intent(this, VerCiudad.class);
            intent.putExtra(NOM_CIUDAD,ciudad);
            startActivity(intent);
        }
    }
}
