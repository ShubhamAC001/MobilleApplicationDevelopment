package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {
    // add an instance of Beerexpert
    private BeerExpert expert = new BeerExpert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

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
        // Intent intent = new Intent(this, ReceiveMessageAcitivity.class);

        // You can add extra information to this intent that can be picked up by the activity you're targeting
        // so it can react in some way.
        // intent.putExtra("message", value);
        // intent.putExtra("message", messageText);

        // Instead of creating an intent that's explicitly for ReceiveMessageActivity
        // we are creating an intent that use a send action.
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        // update the code so that two activities are using the same string
        // The first code explicitly tells Android to start ReceiveMessageActivity
        // intent.putExtra(ReceiveMessageActivity.EXTRA_MESSAGE, messageText);
        intent.putExtra(Intent.EXTRA_TEXT, messageText);

        // start ReceiveMessageActivity with the intent
        // startActivity(intent);

        // If we want to change the code to create the chooser
        String chooserTitle = getString(R.string.chooser);
        Intent chosenIntent = Intent.createChooser(intent, chooserTitle);
        startActivity(chosenIntent);
    }
}
