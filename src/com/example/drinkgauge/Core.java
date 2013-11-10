package com.example.drinkgauge;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;

public class Core extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.core);
		Typeface VitesseBook = Typeface.createFromAsset(getAssets(), "Vitesse-Book.otf");
		
		final TextView tvDrinkNumber = (TextView)findViewById(R.id.tvDrinkNumber);
		final TextView tvBAP = (TextView)findViewById(R.id.tvBAP);
		final TextView tvDrinkSubText = (TextView)findViewById(R.id.tvDrinkSubText);
		final TextView tvBAPSubText = (TextView)findViewById(R.id.tvBAPSubText);
		tvDrinkNumber.setTypeface(VitesseBook);
		tvBAP.setTypeface(VitesseBook);
		tvDrinkSubText.setTypeface(VitesseBook);
		tvBAPSubText.setTypeface(VitesseBook);
		
		/** Boolean isMale = pullBooleanFromPrefs("rb1", getBaseContext());
		Boolean isFemale = pullBooleanFromPrefs("rb2", getBaseContext());
		Long myStarttime = pullLongFromPrefs("starttime", getBaseContext());
		String weight = pullStringFromPrefs("text1", getBaseContext());
		tvDrinkNumber.setText(Long.toString(myStarttime));
		tvBAP.setText(weight); **/
		
		Button plus = (Button) findViewById(R.id.PlusOne);
		plus.setTypeface(VitesseBook);
		
		plus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					double weight = Double.parseDouble(pullStringFromPrefs("text1", getBaseContext()));
					boolean isMale = pullBooleanFromPrefs("rb1", getBaseContext());
					int numdrinks = (Integer.parseInt(tvDrinkNumber.getText().toString())) + 1;
					
					if (numdrinks == 1) {
						long myTime = System.currentTimeMillis();
						pushLongToPrefs("starttime", myTime, getBaseContext());
						double timeDiff = 0;
						tvDrinkSubText.setText("Drink");
						double bap = calculateBAP(isMale, weight, timeDiff, numdrinks);
						tvBAP.setText(Double.toString(bap) + "%");
						
					}
					else {
						tvDrinkSubText.setText("Drinks");
						long myTime = System.currentTimeMillis();
						double timeDiff = (myTime - (pullLongFromPrefs("starttime", getBaseContext()))) / 3600000;
						double bap = calculateBAP(isMale, weight, timeDiff, numdrinks);
						tvBAP.setText(Double.toString(bap) + "%");
					}
					
					tvDrinkNumber.setText(Integer.toString(numdrinks++));
				

			}
		});
		
		Button reset = (Button) findViewById(R.id.Reset);
		reset.setTypeface(VitesseBook);
		
		reset.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					tvBAP.setText("0.0%");
					pushLongToPrefs("starttime", 0, getBaseContext());
					tvDrinkNumber.setText(Integer.toString(0));
			}
		});
		
		Button sub = (Button) findViewById(R.id.SubOne);
		sub.setTypeface(VitesseBook);
		
		sub.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					double weight = Double.parseDouble(pullStringFromPrefs("text1", getBaseContext()));
					boolean isMale = pullBooleanFromPrefs("rb1", getBaseContext());
					int numdrinks = (Integer.parseInt(tvDrinkNumber.getText().toString())) - 1;
					
					if (numdrinks <= -1) {
						/** long myTime = System.currentTimeMillis();
						double timeDiff = (myTime - (pullLongFromPrefs("starttime", getBaseContext()))) / 3600000;
						double bap = calculateBAP(isMale, weight, timeDiff, numdrinks + 1);
						tvBAP.setText(Double.toString(bap) + "%"); **/
						Toast msg = Toast.makeText(getBaseContext(), "No drinks here!", Toast.LENGTH_LONG);
						msg.show();
					} else {
						long myTime = System.currentTimeMillis();
						double timeDiff = (myTime - (pullLongFromPrefs("starttime", getBaseContext()))) / 3600000;
						double bap = calculateBAP(isMale, weight, timeDiff, numdrinks);
						if (numdrinks == 1)
								tvDrinkSubText.setText("Drink");
						else
							tvDrinkSubText.setText("Drinks");
						tvBAP.setText(Double.toString(bap) + "%");
						tvDrinkNumber.setText(Integer.toString(numdrinks--));
					}

			}
		});

	}
	
	public static void pushLongToPrefs(String key, long value, Context context) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
	    SharedPreferences.Editor editor = prefs.edit();
	    editor.putLong(key, value);
	    editor.commit();
	}
	
	public static long pullLongFromPrefs(String key, Context context) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
	    return preferences.getLong(key, 0);
	}
	
	public static void pushStringToPrefs(String key, String value, Context context) {
	    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
	    SharedPreferences.Editor editor = prefs.edit();
	    editor.putString(key, value);
	    editor.commit();
	}

	public static String pullStringFromPrefs(String key, Context context) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
	    return preferences.getString(key, "");
	}
	
	public static void pushBooleanToPrefs(String key, Boolean value, Context context) {
	    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
	    SharedPreferences.Editor editor = prefs.edit();
	    editor.putBoolean(key, value);
	    editor.commit();
	}

	public static Boolean pullBooleanFromPrefs(String key, Context context) {
	    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
	    return preferences.getBoolean(key, false);
	}
	
	public static double calculateBAP(boolean isMale, double weight, double timediff, int numdrinks) {
		double result = 0;
		if (isMale) {
			result = ((numdrinks * 3.084) / (weight * 0.73)) - (0.015 * timediff);
		}
		else {
			result = ((numdrinks * 3.084) / (weight * 0.66)) - (0.015 * timediff);
		}
		return (Math.floor(result * 1000))/1000;
	}

}
