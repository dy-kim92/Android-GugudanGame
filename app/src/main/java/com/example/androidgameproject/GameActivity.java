package com.example.androidgameproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    public static int num1 = (int)(Math.random() * 9) + 1;
    public static int num2 = (int)(Math.random() * 9) + 1;

    private int result = 0;
    private int resultTotal = 0;
    private TextView textQ;
    private TextView resultGame;
    private EditText inputNum;
    private TextView ox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ox = findViewById(R.id.ox);
        textQ = findViewById(R.id.textQ);
        textQ.setText(String.format("%d X %d = ?", num1, num2));
        inputNum = findViewById(R.id.editTextNumber);
        resultGame = findViewById(R.id.gameResultText);
        resultGame.setText(String.format("총 %d개의 문제 중 %d개를 맞췄습니다.", resultTotal, result));

        View btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("result", result);
                intent.putExtra("resultTotal", resultTotal);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        View btnContinue = findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    int inputResult = Integer.parseInt(inputNum.getText().toString());
                    int aResult = num1 * num2;

                    if (aResult == inputResult) {   //  정답일 경우 resultTotal, result 모두 1 증가
                        ox.setText("O");
                        result++;
                        resultTotal++;
                    } else {                        //  오답일 경우 resultTotal 만 1 증가
                        ox.setText("X");
                        resultTotal++;
                    }
                    //  새로운 변수, 문제출력, 결과출력
                    num1 = (int) (Math.random() * 9) + 1;
                    num2 = (int) (Math.random() * 9) + 1;
                    textQ.setText(String.format("%d X %d = ?", num1, num2));
                    resultGame.setText(String.format("총 %d개의 문제 중 %d개를 맞췄습니다.", resultTotal, result));
                } catch (NumberFormatException e){  //  정답 미입력 시 예외처리 Toast
                    Toast.makeText(GameActivity.this,
                            "정답을 입력해주세요",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}
