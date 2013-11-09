package com.example.drinkgauge;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		final EditText Weight = (EditText)findViewById(R.id.txtWeight);
		final EditText Drinks = (EditText)findViewById(R.id.txtDrinks);
		final EditText Time = (EditText)findViewById(R.id.txtHours);
		
		final RadioGroup radioOperation = (RadioGroup)findViewById(R.id.radioOperation);
		final Button calcButton = (Button)findViewById(R.id.calcButton);
		
		final TextView result = (TextView)findViewById(R.id.BAP);
		
		calcButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				double weight = Double.parseDouble(Weight.getText().toString());
				double drinks = Double.parseDouble(Drinks.getText().toString());
				double time = Double.parseDouble(Time.getText().toString());
				
				int selectedOperation = radioOperation.getCheckedRadioButtonId();
				final RadioButton OperOption = (RadioButton)findViewById(selectedOperation);
				String Option = new String(OperOption.getText().toString());
				
				if (Option.equals("Male")) {
					double bap = ((drinks * 3.084) / (weight * 0.73)) - (0.015 * time);
					result.setText(String.valueOf(bap));
				}
				else if (Option.equals("Female")){
					double bap = ((drinks * 3.084) / (weight * 0.66)) - (0.015 * time);
					result.setText(String.valueOf(bap));
				}
				else {
					result.setText(Option);
				}
			}
			
		});
		
	}

}
