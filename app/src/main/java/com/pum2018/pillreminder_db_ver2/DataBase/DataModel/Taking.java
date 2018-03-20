package com.pum2018.pillreminder_db_ver2.DataBase.DataModel;

import java.sql.Time;

/**
 * Created by Wlodek on 2018-03-13.
 */

public class Taking {

    private Long _id;
    private Long medicine_id;
    private int dose;
    private Long doseType_id;
    private int hour;
    private int minute;

    // Constructors:
    public Taking() {
    }

    public Taking(Long medicine_id, int dose, Long doseType_id, int hour, int minute) {
        this.medicine_id = medicine_id;
        this.dose = dose;
        this.doseType_id = doseType_id;
        this.hour = hour;
        this.minute = minute;
    }

    //Getter and Setter:


    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Long getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(Long medicine_id) {
        this.medicine_id = medicine_id;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public Long getDoseType_id() {
        return doseType_id;
    }

    public void setDoseType_id(Long doseType_id) {
        this.doseType_id = doseType_id;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String toString(){
        return  "_id: " + _id.toString() +
                " medicine_id: " + medicine_id.toString() +
                " dose: " + Integer.toString(dose) +
                " doseType_id: " + doseType_id.toString() +
                " hour: " + Integer.toString(hour) +
                " minute: " + Integer.toString(minute) +
                "\n\n";
    }
}
