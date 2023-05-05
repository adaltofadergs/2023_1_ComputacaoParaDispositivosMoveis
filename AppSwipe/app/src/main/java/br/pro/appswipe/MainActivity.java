package br.pro.appswipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout tela;
    private TextView tvTela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tela = findViewById(R.id.layoutTela);
        tvTela = findViewById(R.id.tvTela);

        tela.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeBottom() {
                super.onSwipeBottom();
                tela.setBackgroundColor(Color.GREEN);
                tvTela.setText("Para Baixo");
            }

            @Override
            public void onSwipeTop() {
                super.onSwipeTop();
                tela.setBackgroundColor(Color.BLUE);
                tvTela.setText("Para Cima");
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                tela.setBackgroundColor(Color.RED);
                tvTela.setText("Para Esquerda");
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                tela.setBackgroundColor(Color.YELLOW);
                tvTela.setText("Para Direita");
            }

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                tela.setBackgroundColor(Color.WHITE);
                tvTela.setText("Só um toque");
                return super.onTouch(v, event);
            }
        });


    }
}