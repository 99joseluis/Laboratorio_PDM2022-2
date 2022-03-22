package com.pdm.laboratory.cuestionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView textView;  // Preguntas
    LinearLayout linearLayout; // Pagina para cambiar el background con las respuestas incorrectas
    int iteration = 0; // Contador de las iteraciones
    int correct ; // Contador de respuestas correctas
    int incorrect ; // Contador de respuestas incorrectas

    String[] questions ; // Arreglo de preguntas
    String[] answers; // Arreglo de respuestas


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Aqui obtenemos los datos del layout
        textView = (TextView) findViewById(R.id.textView);
        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);

        // Aqui obtenemos los arreglos del archivo string.xml
        Resources res = getResources();
        questions = res.getStringArray(R.array.questions);
        answers = res.getStringArray(R.array.answers);

    }

    /**
     * Metodo que emplea la accion a generarse cuando se presiona el boton true
     * @param view
     */
    public void responseTrue(View view){
        // Si es true, aumenta el contador de correctas y cambia la pregunta (si no es la ultima pregunta)
        if("True".contentEquals(answers[iteration])){
            correct++;
            if(iteration + 1 < answers.length)
                textView.setText(questions[iteration++]);
        // Si es false, aumenta el contador de incorrectas, cambia el fondo de la aplicacion y espera 10 seg
        }else{
            incorrect++;
            linearLayout.setBackgroundColor(Color.parseColor("#AA0115"));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(iteration + 1 < answers.length)
                        textView.setText(questions[iteration++]);
                    linearLayout.setBackgroundColor(Color.parseColor("#178E76"));
                }
            },1000);
        }
        // Manda a llamar el metodo changeView que cambia la vista si ya son todas las preguntas
        changeView(correct, incorrect);
    }

    /**
     * Metodo que emplea la accion a generarse cuando se presiona el boton false
     * @param view
     */
    public void responseFalse(View view){
        // Si es False, aumenta el contador de correctas y cambia la pregunta (si no es la ultima pregunta)
        if("False".contentEquals(answers[iteration])){
            correct++;
            if(iteration + 1 < answers.length)
                textView.setText(questions[iteration++]);
            // Si es false, aumenta el contador de incorrectas, cambia el fondo de la aplicacion y espera 10 seg
        }else{
            incorrect++;
            linearLayout.setBackgroundColor(Color.parseColor("#AA0115"));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(iteration + 1 < answers.length)
                        textView.setText(questions[iteration++]);
                    linearLayout.setBackgroundColor(Color.parseColor("#178E76"));
                }
            },1000);
        }
        // Manda a llamar el metodo changeView que cambia la vista si ya son todas las preguntas
        changeView(correct, incorrect);
    }

    /**
     * Metodo que emplea la accion a generarse cuando se presiona el boton Omit
     * @param view
     */
    public void responseOmit(View view){
        //Aumenta el contador de incorrectas, cambia el fondo de la aplicacion y espera 10 seg
        incorrect++;
        linearLayout.setBackgroundColor(Color.parseColor("#AA0115"));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText(questions[iteration++]);
                linearLayout.setBackgroundColor(Color.parseColor("#178E76"));
            }
        },1000);
        // Manda a llamar el metodo changeView que cambia la vista si ya son todas las preguntas
        changeView(correct, incorrect);
    }

    private void changeView(int trues,  int falsies){
        // Pregunta si las iteraciones son igual al numero de las respuestas -1
        if(iteration == answers.length-1){
            // Creamos un intent para pasar de este Activity a ResultsActivity
            Intent irLayout = new Intent(MainActivity.this, ResultsActivity.class);
            // Pasamos los parametros de los contadores correctos e incorrectos
            irLayout.putExtra("trues", ""+trues);
            irLayout.putExtra("falsies", ""+falsies);
            // Iniciamos el ResultsActivity
            startActivity(irLayout);
        }
    }
}