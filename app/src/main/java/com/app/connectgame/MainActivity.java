package com.app.connectgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0 means yellow, 1 means red
    int activePlayer = 0;
    //2 means emply to represent the states initially
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    //create array contains of winning position array
    //3 vertical position, 3 horizontal, 2 diagonal for winning
    int[] [] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive= true;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        Log.i("Tag", counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        //everytime counter should be empty so no change after having some counter value
        if (gamestate[tappedCounter] == 2 && gameActive) {

            gamestate[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;

            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(3000);
            for (int[] winningPosition : winningPositions) {
                //position 0==1, 1==2 so by default 2==0
                //not any position should be empty i.e equal to 2 so if all condition successful then someone will win
                if (gamestate[winningPosition[0]] == gamestate[winningPosition[1]] &&
                        gamestate[winningPosition[1]] == gamestate[winningPosition[2]]
                        && gamestate[winningPosition[0]] != 2) {

                    gameActive=false;

                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "yellow";
                    } else {
                        winner = "red";
                    }
                    Button button=(Button)findViewById(R.id.playAgainButton);
                    TextView textView=(TextView)findViewById(R.id.winnerTextView);
                    textView.setText(winner+ "has won");
                    button.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void playAgain(View view) {
        Button button=(Button)findViewById(R.id.playAgainButton);
        TextView textView=(TextView)findViewById(R.id.winnerTextView);
        button.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        GridLayout gridLayout=(GridLayout)findViewById(R.id.grid);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            // do stuff with child view
            counter.setImageDrawable(null);
        }
         activePlayer = 0;
            for(int i=0; i<gamestate.length;i++) {
                gamestate[i] = 2;
               // gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
            }
         gameActive= true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
