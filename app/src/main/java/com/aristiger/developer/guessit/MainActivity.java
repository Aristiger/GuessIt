package com.aristiger.developer.guessit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static int id=0;
    static int idR=0;
    static int value;
    static String answerValue;
    AppCompatButton hintButton;
    AppCompatButton submit;
    LinearLayout container;
    AppCompatEditText answer;
    AppCompatTextView hint;
    AppCompatTextView question;
    Intent intent;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    private List<MovieAux> BollywoodMovies=new ArrayList<>();

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent=getIntent();
        container=findViewById(R.id.container);
        submit=findViewById(R.id.Submit);
        hintButton=findViewById(R.id.hintButton);
        submit.setOnClickListener(submitButtonListener);
        question=findViewById(R.id.question);
        hint=findViewById(R.id.hint);
        hint.setVisibility(View.INVISIBLE);
        answer=findViewById(R.id.answer);
        sharedPref = getApplicationContext().getSharedPreferences("ID",MODE_PRIVATE);
        editor=getSharedPreferences("ID",MODE_PRIVATE).edit();
        value=intent.getIntExtra("key",-1);
        if(value==0)
        {
            idR=sharedPref.getInt("idB",0);
            readMovieData("bollywood");
        }
        else
        {
            idR=sharedPref.getInt("idH",0);
            readMovieData("hollywood");
        }
        Toast.makeText(getApplicationContext(),"idR="+idR,Toast.LENGTH_LONG).show();
    }
    private void readMovieData(String filename) {
        InputStream is;
        if(filename.equals("bollywood"))
            is = getResources().openRawResource(R.raw.bollywood);
        else
            is = getResources().openRawResource(R.raw.hollywood);
        BufferedReader reader=new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String line="";
        try {
            reader.readLine();
            while ((line = reader.readLine() )!= null) {
                String[] tokens=line.split(",");
                MovieAux movie=new MovieAux();
                movie.setQuestion(tokens[0]);
                movie.setHint(tokens[1]);
                movie.setAnswer(tokens[2]);
                BollywoodMovies.add(movie);
            }
        }
        catch(IOException e){
            Log.wtf("MyActivity","Error reading data file on line "+line,e);
            e.printStackTrace();
        }
        question.setText(BollywoodMovies.get(idR).getQuestion());
        hint.setText(Html.fromHtml("<b>Hint</b>"));
        hint.append(": "+ BollywoodMovies.get(idR).getHint());
        hint.setVisibility(View.INVISIBLE);
        answerValue=BollywoodMovies.get(idR).getAnswer().trim();
    }

    View.OnClickListener submitButtonListener=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(submit.getText().toString().equals("NEXT QUESTION"))
            {
                shiftToNextQuestion();
                return;
            }
            if (answer.getText().toString().trim().equalsIgnoreCase(answerValue)) {
                Toast.makeText(getApplicationContext(), "Correct Answer!!!", Toast.LENGTH_SHORT).show();
                shiftToNextQuestion();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Try Again!!!", Toast.LENGTH_SHORT).show();
            }
        }
    };
    public void onClickTakeHint(View view) {

        if(hint.getVisibility()==View.VISIBLE)
        {
            answer.setText(answerValue);
            submit.setText("NEXT QUESTION");
            return;
        }
        if(hint.getVisibility()==View.INVISIBLE)
        {
            hint.setVisibility(View.VISIBLE);
            hintButton.setText("SHOW ANSWER");
        }
    }
    public void shiftToNextQuestion()
    {
        submit.setText("SUBMIT");
        hintButton.setText("TAKE HINT");
        answer.setText("");
        idR = (idR + 1) % BollywoodMovies.size();
        question.setText(BollywoodMovies.get(idR).getQuestion());
        hint.setText(Html.fromHtml("<b>Hint</b>"));
        hint.append(": " + BollywoodMovies.get(idR).getHint());
        hint.setVisibility(View.INVISIBLE);
        answerValue=BollywoodMovies.get(idR).getAnswer();
        switch (id) {
            case 1:
                container.setBackgroundResource(R.drawable.behongo);
                break;
            case 2:
                container.setBackgroundResource(R.drawable.forest);
                break;
            case 3:
                container.setBackgroundResource(R.drawable.frost);
                break;
            case 4:
                container.setBackgroundResource(R.drawable.dark_skies);
                break;
            case 5:
                container.setBackgroundResource(R.drawable.joomla);
                break;
            case 6:
                container.setBackgroundResource(R.drawable.nighthawk);
                break;
            case 7:
                container.setBackgroundResource(R.drawable.purple);
                break;
            case 8:
                container.setBackgroundResource(R.drawable.purple_bliss);
                break;
            case 9:
                container.setBackgroundResource(R.drawable.terminal);
                break;
            case 10:
                container.setBackgroundResource(R.drawable.turquoise);
                break;
            case 0:
                container.setBackgroundResource(R.drawable.royal);
                break;
        }
        id = (id + 1) % 11;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(value==0)
        {
            editor.putInt("idB",idR);
        }
        else
        {
            editor.putInt("idH",idR);
        }
        editor.apply();
        Toast.makeText(getApplicationContext(),"Putting value"+Integer.toString(idR),Toast.LENGTH_LONG).show();
    }
}
