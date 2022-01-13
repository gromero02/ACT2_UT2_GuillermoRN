package com.example.act2_ut2_guillermorn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ActListarUltimo extends AppCompatActivity {

    HelperSQLApp helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_listar_ultimo);

        //ABRIR LA BASE DE DATOS COMO LECTURA

        helper= new HelperSQLApp(this);


    }

    //MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }
    public void actmtd(MenuItem item) {
        Intent i = new Intent(getApplicationContext(),ActListarTodos.class);
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

    public void btmostrarul(View view) {

        db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery(" select max(id),nombre,peso,sabor, podrida from fruitis;", null);

        boolean podrida;
        int peso, id;
        String nombre, sabor, podridaSt = "";

        TextView edtxtMU = findViewById(R.id.txtvRsSelect);

        edtxtMU.append("\n ID--NOMBRE--PESO--SABOR--PODRIDA \n-----------------------------------------------------------------");
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            id = Integer.parseInt(cursor.getString(0));
            nombre = cursor.getString(1);
            peso = Integer.parseInt(cursor.getString(2));
            sabor = cursor.getString(3);
            podrida = Boolean.parseBoolean(cursor.getString(4));
            if (podrida == false) {
                podridaSt = "NO";
            } else if(podrida == true) {
                podridaSt = "SI";
            }

            edtxtMU.append("\n" + id + "      " + nombre + "        " + peso + "        " + sabor + "       " + podridaSt);

            cursor.moveToNext();


        }
    }
}