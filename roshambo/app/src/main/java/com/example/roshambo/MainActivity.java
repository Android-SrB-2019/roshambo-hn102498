package com.example.roshambo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import static com.example.roshambo.Roshambo.PAPER;
import static com.example.roshambo.Roshambo.SCISSOR;
import static com.example.roshambo.Roshambo.ROCK;
import static com.example.roshambo.Roshambo.WIN;

//Hoang Nguyen
//3-10-2019
public class MainActivity extends AppCompatActivity {

    ImageView userImage;
    ImageView gameImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userImage = findViewById(R.id.imgUser);
        gameImage = findViewById(R.id.imgGame);
    }

    public void makeMove(View view) {
        Roshambo roshambo = new Roshambo();
        switch (view.getId()){
            case R.id.btnPaper:
                roshambo.playerMakesMove(PAPER);
                userImage.setImageResource(R.drawable.paper);
                break;
            case R.id.btnScissors:
                roshambo.playerMakesMove(SCISSOR);
                userImage.setImageResource(R.drawable.scissors);
                break;
            case R.id.btnRock:
                roshambo.playerMakesMove(ROCK);
                userImage.setImageResource(R.drawable.rock);
                break;
        }

        switch (roshambo.getGameMove()){
            case PAPER:
                gameImage.setImageResource(R.drawable.paper);
                break;
            case SCISSOR:
                gameImage.setImageResource(R.drawable.scissors);
                break;
            case ROCK:
                gameImage.setImageResource(R.drawable.rock);
                break;
        }

        int result = roshambo.winLoseOrDraw();
        Toast toast = Toast.makeText(this, getResources().getString(result), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toast.show();
        animate();
    }

    private void animate(){
        ObjectAnimator animatorPlayer = ObjectAnimator.ofFloat(userImage,"rotationX",0f, 360f).setDuration(500);
        ObjectAnimator animatorGame = ObjectAnimator.ofFloat(gameImage,"rotationY",0f, 360f).setDuration(500);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorGame,animatorPlayer);
        set.setInterpolator(new AnticipateOvershootInterpolator());
        set.start();
    }
}
