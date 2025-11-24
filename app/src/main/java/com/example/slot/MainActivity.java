package com.example.slot;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int random1, random2, random3, random4, random5, random6;
    private String name,nameet1, age1;
    private int  num, count, played;
    private TextView num1, num2, num3, num4,nametv, num5, num6, numt, countWin,agetv;
    private EditText age, nameEt;
    private Button start,newGame,score,exit;

    public static int count1, gamesPlayed;
    private boolean Run; // Renamed for clarity
    private Handler handler;
    private Runnable numberGeneratorRunnable;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exit = findViewById(R.id.button2);
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
        nametv = findViewById(R.id.Name);
        agetv = findViewById(R.id.agetv);
        nameEt = findViewById(R.id.name1);


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


        Run = false;
        handler = new Handler(Looper.getMainLooper());

        create();
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
                if (played>5){
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
            }}
        });
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nametv.getText().toString();
                scoreAc.putExtra("NAME", name);

                startActivity(scoreAc);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Dialog d = new Dialog(MainActivity.this);
                    d.setContentView(R.layout.mydialoug);
                    Button yes = d.findViewById(R.id.buttonYes);
                    Button no = d.findViewById(R.id.buttonNo);
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                            System.exit(0);
                        }
                    });
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            d.dismiss();
                        }
                    });
                    d.show();
            }
        });
    }
    public void create() {
        Dialog nameDialog = new Dialog(MainActivity.this);
        nameDialog.setContentView(R.layout.namedia);
        nameDialog.setCancelable(false);


        Button submitButton = nameDialog.findViewById(R.id.button3);
        EditText nameInput = nameDialog.findViewById(R.id.name1);
        EditText ageInput = nameDialog.findViewById(R.id.age);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String enteredName = nameInput.getText().toString();
                String enteredAge = ageInput.getText().toString();
                if (enteredName.isEmpty() || enteredAge.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter both name and age", Toast.LENGTH_SHORT).show();
                    return;
                }

                nametv.setText(enteredName);
                agetv.setText(enteredAge);


                nameDialog.dismiss();
            }
        });

        nameDialog.show();
    }
    //public void create() {
        //AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
       // alertDialog.setTitle("Exit");
       // alertDialog.setMessage("Are you sure you want to exit?");
       // alertDialog.setCancelable(true);
       // alertDialog.setIcon(R.drawable.img);
       // alertDialog.setNegativeButton("No", (dialog, which) -> {
        //    dialog.cancel();
     //   });
     //   alertDialog.setPositiveButton("Yes", (dialog, which) -> {
      //      finish();
      //      System.exit(0);
     //   });
      //  AlertDialog dialog = alertDialog.create();
      //  dialog.show();
   // }

}