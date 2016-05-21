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