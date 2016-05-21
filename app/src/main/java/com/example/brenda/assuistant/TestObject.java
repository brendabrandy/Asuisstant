package com.example.brenda.assuistant;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;

/**
 * Created by Brenda on 5/19/2016.
 */
public class TestObject {

    private int showCaseid;
    private String showCaseDateTime;
    private String showCaseClient;
    private String showCasePerson;
    private Boolean showCaseDone;

    public Boolean getShowCaseDone() {
        return showCaseDone;
    }

    public void setShowCaseDone(Boolean showCaseDone) {
        this.showCaseDone = showCaseDone;
    }

    public int getShowCaseid() {
        return showCaseid;
    }

    public void setShowCaseid(int showCaseid) {
        this.showCaseid = showCaseid;
    }

    public String getShowCaseDateTime() {
        return showCaseDateTime;
    }

    public void setShowCaseDateTime(String showCaseDateTime) {
        this.showCaseDateTime = showCaseDateTime;
    }

    public String getShowCaseClient() {
        return showCaseClient;
    }

    public void setShowCaseClient(String showCaseClient) {
        this.showCaseClient = showCaseClient;
    }

    public String getShowCasePerson() {
        return showCasePerson;
    }

    public void setShowCasePerson(String showCasePerson) {
        this.showCasePerson = showCasePerson;
    }

}

