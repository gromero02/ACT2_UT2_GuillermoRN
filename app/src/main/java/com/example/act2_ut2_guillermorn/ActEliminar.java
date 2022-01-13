package com.example.act2_ut2_guillermorn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActEliminar extends AppCompatActivity {
    HelperSQLApp helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_eliminar);

        helper= new HelperSQLApp(this);
        db=helper.getWritableDatabase();
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

    public void btelim(View view) {
        //Variables
            String whereC, nombreUsu, mensaje;

        //Recuperar nombre
        EditText edtxtUsu = findViewById(R.id.etxtNombreE);
            nombreUsu = edtxtUsu.getText().toString();

        whereC = "nombre='"+nombreUsu+"'";

            db.delete("fruitis",whereC ,null ) ;

        //Toast
        mensaje = nombreUsu+" "+"se ha eliminado";

        Toast toast1 = Toast.makeText(getApplicationContext(),mensaje, Toast.LENGTH_SHORT);
        toast1.show();

        //Restablecer campo
            edtxtUsu.setText("");
    }
}