package com.pum2018.pillreminder_db_ver2.DataBase.DataModel;

/**
 * Created by Wlodek on 2018-03-13.
 */

public class Medicine {
    private Long _id;
    private String name;

    //Constructors:
    public Medicine() {
    }

    public Medicine(String name) {
        this.name = name;
    }

    //Getters and Setters:
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String toString(){
        return "_id: " + _id.toString() + " Name: " + name + "\n\n";
    }
}
