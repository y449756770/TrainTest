package com.example.incomplete.trainingtest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.incomplete.trainingtest.NDKProtect;
import com.example.incomplete.trainingtest.R;

public class JniActivity extends AppCompatActivity {

    TextView textView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        NDKJniUtiles utils=new NDKJniUtiles();

        NDKProtect utils=new NDKProtect();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(utils.getString()+"**"+utils.getJniString()+"**"+utils.getCodeString());
    }
}
