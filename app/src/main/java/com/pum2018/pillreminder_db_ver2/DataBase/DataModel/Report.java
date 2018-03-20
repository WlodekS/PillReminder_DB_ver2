package com.pum2018.pillreminder_db_ver2.DataBase.DataModel;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Wlodek on 2018-03-13.
 */

public class Report {

    private Long _id;
    private String medicine_Name;
    private String date;
    private String plannedTime;
    private String actualTime;

    // Constructors:
    public Report() {
    }

    //Constructor without _id

    public Report(String medicine_Name, String date, String plannedTime, String actualTime) {
        this.medicine_Name = medicine_Name;
        this.date = date;
        this.plannedTime = plannedTime;
        this.actualTime = actualTime;
    }


    //Constructor with _id (for update):
    //...Dopisać !!!###


    //Getters and Setters:

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getMedicine_Name() {
        return medicine_Name;
    }

    public void setMedicine_Name(String medicine_Name) {
        this.medicine_Name = medicine_Name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlannedTime() {
        return plannedTime;
    }

    public void setPlannedTime(String plannedTime) {
        this.plannedTime = plannedTime;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String toString(){
        return  "_id: " + _id.toString() +
                " medicine_Name: " + medicine_Name +
                " date: " + date +
                " plannedTime: " + plannedTime +
                " actualTime: " + actualTime +
                "\n\n";
    }
}
