package com.jairobilbao.infociudades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jairobilbao.infociudades.DB.CityDatasource;

public class InsertarCiudad extends AppCompatActivity {
    EditText ciudadLabel;
    EditText provinciaLabel;
    EditText poblacionLabel;
    CityDatasource cityDatasource = new CityDatasource(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_ciudad);
        ciudadLabel = (EditText) findViewById(R.id.nombreLabel);
        provinciaLabel = (EditText) findViewById(R.id.provinciaLabel);
        poblacionLabel = (EditText) findViewById(R.id.poblacionLabel);

    }
    public void insertarCiudad(View v){
        String nomCiudad = ciudadLabel.getText().toString();
        String provincia = provinciaLabel.getText().toString();
        String poblacion = poblacionLabel.getText().toString();
        if (nomCiudad.equals("")||provincia.equals("")||poblacion.equals("")){
            Toast.makeText(this, "Se deben rellenar todos los campos", Toast.LENGTH_LONG).show();
        } else{
             Ciudad ciudad = new Ciudad(-1,nomCiudad,provincia,Integer.parseInt(poblacion));

            //Aquí iría el código para insertar la ciudad en la base de datos
            cityDatasource.insertCity(ciudad);

            ciudadLabel.setText("");
            ciudadLabel.setHint("Introduce nombre de la ciudad");
            provinciaLabel.setText("");
            provinciaLabel.setHint("Introduce provincia");
            poblacionLabel.setText("");
            poblacionLabel.setHint("Introduce nº de habitantes");
        }
    }
}
