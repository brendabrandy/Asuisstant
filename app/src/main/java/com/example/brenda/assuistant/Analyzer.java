package com.example.brenda.assuistant;

/**
 * Created by Brenda on 5/21/2016.
 */
public class Analyzer {

    String[] positive = new String[]{
            "fluorishing","good","established",
    };

    String[] negative = new String[]{
            "terrible","disappointing","bad",
    };

    //public int sentiment(String[] tokens){
     //   int standard = 50;
     //   for (String token : tokens){
//
  //      }
    //}

    private int finalGrade(int score){
        if (score > 90){
            return 5;
        } else if (score > 70){
            return 4;
        }else if (score > 50){
            return 3;
        }else if (score > 30){
            return 2;
        }else{
            return 1;
        }
    }
}
