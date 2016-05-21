package com.example.brenda.assuistant;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LogWriteActivity extends AppCompatActivity {

    TextView dateLogRead;
    TextView clientLogRead;
    TextView repLogRead;
    private AutoCompleteTextView itemDescriptionView;
    private ItemMasterAdapter dbHelper;

    static final String[] Tickers = new String[]{
            "AAA","BBB","CCC","DDD","EEE",
            "FFF","GGG","HHH","III","JJJ"
    };

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
        String log = ((EditText)findViewById(R.id.log_write)).getText().toString();
        String delims = "[@]+";
        String[] tokens = log.split(delims);
        String [] tokens2 = new String[100];
        String [] tokens3 = new String[100];
        for(int i=1;i<tokens.length;i++){
            String delims2 = "[ ]+";
            tokens2 = tokens[i].split(delims2);
            tokens3[i-1] = tokens2[0];
        }
        String token = "blah";
        for (int i=0;i<tokens3.length;i++){
            token+= tokens3[i]+','+'0'+';';
        }
        Intent intent = new Intent(this, Broadcast.class);
        startActivity(intent);
    }


}