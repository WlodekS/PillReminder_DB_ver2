package com.pum2018.pillreminder_db_ver2.DataBase.DataModel;

/**
 * Created by Wlodek on 2018-03-13.
 */

public class Store {

    private Long _id;
    private Long medicine_id;
    private int quantity;

    // Constructors:
    public Store() {
    }

    public Store(Long medicine_id, int quantity) {
        this.medicine_id = medicine_id;
        this.quantity = quantity;
    }

    //Getters and Setters:

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString(){
        return  "_id: " + _id.toString() +
                " medicine_id: " + medicine_id.toString() +
                " quantity: " + Integer.toString(quantity) +
                "\n\n";
    }
}

