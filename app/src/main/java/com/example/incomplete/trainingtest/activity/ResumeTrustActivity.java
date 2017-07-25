package com.example.incomplete.trainingtest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.incomplete.trainingtest.R;
import com.example.incomplete.trainingtest.view.ResumeTrustView;

public class ResumeTrustActivity extends AppCompatActivity {
    ResumeTrustView trustView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_trust);

        trustView = (ResumeTrustView)findViewById(R.id.resume_trust);
        trustView.startAnm(50);

    }
}
