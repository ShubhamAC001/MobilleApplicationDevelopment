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

        Intent intent = getIntent();
        String messageText = intent.getStringExtra(EXTRA_MESSAGE);
        TextView messageView = (TextView) findViewById(R.id.message);
        messageView.setText(messageText);
    }


}
