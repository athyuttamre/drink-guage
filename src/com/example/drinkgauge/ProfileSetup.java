package com.example.drinkgauge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class ProfileSetup extends Activity{
	
	private String SettingsTAG = "DrinkGauge";
	private SharedPreferences prefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.profilesetup);
		
		prefs = getSharedPreferences(SettingsTAG, 0);
		
		Typeface VitesseBook = Typeface.createFromAsset(getAssets(), "Vitesse-Book.otf");
		TextView textView1 = (TextView) findViewById(R.id.textView1);
		TextView textView2 = (TextView) findViewById(R.id.textView2);
		TextView textView3 = (TextView) findViewById(R.id.textView3);
		TextView textView4 = (TextView) findViewById(R.id.textView4);
		textView1.setTypeface(VitesseBook);
		textView2.setTypeface(VitesseBook);
		textView3.setTypeface(VitesseBook);
		textView4.setTypeface(VitesseBook);
		
		final RadioButton rb1 = (RadioButton) findViewById(R.id.radioButton1);
		final RadioButton rb2 = (RadioButton) findViewById(R.id.radioButton2);
		final EditText text1 = (EditText) findViewById(R.id.editText1);
		final long starttime = 0;
		
		rb1.setTypeface(VitesseBook);
		rb2.setTypeface(VitesseBook);
		
		rb1.setChecked(prefs.getBoolean("rb1", false));
		rb2.setChecked(prefs.getBoolean("rb2", false));
		text1.setText(prefs.getString("text1", ""));
		
		Button all_set = (Button) findViewById(R.id.button1);
		all_set.setTypeface(VitesseBook);
		
		all_set.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
							
				/** Editor editor = prefs.edit();
				
				editor.putBoolean("rb1", rb1.isChecked());
				editor.putBoolean("rb2", rb2.isChecked());
				editor.putString("text1", text1.getText().toString());
				editor.putLong("time", starttime);
				
				editor.commit(); **/
				
				pushBooleanToPrefs("rb1", rb1.isChecked(), getBaseContext());
				pushBooleanToPrefs("rb2", rb2.isChecked(), getBaseContext());
				pushStringToPrefs("text1", text1.getText().toString(), getBaseContext());
				pushLongToPrefs("starttime", starttime, getBaseContext());
				
				Intent i = new Intent(ProfileSetup.this, Core.class);
				startActivity(i);
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
	}