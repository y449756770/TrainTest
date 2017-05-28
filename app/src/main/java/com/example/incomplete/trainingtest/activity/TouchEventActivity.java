package com.example.incomplete.trainingtest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

import com.example.incomplete.trainingtest.R;
import com.example.incomplete.trainingtest.utiles.TouchEventUtil;

public class TouchEventActivity extends Activity {


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.w("sunzn", "TouchEventActivity | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
		return super.dispatchTouchEvent(ev);
	}

	public boolean onTouchEvent(MotionEvent event) {
		Log.w("sunzn", "TouchEventActivity | onTouchEvent --> " + TouchEventUtil.getTouchAction(event.getAction()));
		return super.onTouchEvent(event);
	}
	

}