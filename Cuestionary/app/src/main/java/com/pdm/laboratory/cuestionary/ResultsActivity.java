package com.pdm.laboratory.cuestionary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    TextView textViewC;
    TextView textViewI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Creamos un intetn para obtener los parametros que paso MainActivity
        Intent intent = getIntent();
        String corrects = intent.getStringExtra("trues");
        String incorrectly = intent.getStringExtra("falsies");

        // Asignamos los textView
        textViewC = (TextView) findViewById(R.id.TCorrect);
        textViewI = (TextView) findViewById(R.id.TIncorrect);

        // Asignamos el texto a cada textView
        textViewC.setText(corrects);
        textViewI.setText(incorrectly);

    }

    /**
     * Metodo para Cerrar la aplicacion al presionar el boton que dice stop
     * @param view
     */
    public void stoped(View view){
        cerrarAplicacion();
    }

    /**
     * Metodo para evitar que al presionar el boton atras, este regrese al layout anterior
     */
    @Override
    public void onBackPressed() { }

    /**
     * Metodo para cerrar la aplicacion de forma segura
     */
    private void cerrarAplicacion() {
        new AlertDialog.Builder(this)
                .setTitle("¿Realmente desea cerrar la aplicación?")
                .setCancelable(false)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {// un listener que al pulsar, cierre la aplicacion
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        android.os.Process.killProcess(android.os.Process.myPid()); //Su funcion es algo similar a lo que se llama cuando se presiona el botón "Forzar Detención" o "Administrar aplicaciones", lo cuál mata la aplicación
                        //finish(); Si solo quiere mandar la aplicación a segundo plano
                    }
                }).show();
    }
}