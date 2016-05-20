package com.example.brenda.assuistant;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ysharma1126 on 5/19/16.
 */
public class Broadcast extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast);
    }

    public void activitysubmit(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
