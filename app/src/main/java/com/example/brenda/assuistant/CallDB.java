package com.example.brenda.assuistant;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;


public class CallDB {
    static String  url_address = "http://25989377.ngrok.io/";
    public static void main(String[] args) {
        updateTable_log(4,"GOOG,4;","World ELLO!");
    }


    public static void createMeetings(){
        insertTable_log(1,
                "Yash",
                "Facebook",
                "Jim",
                "2016-05-14 04:15:23",
                false,
                "",
                "");
        insertTable_log(2,
                "Yash",
                "Google",
                "Dave",
                "2016-02-14 13:15:23",
                false,
                "",
                "");
        insertTable_log(3,
                "Yash",
                "Suisse",
                "Mike",
                "2016-11-14 04:00:23",
                false,
                "",
                "");
        insertTable_log(4,
                "Yash",
                "Amazon",
                "Susan",
                "2015-01-14 08:00:15",
                false,
                "",
                "");
        insertTable_log(5,
                "Brenda",
                "Delta",
                "Sandeep",
                "2013-11-12 08:00:23",
                false,
                "",
                "");
        insertTable_log(6,
                "Brenda",
                "Chase",
                "Hilary",
                "2016-08-10 09:00:23",
                false,
                "",
                "");
        insertTable_log(7,
                "Brenda",
                "Microsoft",
                "Cory",
                "2020-01-14 11:00:23",
                false,
                "",
                "");
        insertTable_log(8,
                "Brenda",
                "LG",
                "Bob",
                "2015-05-14 11:02:23",
                false,
                "",
                "");
    }
    public static String getTable(String table){
        String table_data = "";
        try {
            URL url = new URL("http://25989377.ngrok.io/" + table);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            table_data = br.readLine();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return table_data;
    }
    public static void insertTable_log(int id, String employee, String client, String rep, String datetime,boolean done, String ticker_sentiment, String notes){
        String POST = "id=" + Integer.toString(id) + "&employee=" + employee + "&client=" + client
                + "&rep=" + rep + "&datetime=" + datetime + "&done=";
        if (done==true){
            POST += "true";
        }
        else{
            POST += "false";
        }
        POST = POST + "&ticker_sentiment=" + ticker_sentiment + "&notes=" + notes;
        try {
            URL url = new URL( "http://25989377.ngrok.io/log_write");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept-Charset","UTF-8");
            con.setReadTimeout(10000);
            con.setConnectTimeout(15000);
            con.connect();

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes( "log_write?" + POST );
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                System.out.println(response.toString());
            } else {
                System.out.println("POST request not worked");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void updateTable_log(int id, String ticker_sentiment, String notes){
        String POST = "id=" + Integer.toString(id) + "&ticker_sentiment=" + ticker_sentiment + "&notes=" + notes + "&done=true";
        try {
            URL url = new URL( "http://b343c848.ngrok.io/log_update");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept-Charset","UTF-8");
            con.setReadTimeout(10000);
            con.setConnectTimeout(15000);
            con.connect();

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes( "log_update?" + POST );
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                System.out.println(response.toString());
            } else {
                System.out.println("POST request not worked");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}