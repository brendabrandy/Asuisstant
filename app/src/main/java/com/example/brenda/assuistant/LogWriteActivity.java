package com.example.brenda.assuistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class LogWriteActivity extends AppCompatActivity {

    TextView dateLogRead;
    TextView clientLogRead;
    TextView repLogRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_write);
        //Get title and imageURL from bundle
        Bundle extras = getIntent().getExtras();
        String datetime = extras.getString("DateTime");
        String client = extras.getString("Client");
        String rep = extras.getString("Person");

        dateLogRead = (TextView) findViewById(R.id.dateLogRead);
        clientLogRead = (TextView) findViewById(R.id.clientLogRead);
        repLogRead = (TextView) findViewById(R.id.repLogRead);

        dateLogRead.setText(datetime);
        clientLogRead.setText(client);
        repLogRead.setText(rep);
    }

    public void logsubmit(View view){
        Intent intent = new Intent(this, Broadcast.class);
        startActivity(intent);
    }
}