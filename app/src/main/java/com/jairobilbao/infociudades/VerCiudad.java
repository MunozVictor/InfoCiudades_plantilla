package com.jairobilbao.infociudades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jairobilbao.infociudades.DB.CityDatasource;

public class VerCiudad extends AppCompatActivity {
    TextView nombreText;
    TextView provinciaText;
    EditText pobModificable;
    Ciudad ciudad = new Ciudad();
    CityDatasource cityDatasource = new CityDatasource(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_ciudad);
        nombreText=(TextView) findViewById(R.id.nombreText);
        provinciaText = (TextView) findViewById(R.id.provinciaText);
        pobModificable = (EditText) findViewById(R.id.pobModificable);

        String nombreCiudad = getIntent().getStringExtra(MainActivity.NOM_CIUDAD);
        Log.v("Nombre ciudad",nombreCiudad);

        //Aquí va el código para recuperar la informacion completa de la ciudad
        Intent i = getIntent();
        String s = i.getStringExtra(MainActivity.NOM_CIUDAD);
        ciudad = cityDatasource.getCity(s);


        Log.v("Nombre ciudad",ciudad.getNomCiudad());
        //Este código es para que muestre datos de ejemplo.
        //En realidad debe mostrar los datos de la ciudad consultada
        nombreText.setText(ciudad.getNomCiudad());
        provinciaText.setText(ciudad.getProvincia());
        pobModificable.setText(String.valueOf(ciudad.getPoblacion()));


    }

    public void modificarCiudad(View v){
        String poblacion = pobModificable.getText().toString();
        if (poblacion.equals("")){
            Toast.makeText(this, "Introduzca la población", Toast.LENGTH_LONG).show();
        } else{
            ciudad.setPoblacion(Integer.parseInt(poblacion));

            //Aquí va la modificación de la ciudad
            cityDatasource.updateCiudad(ciudad);

        }
    }

}
