package com.sharanthangavel.onetask;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText textView; // EditText allows user to change the text, TextView doesn't
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Tells what view to set when you first open the app
        setContentView(R.layout.activity_main);

        // Find the view for the respective variables
        textView = (EditText) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);

        // When you open the app, you want it to show the last thing you had open
        final SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE); // private so only your app can access it

        // Now that we have a link to the sharedpreferences, we need to get the value out of it
        String oldItem = sharedPref.getString("oldItem", "Nothing created yet"); // If no old item, say nothing created yet

        // Assign oldItem string to our text view
        textView.setText(oldItem);

        // If you have an event attached to the button, it puts the new text from the textview into shared preferences
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            // This is going to run everytime someone clicks the button
            public void onClick(View view) {
                // When click button, we want to store the textview inside the sharedpreferences
                SharedPreferences.Editor editor = sharedPref.edit(); // Allows us to edit the values inside
                editor.putString("oldItem", textView.getText().toString());
                editor.commit();

                // Add some animation
                Animation animation = new AlphaAnimation(1.0f, 0.0f);
                animation.setDuration(500); // 1000 milliseconds is 1 second

                // Apply the animation to the button
                button.startAnimation(animation);

                Log.d("DEBUG", "Button clicked"); // Used for debugging. Send ourselves a message
            }
        });
    }
}
