package com.pum2018.pillreminder_db_ver2.DataBase.DataModel;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Wlodek on 2018-03-14.
 */

public class Message {

    private Long _id;
    private String creatingDateTime;
    private String text;


    //Constructors:
    public Message() {
    }

    public Message(String creatingDateTime, String text, Integer readIt) {
        this.creatingDateTime = creatingDateTime;
        this.text = text;
    }

    //Getters and Settes:


    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getCreatingDateTime() {
        return creatingDateTime;
    }

    public void setCreatingDateTime(String creatingDateTime) {
        this.creatingDateTime = creatingDateTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String toString(){
        return
                "_id: " + _id.toString() +
                " creatingDateTime: " + creatingDateTime +
                " text: " + text +
                "\n\n";
    }
}
