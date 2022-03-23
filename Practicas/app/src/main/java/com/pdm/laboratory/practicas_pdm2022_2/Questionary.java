package com.pdm.laboratory.practicas_pdm2022_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pdm.laboratory.practicas_pdm2022_2.models.QuestionModel;



public class Questionary extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout linearLayout;
    private Button btnTrue;
    private Button btnFalse;
    private Button btnNext;
    private TextView textViewQuestion;
    private TextView textViewCorrect;
    private TextView textViewWrong;
    private int counterCorrect;
    private int counterWrong;
    private int counter;
    private QuestionModel[] questionModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionary);
        // Inicializando las variables
        inicializeVariables();
        getQuestions();
        textViewQuestion.setText(questionModel[counter].getQuestion());
    }

    @Override
    public void onClick(View v){
        Log.d("QuestionaryB", "Question : "+ questionModel[counter] + "\nAsnwer Maked: "+ v.getId() + "\nAnswer Correct: " + questionModel[counter].getAnswer());
        switch (v.getId()){
            case R.id.btnTrue:
                if(questionModel[counter].getAnswer()){
                    counterCorrect++;
                    textViewCorrect.setText(""+counterCorrect);
                    setButtons(false);
                }else{
                    counterWrong++;
                    textViewWrong.setText(""+counterWrong);
                    setWrongQuestion();
                    setButtons(false);
                }
                counter++;
                break;
            case R.id.btnFalse:
                if(!questionModel[counter].getAnswer()){
                    counterCorrect++;
                    textViewCorrect.setText(""+counterCorrect);
                    setButtons(false);
                }else{
                    counterWrong++;
                    textViewWrong.setText(""+counterWrong);
                    setWrongQuestion();
                    setButtons(false);
                }
                counter++;
                break;
            case R.id.btnNext:
                setButtons(true);
                if(counter <= questionModel.length-1){
                    textViewQuestion.setText(questionModel[counter].getQuestion());
                }else{
                    textViewQuestion.setText(R.string.Terminate);
                    setButtons(false);
                    btnNext.setEnabled(false);
                }
                break;
            default:
                Log.d("QuestionB", "Sepa que boton se presiono "+ v.getId());
                break;
        }
    }

    /**
     * Metodo para modificar interfaz al seleccionar la respuesta incorrecta
     */
    private void setWrongQuestion(){
        Toast.makeText(getApplicationContext(), R.string.WrongQuestion, Toast.LENGTH_SHORT).show();
        linearLayout.setBackgroundColor(Color.parseColor("#AA0115"));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                linearLayout.setBackgroundColor(Color.parseColor("#178E76"));
            }
        },1000);
    }

    /**
     * Método para cambiar la visibilidad de los botones True y False
     * @param enable true o false (visibilidad del boton)
     */
    private void setButtons(Boolean enable){
        btnTrue.setEnabled(enable);
        btnFalse.setEnabled(enable);
    }

    /**
     * Metodo para obtener las preguntas y respuestas del archivo strings.xml y crear objetos de tipo QuestionModel
     */
    private void getQuestions(){
        Resources resources = getResources();
        String[] questions = resources.getStringArray(R.array.questions);
        String[] answers = resources.getStringArray(R.array.answers);
        // Creamos el arreglo de preguntas
        questionModel = new QuestionModel[questions.length];
        for (int i = 0; i < questions.length; i++){
            Boolean bool = castString(answers[i]);
            questionModel[i] = new QuestionModel(questions[i], bool);
        }
    }

    /**
     * Metodo para hacer un casting de forma segura de String a Boolean
     * @param bool Palabra que podria ser true o false, en otro caso el metodo regresará false
     * @return La palabra casteada a Boolean
     */
    private Boolean castString(String bool){
        bool = bool.toLowerCase();
        if(bool.contains("true"))
            return true;
        if(bool.contains("false"))
            return false;
        return false;
    }

    /**
     * Método para inicializar los TextView, Botones y el Layout
     */
    private void inicializeVariables(){
        linearLayout = (LinearLayout) findViewById(R.id.layoutQuestion);
        btnTrue = (Button) findViewById(R.id.btnTrue);
        btnFalse = (Button) findViewById(R.id.btnFalse);
        btnNext = (Button) findViewById(R.id.btnNext);
        textViewQuestion = (TextView) findViewById(R.id.textVQuestion);
        textViewCorrect = (TextView) findViewById(R.id.textVCorrect);
        textViewWrong = (TextView) findViewById(R.id.textVWrong);
        btnTrue.setOnClickListener(this);
        btnFalse.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        counterCorrect = 0;
        counterWrong = 0;
        counter = 0;
        textViewCorrect.setText(""+counterCorrect);
        textViewWrong.setText(""+counterWrong);
    }
}