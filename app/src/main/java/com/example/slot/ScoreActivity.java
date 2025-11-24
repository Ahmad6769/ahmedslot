package com.example.slot;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ScoreActivity extends AppCompatActivity {
    private TextView scoreTextView,gamesplayedTextView,nameTextView;
    private Button back;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        scoreTextView = findViewById(R.id.totalCountID);
        gamesplayedTextView = findViewById(R.id.gamesPlayedID);
        back = findViewById(R.id.BackID);
        nameTextView = findViewById(R.id.textView);
        String name = getIntent().getStringExtra("NAME");

        scoreTextView.setText("Total wins:"+MainActivity.count1);
        gamesplayedTextView.setText("games played:"+MainActivity.gamesPlayed);
        nameTextView.setText(name+ "s score");


        Intent mainAC = new Intent(ScoreActivity.this, MainActivity.class);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mainAC);
            }
        });

    }
}