package com.example.seng_405_chess_clock_201928028;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ChessClock extends AppCompatActivity {

    public final long TIME_TO_START = 500000;

    private TextView countDownPlayer;
    private TextView countDownOpponent;
    private Button startStopButtonPlayer;
    private Button startStopButtonOpponent;
    private Button resetTimer;
    private CountDownTimer playerCountdownTimer;
    private CountDownTimer opponentCountdownTimer;

    private boolean playerTimerStarted ;
    private boolean opponentTimerStarted ;

    private long timeLeftPlayer = TIME_TO_START;
    private long timeLeftOpponent = TIME_TO_START;

    private boolean playerButtonPressed;
    private boolean opponentButtonPressed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countDownOpponent = findViewById(R.id.timeForOpponent);
        countDownPlayer = findViewById(R.id.timeForPlayer);

        startStopButtonOpponent = findViewById(R.id.opponentButton);
        startStopButtonPlayer = findViewById(R.id.playerButton);

        resetTimer = findViewById(R.id.resetButton);

        buttonClicked();
    }

    private void buttonClicked(){
        startStopButtonOpponent.setOnClickListener(view -> {
            opponentButtonPressed = true;
            if(opponentTimerStarted){
                stopTimer();
            } else {
                startTimer();
            }
        });
        startStopButtonPlayer.setOnClickListener(view -> {
            playerButtonPressed = true;
            if(playerTimerStarted){
                stopTimer();
            } else {
                startTimer();
            }
        });
        resetTimer.setOnClickListener(view -> resetTimerMethod());
    }
    @SuppressLint("SetTextI18n")
    private void startTimer(){
        if (playerButtonPressed){
            playerCountdownTimer = new CountDownTimer(timeLeftPlayer, 1000) {
                @Override
                public void onTick(long millisUntilFinishedPlayer) {
                    timeLeftPlayer = millisUntilFinishedPlayer;
                    updatePlayerTimerText();
                }

                @SuppressLint("SetTextI18n")
                @Override
                public void onFinish() {
                    playerTimerStarted = false;
                    startStopButtonPlayer.setText("START");
                }
            }.start();
            playerTimerStarted = true;
            startStopButtonPlayer.setText("PAUSE");
        }else if (opponentButtonPressed){
            opponentCountdownTimer = new CountDownTimer(timeLeftOpponent, 1000) {
                @Override
                public void onTick(long millisUntilFinishedOpponent) {
                    timeLeftOpponent = millisUntilFinishedOpponent;
                    updateOpponentTimerText();
                }

                @SuppressLint("SetTextI18n")
                @Override
                public void onFinish() {
                    opponentTimerStarted = false;
                    startStopButtonOpponent.setText("START");
                }
            }.start();
            opponentTimerStarted = true;
            startStopButtonOpponent.setText("PAUSE");
        }
    }
    @SuppressLint("SetTextI18n")
    private void stopTimer(){
        if (playerButtonPressed){
            playerCountdownTimer.cancel();
            playerTimerStarted = false;
            startStopButtonPlayer.setText("START");
        }else if (opponentButtonPressed){
            opponentCountdownTimer.cancel();
            opponentTimerStarted = false;
            startStopButtonOpponent.setText("START");
        }
    }
    private void resetTimerMethod(){
        timeLeftOpponent = TIME_TO_START;
        timeLeftPlayer = TIME_TO_START;
        updateOpponentTimerText();
        updatePlayerTimerText();
    }
    private void updatePlayerTimerText(){
        int minutesPlayer = (int) (timeLeftPlayer / 1000) / 60;
        int secondsPlayer = (int) (timeLeftPlayer / 1000) % 60;

        @SuppressLint("DefaultLocale") String timeLeftPlayerFormatted = String.format("%02d:%02d", minutesPlayer, secondsPlayer);

        countDownPlayer.setText(timeLeftPlayerFormatted);
    }
    private void updateOpponentTimerText(){
        int minutesOpponent = (int) (timeLeftOpponent / 1000) / 60;
        int secondsOpponent = (int) (timeLeftOpponent / 1000) % 60;

        @SuppressLint("DefaultLocale") String timeLeftOpponentFormatted = String.format("%02d:%02d", minutesOpponent, secondsOpponent);

        countDownOpponent.setText(timeLeftOpponentFormatted);
    }
}
