package com.example.challenge_3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class AdditionActivity extends AppCompatActivity {

    TextView tvScore, tvLives, tvTime, tvQuestion;
    EditText etAnswer;
    Button btnOK, btnNext;
    int score = 0, lives = 3, questionCount = 0;
    int correctAnswer;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addition_screen);

        // Connect to XML
        tvScore = findViewById(R.id.txtScoreNumber);
        tvLives = findViewById(R.id.txtLifeNumber);
        tvTime = findViewById(R.id.txtTime);
        tvQuestion = findViewById(R.id.txtAddition);
        etAnswer = findViewById(R.id.editTextWriteYourAnswer);
        btnOK = findViewById(R.id.btnOk);
        btnNext = findViewById(R.id.btnNextQuestion);

        startTimer();
        loadNewQuestion();

        btnOK.setOnClickListener(v -> checkAnswer());
        btnNext.setOnClickListener(v -> loadNewQuestion());
    }

    @SuppressLint("SetTextI18n")
    private void loadNewQuestion() {
        if (questionCount >= 5) {
            goToResult();
            return;
        }

        Random rand = new Random();
        int num1 = rand.nextInt(100);
        int num2 = rand.nextInt(100);
        correctAnswer = num1 + num2;

        tvQuestion.setText(num1 + " + " + num2 + " = ?");
        etAnswer.setText("");
        questionCount++;
    }

    private void checkAnswer() {
        String input = etAnswer.getText().toString();
        if (input.isEmpty()) {
            return; // silently ignore empty input
        }

        int userAnswer = Integer.parseInt(input);
        if (userAnswer == correctAnswer) {
            score += 20;
        } else {
            lives--;
        }

        updateUI();

        if (lives <= 0) {
            goToResult();
        }
    }


    @SuppressLint("SetTextI18n")
    private void updateUI() {
        tvScore.setText("" + score);
        tvLives.setText("" + lives);
    }

    private void startTimer() {
        timer = new CountDownTimer(60000, 1000) {
            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {
                tvTime.setText(millisUntilFinished / 1000 + "s");
            }

            @SuppressLint("SetTextI18n")
            public void onFinish() {
                tvTime.setText("Time: 0s");
                goToResult();
            }
        }.start();
    }

    private void goToResult() {
        timer.cancel();
        Intent intent = new Intent(AdditionActivity.this, ResultsActivity.class);
        intent.putExtra("score", score);
        startActivity(intent);
        finish();
    }
}
