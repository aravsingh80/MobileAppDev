package com.example.androidlifecycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String TAG = "com.example.sharedpreferences.sharedprefs";
    Button bRight, bLeft, data2, drun, dlife;
    TextView tLeft, tRight;
    TextView oc1, osta1, od1, osto1, oresu1, op1, orest1, oc2, osta2, od2, osto2, oresu2, op2, orest2;
    SeekBar seekBar;
    TextView[] views;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ConstraintLayout layout;
    long startTime, clicks;
    float cPS;
    int countonCreate = 0;
    int countonStart = 0;
    int countonResume = 0;
    int countonPause = 0;
    int countonStop = 0;
    int countonRestart = 0;
    int countonDestroy = 0;
    int countonCreateLife = 0;
    int countonStartLife = 0;
    int countonResumeLife = 0;
    int countonPauseLife = 0;
    int countonStopLife = 0;
    int countonRestartLife = 0;
    int countonDestroyLife = 0;
    String s = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datalayout);
        bRight = findViewById(R.id.bottomright);
        bLeft = findViewById(R.id.bottomleft);
        data2 = findViewById(R.id.databutton);
        tLeft = findViewById(R.id.topleft);
        tRight = findViewById(R.id.topright);
        seekBar = findViewById(R.id.seekbar);
        layout = findViewById(R.id.activity_main_layout);
        views = new TextView[]{bRight, bLeft, tRight, tLeft};
        oc1 = findViewById(R.id.oncreate1);
        oc2 = findViewById(R.id.oncreate2);
        osta2 = findViewById(R.id.onstart2);
        osta1 = findViewById(R.id.onstart1);
        osto2 = findViewById(R.id.onstop2);
        osto1 = findViewById(R.id.onstop1);
        oresu2 = findViewById(R.id.onresume2);
        oresu1 = findViewById(R.id.onresume1);
        orest2 = findViewById(R.id.onrestart2);
        orest1 = findViewById(R.id.onrestart1);
        od2 = findViewById(R.id.ondestroy2);
        od1 = findViewById(R.id.ondestroy1);
        op2 = findViewById(R.id.onpause2);
        op1 = findViewById(R.id.onpause1);
        dlife = findViewById(R.id.deletelife);
        drun = findViewById(R.id.deleterun);
        bRight.setOnClickListener(this);
        bLeft.setOnClickListener(this);
        tLeft.setOnClickListener(this);
        tRight.setOnClickListener(this);
        sharedPreferences = getSharedPreferences(TAG, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int lastProgress;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                for (TextView x : views) {
                    x.setTextSize(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                lastProgress = seekBar.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
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
        countonCreate++;
        countonCreateLife++;
        storeValues();
    }

    private void setInitialValues() {
        for (TextView x : views) {
            x.setText(sharedPreferences.getString(x.getTag().toString(), "0"));
        }
        seekBar.setProgress(30);
        bLeft.setBackgroundColor(Color.parseColor("#1286EE"));
        bRight.setBackgroundColor(Color.parseColor("#EE1212"));
        tLeft.setTextColor(Color.parseColor("#AF0B26"));
        tRight.setTextColor(Color.parseColor("#274541"));
        countonCreateLife = 0;
        countonStartLife = 0;
        countonResumeLife = 0;
        countonPauseLife = 0;
        countonStopLife = 0;
        countonRestartLife = 0;
        countonDestroyLife = 0;
    }


    private void storeValues()
    {
        editor.putInt("topleft", Integer.parseInt(tLeft.getText().toString()));
        editor.putInt("topright", Integer.parseInt(tRight.getText().toString()));
        editor.putInt("bottomleft", Integer.parseInt(bLeft.getText().toString()));
        editor.putInt("bottomright", Integer.parseInt(bRight.getText().toString()));
        editor.putInt("onCreate",  countonCreate);
        editor.putInt("onStart", countonStart);
        editor.putInt("onResume",  countonResume);
        editor.putInt("onPause",  countonPause);
        editor.putInt("onStop",  countonStop);
        editor.putInt("onRestart",  countonRestart);
        editor.putInt("onDestroy",  countonDestroy);
        editor.apply();
        System.out.println("Values onCreate:" + countonCreate);
        System.out.println("Values onStart:" + countonStart);
        System.out.println("Values onResume:" + countonResume);
        System.out.println("Values onPause:" + countonPause);
        System.out.println("Values onStop:" + countonStop);
        System.out.println("Values onRestart:" + countonRestart);
        System.out.println("Values onDestroy:" + countonDestroy);
        System.out.println("Values =============================");
//        s = "onCreate: " + countonCreate;
//        oc1.setText(s);
//        s = "onStart: " + countonStart;
//        osta1.setText(s);
//        s = "onStop: " + countonStop;
//        osto1.setText(s);
//        s = "onResume: " + countonResume;
//        oresu1.setText(s);
//        s = "onRestart: " + countonRestart;
//        orest1.setText(s);
//        s = "onDestroy: " + countonDestroy;
//        od1.setText(s);
//        s = "onPause: " + countonPause;
//        op1.setText(s);
//        s = "onCreate: " + countonCreateLife;
//        oc2.setText(s);
//        s = "onStart: " + countonStartLife;
//        osta2.setText(s);
//        s = "onStop: " + countonStopLife;
//        osto2.setText(s);
//        s = "onResume: " + countonResumeLife;
//        oresu2.setText(s);
//        s = "onRestart: " + countonRestartLife;
//        orest2.setText(s);
//        s = "onDestroy: " + countonDestroyLife;
//        od2.setText(s);
//        s = "onPause: " + countonPauseLife;
//        op2.setText(s);
    }

    private void setInitialValuesLife()
    {
        countonCreate = 0;
        countonStart = 0;
        countonResume = 0;
        countonPause = 0;
        countonStop = 0;
        countonRestart = 0;
        countonDestroy = 0;
    }

    private void erase()
    {
        editor.remove("Values").commit();
    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            Button x2 = (Button) v;
            if (((ColorDrawable) x2.getBackground()).getColor() == Color.parseColor("#EE1212")) {
                x2.setBackgroundColor(Color.parseColor("#EE12EB"));
            } else if (((ColorDrawable) x2.getBackground()).getColor() == Color.parseColor("#EE12EB")) {
                x2.setBackgroundColor(Color.parseColor("#EE1212"));
            } else if (((ColorDrawable) x2.getBackground()).getColor() == Color.parseColor("#1286EE")) {
                x2.setBackgroundColor(Color.parseColor("#EEEB12"));
            } else {
                x2.setBackgroundColor(Color.parseColor("#1286EE"));
            }
            Snackbar snackbar = Snackbar.make(layout, "Background color changed to " + ((ColorDrawable) x2.getBackground()).getColor(), Snackbar.LENGTH_LONG);
            snackbar.setActionTextColor(Color.BLUE);
            View snackBarView = snackbar.getView();
            TextView textView = snackBarView.findViewById(R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        } else {
            TextView x2 = (TextView) v;
            if (x2.getCurrentTextColor() == Color.parseColor("#AF0B26")) {
                x2.setTextColor(Color.parseColor("#9404bf"));
            } else if (x2.getCurrentTextColor() == Color.parseColor("#9404bf")) {
                x2.setTextColor(Color.parseColor("#AF0B26"));
            } else if (x2.getCurrentTextColor() == Color.parseColor("#274541")) {
                x2.setTextColor(Color.parseColor("#bf4904"));
            } else {
                x2.setTextColor(Color.parseColor("#274541"));
            }
            Snackbar snackbar = Snackbar.make(layout, "Text color changed to " + x2.getCurrentTextColor(), Snackbar.LENGTH_LONG);
            snackbar.setActionTextColor(Color.BLUE);
            View snackBarView = snackbar.getView();
            TextView textView = snackBarView.findViewById(R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        }
        TextView x = (TextView) v;
        x.setText("" + (Integer.parseInt(x.getText().toString()) + 1));
        editor.putString(x.getTag().toString(), x.getText().toString()).apply();
        cPS = ++clicks / ((System.currentTimeMillis() - startTime) / 1000f);
        Toast.makeText(this, "" + cPS, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        countonPause++;
        countonPauseLife++;
        storeValues();
    }

    @Override
    protected void onStart() {
        super.onStart();
        countonStart++;
        countonStartLife++;
        storeValues();
    }


    @Override
    protected void onResume() {
        super.onResume();
        countonResume++;
        countonResumeLife++;
        storeValues();
        setInitialValues();
    }

    @Override
    protected void onStop() {
        super.onStop();
        countonStop++;
        countonStopLife++;
        storeValues();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        countonRestart++;
        countonRestartLife++;
        storeValues();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countonDestroy++;
        countonDestroyLife++;
        storeValues();
    }

    public void data(View view) {
//        s = "onCreate: " + countonCreate;
//        oc1.setText(s);
//        s = "onStart: " + countonStart;
//        osta1.setText(s);
//        s = "onStop: " + countonStop;
//        osto1.setText(s);
//        s = "onResume: " + countonResume;
//        oresu1.setText(s);
//        s = "onRestart: " + countonRestart;
//        orest1.setText(s);
//        s = "onDestroy: " + countonDestroy;
//        od1.setText(s);
//        s = "onPause: " + countonPause;
//        op1.setText(s);
//        s = "onCreate: " + countonCreateLife;
//        oc2.setText(s);
//        s = "onStart: " + countonStartLife;
//        osta2.setText(s);
//        s = "onStop: " + countonStopLife;
//        osto2.setText(s);
//        s = "onResume: " + countonResumeLife;
//        oresu2.setText(s);
//        s = "onRestart: " + countonRestartLife;
//        orest2.setText(s);
//        s = "onDestroy: " + countonDestroyLife;
//        od2.setText(s);
//        s = "onPause: " + countonPauseLife;
//        op2.setText(s);
//        oc1 = findViewById(R.id.oncreate1);
//        oc2 = findViewById(R.id.oncreate2);
//        osta2 = findViewById(R.id.onstart2);
//        osta1 = findViewById(R.id.onstart1);
//        osto2 = findViewById(R.id.onstop2);
//        osto1 = findViewById(R.id.onstop1);
//        oresu2 = findViewById(R.id.onresume2);
//        oresu1 = findViewById(R.id.onresume1);
//        orest2 = findViewById(R.id.onrestart2);
//        orest1 = findViewById(R.id.onrestart1);
//        od2 = findViewById(R.id.ondestroy2);
//        od1 = findViewById(R.id.ondestroy1);
//        op2 = findViewById(R.id.onpause2);
//        op1 = findViewById(R.id.onpause1);
//        dlife = findViewById(R.id.deletelife);
//        drun = findViewById(R.id.deleterun);
        setContentView(R.layout.datalayout);
        oc1.setText(" ");
    }

    public void delrun(View view) {
        setInitialValues();
        storeValues();
    }

    public void dellife(View view) {
        setInitialValuesLife();
        storeValues();
    }
}