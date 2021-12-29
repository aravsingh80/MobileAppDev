package com.example.buttonsandtextviews;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.os.Bundle;
public class MainActivity extends AppCompatActivity {
    Button inc;
    TextView greet;
    int count = 0;
    TextView newbutton2;
    Boolean x = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inc = findViewById(R.id.increment_button);
        greet = findViewById(R.id.greeting_textview);
        newbutton2 = findViewById(R.id.new_button);
        inc.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                System.out.println("Incrementing " + ++count);
                Log.i("incrementing", ""+count);
                greet.setText(""+count);
            }
        });

    }

    public void decrement(View view) {
        System.out.println("Decrementing " + --count);
        greet.setText(""+count);
    }

    public void startover(View view2)
    {
        if(x)
        {
            System.out.println("Incrementing " + ++count);
            greet.setText(""+count);
            newbutton2.setText("Start Over!");
            x = false;
        }
        else
        {
            newbutton2.setText("Start");
            count = 0;
            System.out.println("Count = 0");
            greet.setText("Hi!");
            x = true;
        }
    }
}