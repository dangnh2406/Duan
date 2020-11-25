package com.example.duanbanrauxanh.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.duanbanrauxanh.R;
import com.example.duanbanrauxanh.Class.ProgressBarAnimation;

public class LoadScreenActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progressBar = findViewById(R.id.proges);
        progressBar.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
        textView = findViewById(R.id.data);
        progressBar.setMax(100);
        progressBar.setScaleY(3f);
        progesAnimation();

    }

    public void progesAnimation() {
        ProgressBarAnimation progressBarAnimation = new ProgressBarAnimation(this, progressBar, textView, 0f, 100f);
        progressBarAnimation.setDuration(1000);
        progressBar.setAnimation(progressBarAnimation);
    }


}