package com.example.ridempang;

import KeyboardPang.GameMain;
import RidemPang.Base.RidemPangView;
import Technology.Base.TechnologyActivity;
import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.Button;

public class MainActivity extends TechnologyActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitTechnologyActivity();
        SetGameView(new RidemPangView(this,true,true));
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(500);
       //Button btn = new Button(this);
       // GetCurrentLayout().addView(btn);
        
        ApplyCurrentLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
