package com.example.brenda.assuistant;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class CallDB {
    public static void main(String[] args) {
        System.out.println(getTable("ticks"));
    }
    public static String getTable(String table){
        String table_data = "";
        try {
            URL url = new URL("http://b343c848.ngrok.io/" + table);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            table_data = br.readLine();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return table_data;
    }
}