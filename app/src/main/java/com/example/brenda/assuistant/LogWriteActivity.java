package com.example.brenda.assuistant;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
    EditText logger;
    int id;
    String log;
    ProgressDialog nDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_write);
        Bundle extras = getIntent().getExtras();
        String datetime = extras.getString("DateTime");
        String client = extras.getString("Client");
        String rep = extras.getString("Person");
        id = extras.getInt("ID");
        //Boolean done = extras.getBoolean("Done");

        dateLogRead = (TextView) findViewById(R.id.dateLogRead);
        clientLogRead = (TextView) findViewById(R.id.clientLogRead);
        repLogRead = (TextView) findViewById(R.id.repLogRead);
        logger = (EditText) findViewById(R.id.activity_write);
        dateLogRead.setText(datetime);
        clientLogRead.setText(client);
        repLogRead.setText(rep);

        /*
        speech = SpeechRecognizer.createSpeechRecognizer(this);
        speech.setRecognitionListener(this);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,
                "en");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                this.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    speech.startListening(recognizerIntent);
                }
                else {
                    speech.stopListening();
                }
                }
        });

         */

    }

    public void logsubmit(View view){

        nDialog = new ProgressDialog(LogWriteActivity.this);
        nDialog.setTitle("Uploading information");
        nDialog.setMessage("Loading..");
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(true);
        new jsonTaskWriteLog().execute();
    }


    public class jsonTaskWriteLog extends AsyncTask<String, String, String > {

        @Override
        protected void onPreExecute() {
            //Shows dialog box in async task
            super.onPreExecute();
            log = logger.getText().toString();
            nDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
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
            Log.d("LOG WRITE",Integer.toString(id));
            CallDB.updateTable_log(id,token,log);

           return log;
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            nDialog.dismiss();
            Intent intent = new Intent(LogWriteActivity.this, Broadcast.class);
            startActivity(intent);
            // TODO: set result to listView
        }
    }

    /*
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_AT:
                //Set up progress dialog as json is being retrieved
                dialog = new ProgressDialog(this);
                dialog.setIndeterminate(true);
                dialog.setCancelable(false);
                dialog.setMessage("Loading. Please wait...");

                new jsonTask3().execute();
                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }

    public class jsonTask3 extends AsyncTask<String, Void, List<TestObject3> > {

        BufferedReader reader;
        HttpURLConnection conn;

        @Override
        protected void onPreExecute() {
            //Shows dialog box in async task
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected List<TestObject3> doInBackground(String... params) {
            //return List of TestObjects
            List<TestObject3> testObjectList = new ArrayList<>();

            String jsonString = null;
            try {
                InputStream is = getAssets().open("ticker.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                jsonString = new String(buffer, "UTF-8");
                JSONObject reader = new JSONObject(jsonString);
                JSONArray parentArray = reader.getJSONArray("ticker");
                //String finalString = meetings.toString();
                //Log.d("MEETING FRAGMENT", finalString);
                //JSONArray parentArray = new JSONArray(finalString);//the json returned is an array
                for (int i = 0; i < parentArray.length(); i++) {
                    //get ith object from json and set properties of object according
                    //to the jsonobject
                    JSONObject childObject = parentArray.getJSONObject(i);
                    TestObject3 testObject = new TestObject3();
                    testObject.setTicker(childObject.getString("Ticker"));
                    testObjectList.add(testObject);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            return testObjectList;
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(List<TestObject3> result) {
            //after execution, set adapter and set up listview
            //dismiss dialog
            super.onPostExecute(result);
            dialog.dismiss();
           /*
            RelativeLayout rl = new RelativeLayout(getApplicationContext());

            ArrayList<String> arrayList = new ArrayList<String>();
            for (int i=0;i<result.size();i++) {
                arrayList.add(result.get(i).getTicker());
            }
            Spinner list = new Spinner(getApplicationContext());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_spinner_dropdown_item, arrayList);
            list.setAdapter(adapter);
            rl.addView(list);
            setContentView(rl);
            */

    //Attempted to implement Intellisense for the Ticker. This is the reason for the Frame Layout in activity_log_write.xml,
    // and the parsing of the json using TestObject3.java and this file here. Hung up on the need to dynamically create a spinner,
    //however even after avoiding that issue, returning back to the EditText where it left off.

}