package com.example.brenda.assuistant;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class LogReadActivity extends AppCompatActivity{

    TextView dateLogRead;
    TextView clientLogRead;
    TextView repLogRead;
    ProgressDialog dialog;
    TextView notesText;
    ListView listView;
    String ticker_sentiment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_read);

        //Get title and imageURL from bundle
        Bundle extras = getIntent().getExtras();
        String datetime = extras.getString("DateTime");
        String client = extras.getString("Client");
        String rep = extras.getString("Person");
        ticker_sentiment = extras.getString("Ticker_sentiment");
        String notes = extras.getString("notes");
        dateLogRead = (TextView) findViewById(R.id.dateLogRead);
        clientLogRead = (TextView) findViewById(R.id.clientLogRead);
        repLogRead = (TextView) findViewById(R.id.repLogRead);
        notesText = (TextView) findViewById(R.id.notesText);
        listView = (ListView) findViewById(R.id.summarytable);

        dateLogRead.setText(datetime);
        clientLogRead.setText(client);
        repLogRead.setText(rep);
        notesText.setText(notes);

        //String poop = CallDB.getTable("log");
        //Log.d("LOG READ",poop);
        new logReadTask().execute();
    }

    public class logReadTask extends AsyncTask<String, Void, List<TestObjectSummary> > {

        @Override
        protected void onPreExecute() {
            //Shows dialog box in async task
            super.onPreExecute();
            dialog = new ProgressDialog(LogReadActivity.this);
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            dialog.setMessage("Loading. Please wait...");
            dialog.show();
        }

        @Override
        protected List<TestObjectSummary> doInBackground(String... params) {
            //return List of TestObjects
            List<TestObjectSummary> testObjectSummaryList = new ArrayList<>();

            String delims = "[,;]";
            String[] tokens = ticker_sentiment.split(delims);
            Log.d("ASYNC TASK",tokens.toString());
            for (int i = 0; i < tokens.length; i = i+2) {
                TestObjectSummary o = new TestObjectSummary();
                o.setSentiment(tokens[i + 1]);
                o.setTicker(tokens[i]);
                testObjectSummaryList.add(o);
            }
            return testObjectSummaryList;
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(List<TestObjectSummary> result) {
            //after execution, set adapter and set up listview
            //dismiss dialog
            super.onPostExecute(result);
            dialog.dismiss();
            Log.d("ASYNC TASK",result.toString());
            JsonAdapterSummary myAdapter = new JsonAdapterSummary(getApplicationContext(), R.layout.listviewsummary, result);
            listView.setAdapter(myAdapter);

            // TODO: set result to listView
        }
    }

    public class JsonAdapterSummary extends ArrayAdapter {

        public List<TestObjectSummary> ListObject;
        private int resource;
        private LayoutInflater inflater;


        //Constructor
        public JsonAdapterSummary(Context context, int resource, List<TestObjectSummary> objects) {
            super(context, resource, objects);
            ListObject = objects;
            this.resource = resource;
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        //populates listviewrow with image and title
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = inflater.inflate(resource, null);
            }

            TextView jsonTicker;
            RatingBar jsonSentiment;


            jsonTicker = (TextView) convertView.findViewById(R.id.ticker);
            jsonSentiment = (RatingBar) convertView.findViewById(R.id.sentiment);

            Log.d("JSON ADAPTER",ListObject.get(position).getTicker());
            Log.d("JSON ADAPTER",ListObject.get(position).getSentiment());
            jsonTicker.setText(ListObject.get(position).getTicker());
            jsonSentiment.setRating(Integer.valueOf(ListObject.get(position).getSentiment()));

            return convertView;
        }

    }

}
