package e.pnaveena.hangman;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String[] words = new String[]{"BANDWAGON", "ESPIONAGE", "KNAPSACK", "GALVANIZE", "PERSUADE", "ABANDONED", "THUMBSCREW", "WRISTWATCH", "YACHTSMAN", "DEVELOPMENT", "PNEUMONIA", "ADVERTISEMENT", "BUZZINGA", "HUNGRY", "WALTZ"};
    int index;

    String a = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        index = random();
        TextView A = findViewById(R.id.word);

        for (int i = 0; i < words[index].length(); i++)
            a = a + "_ ";
        A.setText(a);
        TextView E=findViewById(R.id.bestScore);
        SharedPreferences sharedPreferences=this.getSharedPreferences("bestScore",Context.MODE_PRIVATE);
        int bestScore=sharedPreferences.getInt("score",0);
        E.setText(bestScore+"");


    }

    private int random() {
        Random r = new Random();
        return  r.nextInt(15);
    }


    String b = "";
    int flag=0;
    int score=0;
    int counter=0;
    int f=0;
    

    public void guess(View view) {
        flag=0;
        TextView A = findViewById(R.id.guessedLetters);
        TextView C = findViewById(R.id.word);
        EditText B = findViewById(R.id.guessBox);
        TextView D=findViewById(R.id.score);
        String letter = B.getText().toString();



        if(counter<words[index].length()){

            for (int i = 0; i < words[index].length(); i++) {
             if (letter.charAt(0) == words[index].charAt(i)&&letter.charAt(0)!=a.charAt(2*i)) {
                StringBuilder str = new StringBuilder(a);
                str.setCharAt((2*i),words[index].charAt(i));
                a=str.toString();
                C.setText(a);
                flag=1;

                counter++;


            }


        }
        if(flag==0)
            score++;
        D.setText(score+"");

        b=b+" "+letter.charAt(0);
        A.setText(b  );
        B.setText("");
    }
    else{
         A.setTextSize(25);
        A.setText("WORD COMPLETED");
            TextView E=findViewById(R.id.bestScore);
       SharedPreferences sharedPreferences=getSharedPreferences("bestScore",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        int best =sharedPreferences.getInt("score",0);
            if (f == 0 || best > score) {
                editor.putInt("score",score);
                editor.commit();
                f=1;
                E.setText(score+"");

            }



    }


}}