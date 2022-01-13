package com.example.act2_ut2_guillermorn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   //VARIABLES
        String sabor;
        String nombre;
        int peso;
        Boolean podrida;

   //BD
        HelperSQLApp helper;
        SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //AÑADIR SPINNER A LA APP
        String[] valores = {"dulce","salado", "amargo"};
        //RECUPERAR VALORES
        Spinner cboxSabores = (Spinner) findViewById(R.id.spinner);
        cboxSabores.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores));
        cboxSabores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // vacio

            }
        });

        ///CREACIÓN DE LA BASE DE DATOS

        helper= new HelperSQLApp(this);
        db=helper.getWritableDatabase();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    public void añadir(View view) {

        //RECUPERAR VALORES
        EditText edtxtNombre = findViewById(R.id.edtxtNombre);
        EditText edtxtPeso = findViewById(R.id.edtxtPeso);
        CheckBox chbPodrida = findViewById(R.id.chbPodrida);
        Spinner cboxSabores = (Spinner) findViewById(R.id.spinner);

        //NOMBRE
        nombre = edtxtNombre.getText().toString();
        //PESO
        peso = Integer.parseInt(edtxtPeso.getText().toString());
        //SABOR
        sabor = cboxSabores.getSelectedItem().toString();
        //PODRIDA
        podrida = chbPodrida.isChecked();

        //TOAST
            Toast toast1 = Toast.makeText(getApplicationContext(),"Los datos se han añadido", Toast.LENGTH_SHORT);
            toast1.show();

        //INSERT
            ContentValues values= new ContentValues();
            values.put("nombre", nombre);
            values.put("peso", peso);
            values.put("sabor", sabor);
            values.put("podrida", podrida);
            db.insert("fruitis",null, values);

        //Borrar valores
            edtxtNombre.setText("");
            edtxtPeso.setText("");
            chbPodrida.setChecked(false);
            nombre ="";peso=0; sabor=""; podrida=false;
    }

    public void actmtd(MenuItem item) {
        Intent i = new Intent(getApplicationContext(),ActListarTodos.class);
        startActivity(i);
    }

    public void actmtd(View view) {
        Intent i = new Intent(getApplicationContext(),ActListarTodos.class);
        startActivity(i);
    }

    public void actmul(View view) {
        Intent i = new Intent(getApplicationContext(),ActListarUltimo.class);
        startActivity(i);
    }

    public void acte(MenuItem item) {
        Intent i = new Intent(getApplicationContext(),ActEliminar.class);
        startActivity(i);
    }

    public void actin(MenuItem item) {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }

    public void actlpnomb(View view) {

        String nombreUsu;
        EditText edtxtFPN = findViewById(R.id.edtxtNombreFiltro);

        edtxtFPN.setVisibility(View.VISIBLE);
            //Si el campo tiene texto, al pulsar el boton nos redirigirá a la página de los resultados
            if(!edtxtFPN.getText().toString().equals("")){
                Intent i = new Intent(getApplicationContext(),ActListarPorNombre.class);
                
                //Recuperar nombre escrito por el usuario
                nombreUsu = edtxtFPN.getText().toString();
                        i.putExtra("nUsu",nombreUsu);
                startActivity(i);
            }
    }
}