package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String TAG = "com.example.sharedpreferences.sharedprefs";
    Button bRight, bLeft;
    TextView tLeft, tRight;
    SeekBar seekBar;
    TextView[] views;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ConstraintLayout layout;
    long startTime, clicks;
    float cPS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bRight = findViewById(R.id.bottomright);
        bLeft = findViewById(R.id.bottomleft);
        tLeft = findViewById(R.id.topleft);
        tRight = findViewById(R.id.topright);
        seekBar = findViewById(R.id.seekbar);
        layout = findViewById(R.id.activity_main_layout);
        views = new TextView[]{bRight, bLeft, tRight, tLeft};
        bRight.setOnClickListener(this);
        bLeft.setOnClickListener(this);
        tLeft.setOnClickListener(this);
        tRight.setOnClickListener(this);
        sharedPreferences = getSharedPreferences(TAG, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            int lastProgress;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                for(TextView x : views)
                {
                    x.setTextSize(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
                lastProgress = seekBar.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                Snackbar snackbar = Snackbar.make(layout, "Font Size changed to " + seekBar.getProgress() + "sp", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                seekBar.setProgress(lastProgress);
                                for (TextView x : views) {
                                    x.setTextSize(lastProgress);
                                }
                                Snackbar.make(layout, "Font Size reverted back to " + lastProgress + "sp", Snackbar.LENGTH_LONG);
                            }
                        });
                        snackbar.setActionTextColor(Color.BLUE);
                        View snackBarView = snackbar.getView();
                        TextView textView = snackBarView.findViewById(R.id.snackbar_text);
                        textView.setTextColor(Color.WHITE);
                        snackbar.show();
                     }
                });
                layout.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        editor.clear().apply();
                        setInitialValues();
                        return false;
                    }
                });
                setInitialValues();
                startTime = System.currentTimeMillis();
            }

    private void setInitialValues()
    {
        for(TextView x : views)
        {
            x.setText(sharedPreferences.getString(x.getTag().toString(),"0"));
        }
        seekBar.setProgress(30);
        bLeft.setBackgroundColor(Color.parseColor("#1286EE"));
        bRight.setBackgroundColor(Color.parseColor("#EE1212"));
        tLeft.setTextColor(Color.parseColor("#AF0B26"));
        tRight.setTextColor(Color.parseColor("#274541"));
    }

    @Override
    public void onClick(View v)
    {
        if(v instanceof Button)
        {
            Button x2 = (Button)v;
            if(((ColorDrawable)x2.getBackground()).getColor() == Color.parseColor("#EE1212"))
            {
                x2.setBackgroundColor(Color.parseColor("#EE12EB"));
            }
            else if(((ColorDrawable)x2.getBackground()).getColor() == Color.parseColor("#EE12EB"))
            {
                x2.setBackgroundColor(Color.parseColor("#EE1212"));
            }
            else if(((ColorDrawable)x2.getBackground()).getColor() == Color.parseColor("#1286EE"))
            {
                x2.setBackgroundColor(Color.parseColor("#EEEB12"));
            }
            else
            {
                x2.setBackgroundColor(Color.parseColor("#1286EE"));
            }
            Snackbar snackbar = Snackbar.make(layout, "Background color changed to " + ((ColorDrawable)x2.getBackground()).getColor(), Snackbar.LENGTH_LONG);
            snackbar.setActionTextColor(Color.BLUE);
            View snackBarView = snackbar.getView();
            TextView textView = snackBarView.findViewById(R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        }
        else
        {
            TextView x2 = (TextView)v;
            if(x2.getCurrentTextColor() == Color.parseColor("#AF0B26"))
            {
                x2.setTextColor(Color.parseColor("#9404bf"));
            }
            else if(x2.getCurrentTextColor() == Color.parseColor("#9404bf"))
            {
                x2.setTextColor(Color.parseColor("#AF0B26"));
            }
            else if(x2.getCurrentTextColor() == Color.parseColor("#274541"))
            {
                x2.setTextColor(Color.parseColor("#bf4904"));
            }
            else
            {
                x2.setTextColor(Color.parseColor("#274541"));
            }
            Snackbar snackbar = Snackbar.make(layout, "Text color changed to " + x2.getCurrentTextColor(), Snackbar.LENGTH_LONG);
            snackbar.setActionTextColor(Color.BLUE);
            View snackBarView = snackbar.getView();
            TextView textView = snackBarView.findViewById(R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        }
        TextView x = (TextView)v;
        x.setText("" + (Integer.parseInt(x.getText().toString()) + 1));
        editor.putString(x.getTag().toString(), x.getText().toString()).apply();
        cPS = ++clicks/((System.currentTimeMillis() - startTime)/1000f);
        Toast.makeText(this, "" + cPS, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setInitialValues();
    }
}