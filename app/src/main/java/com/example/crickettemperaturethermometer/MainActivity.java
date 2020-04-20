package com.example.crickettemperaturethermometer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timer;
    Button btnCounter;
    CountDownTimer countDownTimer;
    long timeLeftInMilliseconds = 25000;
    boolean timerRunning;
    Button btnSubmit;
    TextView resultID;
    EditText editText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = findViewById(R.id.timer);
        btnCounter = findViewById(R.id.btnCounter);
        btnSubmit = findViewById(R.id.btnSubmit);
        resultID = findViewById(R.id.resultID);
        editText = findViewById(R.id.editText);

        resultID.setVisibility(View.INVISIBLE);

        btnCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double result;
                result = Double.parseDouble(editText.getText().toString());

                result(result);

                resultID.setVisibility(View.VISIBLE);

            }
        });
    }

    public void result(double result){

        double finalResult;

        finalResult = result / 3 + 4;
        resultID.setText("La temperatura exterior es de " + finalResult);
    }

    public void startStop(){
        if (timerRunning){
            stopTimer();
        }else{
            startTimer();
        }
    }

    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliseconds = millisUntilFinished;
                updateTimer();

            }

            @Override
            public void onFinish() {

            }
        }.start();
        timerRunning = true;
    }

    public void stopTimer(){
        countDownTimer.cancel();
        timerRunning = false;
    }

    public void updateTimer(){
        int seconds = (int)timeLeftInMilliseconds % 60000 / 1000;

        String timeLeft;

        timeLeft = "" + seconds;

        timer.setText(timeLeft);

        if (seconds == 0)
        {
            stopTimer();
            timeLeftInMilliseconds = 25000;
            timer.setText(timeLeft);
        }
    }
}
