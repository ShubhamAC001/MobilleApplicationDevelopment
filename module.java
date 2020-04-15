// module in MainActivity.java

package com.hfad.myfirstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.List;

// MainAcitivity extends the Android class android.app.Activity
public class MainActivity extends Acitivity {

	@Override 
	// Implement the OnCreate() method from the Activity Android class.
	// This method is called when the activity is first created
	protected void OnCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// specify which layout to use
		setContentView(R.layout.activity_main)
	}

	// button onclick module
	public void onClickFindBeer(View view) {
		// Get a reference to the TextView
		TextView brands = (TextView) findViewById(R.id.brands);

		// Get a reference to the Spinner
		Spinner color = (Spinner) findViewById(R.id.color);

		// Get the selected item in the Spinner
		String beerType = String.valueOf(color.getSelectedItem());
		
		// Display the selected item
		// brands.setText(beerType);

		// Get recommendations from the BeerExpert class
        List<String> brandsList = expert.getBrands(beerType);
        StringBuilder brandsFormatted = new StringBuilder();
        for (String brand: brandsList) {
            brandsFormatted.append(brand).append('\n');
        }
        // Display the beers
        brands.setText(brandsFormatted);
	}

	// Call onSendMessage() when the button is clicked
    public void onSendMessage(View view) {
        EditText messageView = (EditText) findViewById(R.id.message);

        // Get the text that in the EditText
        String messageText = messageView.getText().toString();

        // Create new intent: Intent intent = new Intent(this, Target.class);
        Intent intent = new Intent(this, ReceiveMessageAcitivity.class);

        // You can add extra information to this intent that can be picked up by the activity you're targeting
        // so it can react in some way.
        // intent.putExtra("message", value);
        // intent.putExtra("message", messageText);

        // update the code so that two activities are using the same string
        intent.putExtra(ReceiveMessageAcitivity.EXTRA_MESSAGE, messageText);

        // start ReceiveMessageActivity with the intent
        startActivity(intent);
    }
}

// code in BeerExpert.java
package com.example.testing;

import java.util.ArrayList;
import java.util.List;

public class BeerExpert {
    List<String> getBrands(String color) {
        List<String> brands = new ArrayList<>();
        if (color.equals("amber")) {
            brands.add("Jack Amber");
            brands.add("Red Moose");
        } else {
            brands.add("Jail Pale Ale");
            brands.add("Gout Stout");
        }
        return brands;
    }
}

// code in ReceiveMessageActivity.java
package com.example.testing;

import android.app.Activity;
import  android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

// make sure your activity extends the Activity class
public class ReceiveMessageAcitivity extends Activity {
    // This is the name of the extra value we're passing in the intent
    public static final String EXTRA_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message_acitivity);

        // Get the Intent
        Intent intent = getIntent();

        // Get the string transferred by intent using the same variable name
        String messageText = intent.getStringExtra(EXTRA_MESSAGE);

        // Find the TextViiew component
        TextView messageView = (TextView) findViewById(R.id.message);

        // Print the String
        messageView.setText(messageText);
    }
}
