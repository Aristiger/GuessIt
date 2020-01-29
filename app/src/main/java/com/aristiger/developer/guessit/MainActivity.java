package com.aristiger.developer.guessit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static int id=0;
    static int id1=0;
    AppCompatButton submit;
    LinearLayout container;
    AppCompatEditText answer;
    AppCompatTextView hint;
    AppCompatTextView question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container=findViewById(R.id.container);
        submit=findViewById(R.id.Submit);
        submit.setOnClickListener(submitButtonListener);
        question=findViewById(R.id.question);
        hint=findViewById(R.id.hint);
        answer=findViewById(R.id.answer);
        readMovieData();
    }
    private List<MovieAux> BollywoodMovies=new ArrayList<>();
    private void readMovieData() {
        InputStream is = getResources().openRawResource(R.raw.bollywood);
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
    }

    View.OnClickListener submitButtonListener=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            question.setText(BollywoodMovies.get(id1).getQuestion().toString());
            hint.setText(BollywoodMovies.get(id1).getHint().toString());
            answer.setText(BollywoodMovies.get(id1).getAnswer().toString());
            id1=(id1+1)%BollywoodMovies.size();
            switch (id)
            {
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
            id=(id+1)%11;
        }
    };
}
