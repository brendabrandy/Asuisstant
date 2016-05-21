package com.example.brenda.assuistant;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by ysharma1126 on 5/19/16.
 */
public class Broadcast extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast);
        DataBaseHelper db = new DataBaseHelper(getApplicationContext());
        String user = (String) db.getUserDetails().get("name");
    }

    public void activitysubmit(View view){
        String broadcast = ((EditText)findViewById(R.id.log_write)).getText().toString();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

