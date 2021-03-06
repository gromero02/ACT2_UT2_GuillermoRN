package com.example.act2_ut2_guillermorn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ActListarTodos extends AppCompatActivity {

    ListView lv1;
    HelperSQLApp helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_listar_todos);

        lv1 = (ListView)findViewById(R.id.lvFruitis);

        ArrayList<String> arr = new ArrayList<>();

        helper= new HelperSQLApp(this);
        db=helper.getReadableDatabase();

        Cursor fila = db.rawQuery("select * from fruitis", null);

        if(fila.moveToFirst()){
            do{
                arr.add(fila.getString(0) + " --->  " + fila.getString(1)+ " -- " + fila.getString(2)+ " -- " + fila.getString(3)+ " -- " + fila.getString(4));

            }while(fila.moveToNext());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        lv1.setAdapter(adapter);

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
}