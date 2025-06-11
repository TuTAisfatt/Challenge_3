package com.example.challenge_3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {

    TextView tvFinalScore;
    Button btnPlayAgain, btnExit;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        tvFinalScore = findViewById(R.id.txtResults);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        btnExit = findViewById(R.id.btnExit);

        int score = getIntent().getIntExtra("score", 0);
        tvFinalScore.setText("Score: " + score);

        btnPlayAgain.setOnClickListener(v -> {
            Intent intent = new Intent(ResultsActivity.this, AdditionActivity.class);
            startActivity(intent);
            finish();
        });

        btnExit.setOnClickListener(v -> {
            Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
