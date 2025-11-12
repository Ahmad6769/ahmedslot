package com.example.slot;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int random1, random2, random3, random4, random5, random6;
    private int  num, count, played;
    private TextView num1, num2, num3, num4, num5, num6, numt, countWin;
    private Button start,newGame,score;

    public static int count1, gamesPlayed;
    private boolean Run; // Renamed for clarity
    private Handler handler;
    private Runnable numberGeneratorRunnable;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.button);
        num1 = findViewById(R.id.aID);
        num2 = findViewById(R.id.bID);
        num3 = findViewById(R.id.cID);
        num4 = findViewById(R.id.dID);
        num5 = findViewById(R.id.eID);
        num6 = findViewById(R.id.fID);
        numt = findViewById(R.id.numID);
        countWin = findViewById(R.id.countWinID);
        newGame = findViewById(R.id.newGameID);
        score = findViewById(R.id.ScoreID);
        count=0;
        played=0;
        gamesPlayed=0;
        count1=0;
        Intent scoreAc = new Intent(MainActivity.this, ScoreActivity.class);

        // --- Initial setup for the 6 static numbers ---
        random1 = (int) (Math.random() * 39) + 1;
        random2 = (int) (Math.random() * 39) + 1;
        random3 = (int) (Math.random() * 39) + 1;
        random4 = (int) (Math.random() * 39) + 1;
        random5 = (int) (Math.random() * 39) + 1;
        random6 = (int) (Math.random() * 39) + 1;

        num1.setText(String.valueOf(random1));
        num2.setText(String.valueOf(random2));
        num3.setText(String.valueOf(random3));
        num4.setText(String.valueOf(random4));
        num5.setText(String.valueOf(random5));
        num6.setText(String.valueOf(random6));

        // --- State and Handler setup ---
        Run = false; // The process is not running initially
        handler = new Handler(Looper.getMainLooper());

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Run = !Run;

                if (Run&&played<6) {
                    start.setText("Stop");
                    start.setBackgroundColor(Color.RED);
                    numberGeneratorRunnable = new Runnable() {
                        @Override
                        public void run() {
                            num = (int) (Math.random() * 39) + 1;
                            numt.setText(String.valueOf(num));

                            handler.postDelayed(this, 1000);
                        }
                    };
                    handler.post(numberGeneratorRunnable);

                } else {
                    start.setText("Start");
                    start.setBackgroundColor(Color.GREEN);
                    if (num==random1&&played<6) {
                        num1.setBackgroundColor(Color.RED);
                        count++;

                    }
                    if(num==random2&&played<6) {
                        num2.setBackgroundColor(Color.RED);
                        count++;
                        countWin.setText(count+" of 6");

                    }
                    if(num==random3&&played<6) {
                        num3.setBackgroundColor(Color.RED);
                        count++;
                        countWin.setText(count+" of 6");
                    }
                    if(num==random4&&played<6) {
                        num4.setBackgroundColor(Color.RED);
                        count++;
                        countWin.setText(count+" of 6");
                    }
                    if(num==random5&&played<6) {
                        num5.setBackgroundColor(Color.RED);
                        count++;
                        countWin.setText(count+" of 6");
                    }
                    if(num==random6&&played<6) {
                        num6.setBackgroundColor(Color.RED);
                        count++;
                        countWin.setText(count+" of 6");
                    }
                    if (numberGeneratorRunnable != null) {
                        handler.removeCallbacks(numberGeneratorRunnable);
                    }
                    played++;
                }
            }
        });
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (played>5)
                    random1 = (int) (Math.random() * 39) + 1;
                    random2 = (int) (Math.random() * 39) + 1;
                    random3 = (int) (Math.random() * 39) + 1;
                    random4 = (int) (Math.random() * 39) + 1;
                    random5 = (int) (Math.random() * 39) + 1;
                    random6 = (int) (Math.random() * 39) + 1;

                num1.setText(String.valueOf(random1));
                num2.setText(String.valueOf(random2));
                num3.setText(String.valueOf(random3));
                num4.setText(String.valueOf(random4));
                num5.setText(String.valueOf(random5));
                num6.setText(String.valueOf(random6));

                num1.setBackgroundColor(Color.WHITE);
                num2.setBackgroundColor(Color.WHITE);
                num3.setBackgroundColor(Color.WHITE);
                num4.setBackgroundColor(Color.WHITE);
                num5.setBackgroundColor(Color.WHITE);
                num6.setBackgroundColor(Color.WHITE);
                Run = false;
                count1+=count;
                played=0;
                count=0;
                gamesPlayed++;
                countWin.setText(count+" of 6");
                start.setText("Start");
            }
        });
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(scoreAc);
            }
        });
    }
}