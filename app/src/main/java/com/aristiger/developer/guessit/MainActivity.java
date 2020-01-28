package com.aristiger.developer.guessit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    static int id=0;
    AppCompatButton submit;
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container=findViewById(R.id.container);
        submit=findViewById(R.id.Submit);
        submit.setOnClickListener(submitButtonListener);
    }
    View.OnClickListener submitButtonListener=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
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
