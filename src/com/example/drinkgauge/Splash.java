package com.example.drinkgauge;

import android.os.Bundle;
import android.view.Window;
import android.app.Activity;
import android.content.Intent;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);
		
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(5000);
				} catch(InterruptedException e) {
					e.printStackTrace();
				} finally{
					Intent startNext = new Intent(Splash.this, ProfileSetup.class);
					startActivity(startNext);
				}
			}
		};
		timer.start();
	}

}
