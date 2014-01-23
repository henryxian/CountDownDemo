package com.henryxian.countdowndemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

	private static final String TAG = "MainActivity";
	private long timeElapsed;
	private boolean timeStartedFlag = false;
	private Button startButton;
	private TextView text;
	private TextView timeElapsedView;
	private MyCountDownTimer myCountDownTimer;
	
	private final long startTime = 50000;
	private final long interval = 1000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startButton = (Button)findViewById(R.id.button);
		startButton.setOnClickListener(this);
		
		text = (TextView)findViewById(R.id.timer);
		timeElapsedView = (TextView)findViewById(R.id.timeElapsed);
		myCountDownTimer = new MyCountDownTimer(startTime, interval);
		text.setText(text.getText() + " " + String.valueOf(startTime));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (!timeStartedFlag){
			myCountDownTimer.start();
			timeStartedFlag = true;
			startButton.setText("Pause");
		}else{
			myCountDownTimer.cancel();
			timeStartedFlag = false;
			startButton.setText("Reset");
		}
	}

	public class MyCountDownTimer extends CountDownTimer{
		public MyCountDownTimer(long startTime, long interval)
		{
			super(startTime, interval);
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			text.setText("Time's up!");
			timeElapsedView.setText("Time elapsed: " + String.valueOf(startTime));
		}

		@Override
		public void onTick(long millisUntilFinished) {
			// TODO Auto-generated method stub
			text.setText("Time remains:" + String.valueOf(millisUntilFinished));
			timeElapsed = startTime - millisUntilFinished;
			timeElapsedView.setText("Time elapsed: " + String.valueOf(timeElapsed));
		}
		
	}
	
}
