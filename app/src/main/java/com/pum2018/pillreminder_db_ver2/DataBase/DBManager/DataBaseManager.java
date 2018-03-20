package com.pum2018.pillreminder_db_ver2.DataBase.DBManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pum2018.pillreminder_db_ver2.DataBase.DataModel.DoseType;
import com.pum2018.pillreminder_db_ver2.DataBase.DataModel.Medicine;
import com.pum2018.pillreminder_db_ver2.DataBase.DataModel.Message;
import com.pum2018.pillreminder_db_ver2.DataBase.DataModel.Report;
import com.pum2018.pillreminder_db_ver2.DataBase.DataModel.Store;
import com.pum2018.pillreminder_db_ver2.DataBase.DataModel.Taking;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wlodek on 2018-03-13.
 */

public class DataBaseManager extends SQLiteOpenHelper {

    // Database name:
    private static final String DB_NAME = "PillRemDB";

    // Database version:
    private static final int DB_VERSION = 13;

    // Table names:
    private static final String MEDICINE_TABLE = "medicine";
    private static final String DOSETYPE_TABLE = "dosetypes";
    private static final String STORE_TABLE = "store";
    private static final String TAKING_TABLE = "takings";
    private static final String REPORT_TABLE = "reports";
    private static final String MESSAGES_TABLE = "messages";


    // TABLE COLUMNS :
    // MEDICINE table columns:
    private static final String MED_KEY_ID = "_id";
    private static final String MED_KEY_NAME = "name";

    // DOSETYPE table columns:
    private static final String DOS_KEY_ID = "_id";
    private static final String DOS_KEY_NAME = "name";

    // STORE table columns:
    private static final String STO_KEY_ID = "_id";
    private static final String STO_KEY_MEDICINE_ID = "medicine_id";
    private static final String STO_KEY_QUANTITY = "quantity";

    // TAKING table columns:
    private static final String TAK_KEY_ID = "_id";
    private static final String TAK_KEY_MEDICINE_ID = "medicine_id";
    private static final String TAK_KEY_DOSE = "dose";
    private static final String TAK_KEY_DOSETYPE_ID = "dosetype_id";
    private static final String TAK_KEY_HOUR = "hour";
    private static final String TAK_KEY_MINUTE = "minute";


    // REPORT table columns:
    private static final String REP_KEY_ID = "_id";
    private static final String REP_KEY_MEDICINENAME = "medicineName";
    private static final String REP_KEY_DATE = "date";
    private static final String REP_KEY_PLANNEDTIME = "plannedtime";
    private static final String REP_KEY_ACTUALTIME = "actualtime";

    // MESSAGES table columns:
    private static final String MES_KEY_ID = "_id";
    private static final String MES_KEY_TIME = "creatingDateTime";
    private static final String MES_KEY_TEXT = "text";


    //UWAGA! (W.S.):
    //When Error: E/SQLiteLog: (1) near "AUTOINCREMENT": syntax error
    //Description:
    //There is NO AUTOINCREMENT keyword in SQLite!!

    // Medicine Table - create string (I'm doing it the first because reference to it later)
    private static final String CREATE_MEDICINE_TABLE =
            "CREATE TABLE if not exists " + MEDICINE_TABLE + " ("
                    + MED_KEY_ID + " INTEGER PRIMARY KEY NOT NULL,"
                    + MED_KEY_NAME + " CHAR(30) NOT NULL"
                    + ");";


    // DoseType Table - create string:
    private static final String CREATE_DOSETYPE_TABLE =
            "CREATE TABLE if not exists " + DOSETYPE_TABLE + " ("
                    + DOS_KEY_ID + " INTEGER PRIMARY KEY NOT NULL,"
                    + DOS_KEY_NAME + " CHAR(20) NOT NULL"
                    + ");";

    // Store Table - create string:
    private static final String CREATE_STORE_TABLE =
            "CREATE TABLE if not exists " + STORE_TABLE + " ("
                    + STO_KEY_ID + " INTEGER PRIMARY KEY NOT NULL,"
                    + STO_KEY_MEDICINE_ID + " INTEGER NOT NULL,"
                    + STO_KEY_QUANTITY + " INTEGER, "
                    + "FOREIGN KEY (" + STO_KEY_MEDICINE_ID + ") REFERENCES " + MEDICINE_TABLE + "(" + MED_KEY_ID + ")"
                    + ");";


    // Taking Table - create string:
    private static final String CREATE_TAKING_TABLE =
            "CREATE TABLE if not exists " + TAKING_TABLE + " ("
                    + TAK_KEY_ID + " INTEGER PRIMARY KEY NOT NULL,"
                    + TAK_KEY_MEDICINE_ID + " INTEGER NOT NULL,"
                    + TAK_KEY_DOSE + " INTEGER,"
                    + TAK_KEY_DOSETYPE_ID + " INTEGER NOT NULL,"
                    + TAK_KEY_HOUR + " INTEGER,"
                    + TAK_KEY_MINUTE + " INTEGER, "
                    + "FOREIGN KEY (" + TAK_KEY_MEDICINE_ID + ") REFERENCES " + MEDICINE_TABLE + "(" + MED_KEY_ID + "), "
                    + "FOREIGN KEY (" + TAK_KEY_DOSETYPE_ID + ") REFERENCES " + DOSETYPE_TABLE + "(" + DOS_KEY_ID + ")"
                    + ");";


    // Report Table - create string:
    private static final String CREATE_RAPORT_TABLE =
            "CREATE TABLE if not exists " + REPORT_TABLE + " ("
                    + REP_KEY_ID + " INTEGER PRIMARY KEY NOT NULL, "
                    + REP_KEY_MEDICINENAME + " CHAR(30), "
                    + REP_KEY_DATE + " CHAR(10), "
                    + REP_KEY_PLANNEDTIME + " CHAR(5), "
                    + REP_KEY_ACTUALTIME + " CHAR(5) "
                    + ");";


    // Messages Table - create string:
    private static final String CREATE_MESSAGES_TABLE =
            "CREATE TABLE if not exists " + MESSAGES_TABLE + " ("
                    + MES_KEY_ID + " INTEGER PRIMARY KEY NOT NULL, "
                    + MES_KEY_TIME + " TIME, "
                    + MES_KEY_TEXT + " CHAR(30) "
                    + ");";


    // Constructor:
    public DataBaseManager(Context context){
        super(context, DB_NAME, null, DB_VERSION);


    }

    //Creating tables:

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MEDICINE_TABLE);
        db.execSQL(CREATE_DOSETYPE_TABLE);
        db.execSQL(CREATE_STORE_TABLE);
        db.execSQL(CREATE_TAKING_TABLE);
        db.execSQL(CREATE_RAPORT_TABLE);
        db.execSQL(CREATE_MESSAGES_TABLE);
        Log.d("DB", "Metoda onCreate - Baza danych zostala utworzona.");
    }

    // TODO: In the PROF. version change it. This version delete old data!
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //DROP tables:
        db.execSQL("DROP TABLE IF EXISTS " + MEDICINE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DOSETYPE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + STORE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TAKING_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + REPORT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MESSAGES_TABLE);
        Log.d("DB", "Metoda onUpgrade - Tabele w bazie zostaly skasowane.");

        //Creating new version of tables:
        onCreate(db);
    }

    //=====================================
    //     C R E A T E   M E T H O D S
    //=====================================

    /**
     * dbCreateMedicine
     * takes a medicine object and inserts the data into the database
     * Param1: medicine - medicine object
     * Return: _id (long) generated by the database for the added medicine
     */
    public long dbCreateMedicine(Medicine medicine) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MED_KEY_NAME, medicine.getName());
        //Insert row:
        long medicine_id = db.insertOrThrow(MEDICINE_TABLE, null, values);

        //LOG CAT:
        Log.d("DB", "Table: " + MEDICINE_TABLE  + ". Record added. _id= " + medicine_id);

        return medicine_id;
    }

    /**
     * dbCreateDoseType
     * takes a DoseType object and inserts the data into the database
     * Param1: doseType - DoseType object
     * Return: _id (long) generated by the database for the added doseType
     */
    public long dbCreateDoseType(DoseType doseType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DOS_KEY_NAME, doseType.getName());
        //Insert row:
        long dosetype_id = db.insertOrThrow(DOSETYPE_TABLE, null, values);

        //LOG CAT:
        Log.d("DB", "Table: " + DOSETYPE_TABLE  + ". Record added. _id= " + dosetype_id);

        return dosetype_id;
    }

    /**
     * dbCreateStore
     * takes a Store object and inserts the data into the database
     * Param1: store - Store object
     * Param2: quantity - Integer. Number of medicine in the store
     * Return: _id (long) generated by the database for the added store
     */
    public long dbCreateStore(Integer medicine_ID, Integer quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STO_KEY_MEDICINE_ID, medicine_ID);
        values.put(STO_KEY_QUANTITY, quantity);
        //Insert row:
        long store_id = db.insertOrThrow(STORE_TABLE, null, values);

        //LOG CAT:
        Log.d("DB", "Table: " + STORE_TABLE  + ". Record added. _id= " + store_id);

        return store_id;
    }

    /**
     * dbCreateTaking
     * takes a Taking object and inserts the data into the database
     * Param1: store - Store object
     * Param2: quantity - Integer. Number of medicine in the store
     * Return: _id (long) generated by the database for the added taking
     */
    public long dbCreateTaking(Integer medicine_id, Integer dose, Integer dosetype_id,
                             Integer hour, Integer minute) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TAK_KEY_MEDICINE_ID, medicine_id);
        values.put(TAK_KEY_DOSE, dose);
        values.put(TAK_KEY_DOSETYPE_ID, dosetype_id);
        values.put(TAK_KEY_HOUR, hour);
        values.put(TAK_KEY_MINUTE, minute);
        //Insert row:
        long taking_id = db.insertOrThrow(TAKING_TABLE, null, values);

        //LOG CAT:
        Log.d("DB", "Table: " + TAKING_TABLE  + ". Record added. _id= " + taking_id);

        return taking_id;
    }


    /**
     * dbCreateReport
     * takes a Report object and inserts the data into the database
     * Param1: report - Report object
     * Return: _id (long) generated by the database for the added report
     */
    public long dbCreateReport(String medicine_name, String sDate, String sPlannedTime, String sActualTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(REP_KEY_MEDICINENAME, medicine_name);
        values.put(REP_KEY_DATE, sDate);
        values.put(REP_KEY_PLANNEDTIME, sPlannedTime);
        values.put(REP_KEY_ACTUALTIME, sActualTime);
        //Insert row:
        long report_id = db.insertOrThrow(REPORT_TABLE, null, values);

        //LOG CAT:
        Log.d("DB", "Table: " + REPORT_TABLE  + ". Record added. _id= " + report_id);

        return report_id;
    }


    /**
     * dbCreateMessage
     * takes a Message object and inserts the data into the database
     * Param1: message - Message object
     * Return: _id (long) generated by the database for the added message
     */
    public long dbCreateMessage(String sDateTime, String sText) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MES_KEY_TIME, sDateTime);
        values.put(MES_KEY_TEXT, sText);
        //Insert row:
        long message_id = db.insertOrThrow(MESSAGES_TABLE, null, values);

        //LOG CAT:
        Log.d("DB", "Table: " + MESSAGES_TABLE  + ". Record added. _id= " + message_id);

        return message_id;
    }

    //========================================
    //     R E A D (GET)    M E T H O D S
    //========================================
    //----------------
    // Get 1 element :
    //----------------
    /**
     * dbGetMedicine
     * Get a Medicine object from the table MEDICINE_TABLE
     * Param1: id of Medicine object
     * Return: Medicine object
     */
    public Medicine dbGetMedicine(Long id) {
        String sSelectString = "SELECT * FROM " +
                MEDICINE_TABLE + " WHERE "      +
                MED_KEY_ID   + " = " + id;
        Medicine medicine = new Medicine();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sSelectString, null);
        if (cursor != null){
            cursor.moveToFirst();
            medicine.set_id(cursor.getLong(0));
            medicine.setName(cursor.getString(1));
        }
        cursor.close();
        return medicine;
    }


    /**
     * dbGetDoseType
     * Get a DoseType object from the table DOSETYPE_TABLE
     * Param1: id of DoseType object
     * Return: DoseType object
     */
    public DoseType dbGetDoseType(Long id) {
        String sSelectString = "SELECT * FROM " +
                DOSETYPE_TABLE + " WHERE "      +
                DOS_KEY_ID   + " = " + id;
        DoseType dosetype = new DoseType();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sSelectString, null);
        if (cursor != null){
            cursor.moveToFirst();
            dosetype.set_id(cursor.getLong(0));
            dosetype.setName(cursor.getString(1));
        }
        cursor.close();
        return dosetype;
    }

    /**
     * dbGetStore
     * Get a Store object from the table STORE_TABLE
     * Param1: id of Store object
     * Return: Store object
     */
    public Store dbGetStore(Integer id) {
        String sSelectString = "SELECT * FROM " +
                STORE_TABLE + " WHERE "      +
                STO_KEY_ID   + " = " + id;
        Store store = new Store();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sSelectString, null);
        if (cursor != null){
            cursor.moveToFirst();
            store.set_id(cursor.getLong(0));
            store.setMedicine_id(cursor.getLong(1));
            store.setQuantity(cursor.getInt(2));
        }
        cursor.close();
        return store;
    }


    /**
     * dbGetTaking
     * Get a Taking object from the table TAKING_TABLE
     * Param1: id of Taking object
     * Return: Taking object
     */
    public Taking dbGetTaking(Integer id) {
        String sSelectString = "SELECT * FROM " +
                TAKING_TABLE + " WHERE "      +
                TAK_KEY_ID   + " = " + id;
        Taking taking = new Taking();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sSelectString, null);
        if (cursor != null){
            cursor.moveToFirst();
            taking.set_id(cursor.getLong(0));
            taking.setMedicine_id(cursor.getLong(1));
            taking.setDose(cursor.getInt(2));
            taking.setDoseType_id(cursor.getLong(3));
            taking.setHour(cursor.getInt(4));
            taking.setMinute(cursor.getInt(5));
        }
        cursor.close();
        return taking;
    }

    /**
     * dbGetReport
     * Get a Report object from the table REPORT_TABLE
     * Param1: id of Report object
     * Return: Report object
     */
    public Report dbGetReport(Integer id) {
        String sSelectString = "SELECT * FROM " +
                REPORT_TABLE + " WHERE "      +
                REP_KEY_ID   + " = " + id;
        Report report = new Report();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sSelectString, null);
        if (cursor != null){
            cursor.moveToFirst();
            report.set_id(cursor.getLong(0));
            report.setMedicine_Name(cursor.getString(1));
            report.setDate(cursor.getString(2));
            report.setPlannedTime(cursor.getString(3));
            report.setActualTime(cursor.getString(4));
        }
        cursor.close();
        return report;
    }

    /**
     * dbGetMessage
     * Get a Message object from the table MESSAGE_TABLE
     * Param1: id of Message object
     * Return: Message object
     */
    public Message dbGetMessage(Integer id) {
        String sSelectString = "SELECT * FROM " +
                MESSAGES_TABLE + " WHERE "      +
                MES_KEY_ID   + " = " + id;
        Message message = new Message();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sSelectString, null);
        if (cursor != null){
            cursor.moveToFirst();
            message.set_id(cursor.getLong(0));
            message.setCreatingDateTime(cursor.getString(1));
            message.setText(cursor.getString(2));
        }
        cursor.close();
        return message;
    }

    //---------------------------
    // Get list of all elements :
    //---------------------------

    /**
     * dbGetAllMedicines
     * Get all Medicine rows from table MEDICINE_TABLE
     * Params: no params
     * Return: list of medicine objects
     */
    public List<Medicine> dbGetAllMedicines() {
        List<Medicine> medicines = new ArrayList<>();
        String selectString = "SELECT * FROM " + MEDICINE_TABLE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectString, null);
        if (cursor != null){
            while(cursor.moveToNext()){
                Medicine medicine = new Medicine();
                medicine.set_id(cursor.getLong(0));
                medicine.setName(cursor.getString(1));
                medicines.add(medicine);
            }
        }
        cursor.close();
        return medicines;
    }

    /**
     * dbGetAllDoseTypes
     * Get all DoseType rows from table DOSETYPE_TABLE
     * Params: no params
     * Return: list of DoseType objects
     */
    public List<DoseType> dbGetAllDoseTypes() {
        List<DoseType> dosetypes = new ArrayList<>();
        String selectString = "SELECT * FROM " + DOSETYPE_TABLE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectString, null);
        if (cursor != null){
            while(cursor.moveToNext()){
                DoseType dosetype = new DoseType();
                dosetype.set_id(cursor.getLong(0));
                dosetype.setName(cursor.getString(1));
                dosetypes.add(dosetype);
            }
        }
        cursor.close();
        return dosetypes;
    }


    /**
     * dbGetAllStores
     * Get all Store rows from table STORE_TABLE
     * Params: no params
     * Return: list of Store objects
     */
    public List<Store> dbGetAllStores() {
        List<Store> stores = new ArrayList<>();
        String selectString = "SELECT * FROM " + STORE_TABLE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectString, null);
        if (cursor != null){
            while(cursor.moveToNext()){
                Store store = new Store();
                store.set_id(cursor.getLong(0));
                store.setMedicine_id(cursor.getLong(1));
                store.setQuantity(cursor.getInt(2));
                stores.add(store);
            }
        }
        cursor.close();
        return stores;
    }

    /**
     * dbGetAllTakings
     * Get all Taking rows from table TAKING_TABLE
     * Params: no params
     * Return: list of Taking objects
     */
    public List<Taking> dbGetAllTakings() {
        List<Taking> takings = new ArrayList<>();
        String selectString = "SELECT * FROM " + TAKING_TABLE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectString, null);
        if (cursor != null){
            while(cursor.moveToNext()){
                Taking taking = new Taking();
                taking.set_id(cursor.getLong(0));
                taking.setMedicine_id(cursor.getLong(1));
                taking.setDose(cursor.getInt(2));
                taking.setDoseType_id(cursor.getLong(3));
                taking.setHour(cursor.getInt(4));
                taking.setMinute(cursor.getInt(5));
                takings.add(taking);
            }
        }
        cursor.close();
        return takings;
    }

    /**
     * dbGetAllReports
     * Get all Report rows from table REPORT_TABLE
     * Params: no params
     * Return: list of Report objects
     */
    public List<Report> dbGetAllReports() {
        List<Report> reports = new ArrayList<>();
        String selectString = "SELECT * FROM " + REPORT_TABLE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectString, null);
        if (cursor != null){
            while(cursor.moveToNext()){
                Report report = new Report();
                report.set_id(cursor.getLong(0));
                report.setMedicine_Name(cursor.getString(1));
                report.setDate(cursor.getString(2));
                report.setPlannedTime(cursor.getString(3));
                report.setActualTime(cursor.getString(4));
                reports.add(report);
            }
        }
        cursor.close();
        return reports;
    }

    /**
     * dbGetAllMessages
     * Get all Message rows from table MESSAGES_TABLE
     * Params: no params
     * Return: list of Message objects
     */
    public List<Message> dbGetAllMessages() {
        List<Message> messages = new ArrayList<>();
        String selectString = "SELECT * FROM " + MESSAGES_TABLE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectString, null);
        if (cursor != null){
            while(cursor.moveToNext()){
                Message message = new Message();
                message.set_id(cursor.getLong(0));
                message.setCreatingDateTime(cursor.getString(1));
                message.setText(cursor.getString(2));
                messages.add(message);
            }
        }
        cursor.close();
        return messages;
    }


    //======================================
    //     U P D A T E    M E T H O D S
    //======================================

    /**
     * dbUpdateMedicine
     * Update a Medicine object in the table MEDICINE_TABLE
     * Param1: Medicine object
     * Return: nothing
     */
    public void dbUpdateMedicine(Medicine medicine) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MED_KEY_NAME, medicine.getName());
        //Put _id of medicine in the list of arguments:
        String[] args = {String.valueOf(medicine.get_id())};
        //Update row:
        db.update(MEDICINE_TABLE, values, MED_KEY_ID+"=?", args);

        //LOG CAT:
        Log.d("DB", "Table: " + MEDICINE_TABLE  + ". Record updated. _id= " + medicine.get_id());
    }

    /**
     * dbUpdateDoseType
     * Update a DoseType object in the table DOSETYPE_TABLE
     * Param1: DoseType object
     * Return: nothing
     */
    public void dbUpdateDoseType(DoseType dosetype) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DOS_KEY_NAME, dosetype.getName());
        //Put _id of dosetype in the list of arguments:
        String[] args = {String.valueOf(dosetype.get_id())};
        //Update row:
        db.update(DOSETYPE_TABLE, values, DOS_KEY_ID+"=?", args);

        //LOG CAT:
        Log.d("DB", "Table: " + DOSETYPE_TABLE  + ". Record updated. _id= " + dosetype.get_id());

    }

    /**
     * dbUpdateStore
     * Update a Store object in the table STORE_TABLE
     * Param1: Store object
     * Return: nothing
     */
    public void dbUpdateStore(Store store) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STO_KEY_MEDICINE_ID, store.getMedicine_id());
        values.put(STO_KEY_QUANTITY, store.getQuantity());
        //Put _id of store in the list of arguments:
        String[] args = {String.valueOf(store.get_id())};
        //Update row:
        db.update(STORE_TABLE, values, STO_KEY_ID+"=?", args);

        //LOG CAT:
        Log.d("DB", "Table: " + STORE_TABLE  + ". Record updated. _id= " + store.get_id());
    }

    /**
     * dbUpdateTaking
     * Update a Taking object in the table TAKING_TABLE
     * Param1: Taking object
     * Return: nothing
     */
    public void dbUpdateTaking(Taking taking) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TAK_KEY_MEDICINE_ID, taking.getMedicine_id());
        values.put(TAK_KEY_DOSE, taking.getDose());
        values.put(TAK_KEY_DOSETYPE_ID, taking.getDoseType_id());
        values.put(TAK_KEY_HOUR, taking.getHour());
        values.put(TAK_KEY_MINUTE, taking.getMinute());
        //Put _id of store in the list of arguments:
        String[] args = {String.valueOf(taking.get_id())};
        //Update row:
        db.update(TAKING_TABLE, values, TAK_KEY_ID+"=?", args);

        //LOG CAT:
        Log.d("DB", "Table: " + TAKING_TABLE  + ". Record updated. _id= " + taking.get_id());

    }

    /**
     * dbUpdateReport
     * Update a Report object in the table REPORT_TABLE
     * Param1: Report object
     * Return: nothing
     */
    public void dbUpdateReport(Report report) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(REP_KEY_MEDICINENAME, report.getMedicine_Name());
        values.put(REP_KEY_DATE, String.valueOf(report.getDate()));
        values.put(REP_KEY_PLANNEDTIME, String.valueOf(report.getPlannedTime()));
        values.put(REP_KEY_ACTUALTIME, String.valueOf(report.getActualTime()));
        //Put _id of store in the list of arguments:
        String[] args = {String.valueOf(report.get_id())};
        //Update row:
        db.update(REPORT_TABLE, values, REP_KEY_ID+"=?", args);

        //LOG CAT:
        Log.d("DB", "Table: " + REPORT_TABLE  + ". Record updated. _id= " + report.get_id());

    }

    /**
     * dbUpdateMessage
     * Update a Message object in the table MESSAGE_TABLE
     * Param1: Message object
     * Return: nothing
     */
    public void dbUpdateMessage(Message message) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MES_KEY_TIME, String.valueOf(message.getCreatingDateTime()));
        values.put(MES_KEY_TEXT, message.getText());
        //Put _id of store in the list of arguments:
        String[] args = {String.valueOf(message.get_id())};
        //Update row:
        db.update(MESSAGES_TABLE, values, MES_KEY_ID+"=?", args);

        //LOG CAT:
        Log.d("DB", "Table: " + MESSAGES_TABLE  + ". Record updated. _id= " + message.get_id());
    }

    //======================================
    //     D E L E T E    M E T H O D S
    //======================================

    /**
     * dbDeleteMedicine
     * delete a Medicine object in the database by his _id
     * Param1: _id of Medicine object
     * Return: nothing
     */
    public void dbDeleteMedicine(long medicineID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = {"" + medicineID};
        //delete row where _id=medicineID:
        db.delete(MEDICINE_TABLE, "_id=?", args);

        //LOG CAT:
        Log.d("DB", "Table: " + MEDICINE_TABLE  + ". Record deleted. _id= " + medicineID);
    }

    /**
     * dbDeleteDoseType
     * delete a DoseType object in the database by his _id
     * Param1: _id of DoseType object
     * Return: nothing
     */
    public void dbDeleteDoseType(long dosetypeID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = {"" + dosetypeID};
        //delete row where _id=dosetypeID:
        db.delete(DOSETYPE_TABLE, "_id=?", args);

        //LOG CAT:
        Log.d("DB", "Table: " + DOSETYPE_TABLE  + ". Record deleted. _id= " + dosetypeID);
    }

    /**
     * dbDeleteStore
     * delete a Store object in the database by his _id
     * Param1: _id of Store object
     * Return: nothing
     */
    public void dbDeleteStore(long storeID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = {"" + storeID};
        //delete row where _id=storeID:
        db.delete(STORE_TABLE, "_id=?", args);

        //LOG CAT:
        Log.d("DB", "Table: " + STORE_TABLE  + ". Record deleted. _id= " + storeID);
    }

    /**
     * dbDeleteTaking
     * delete a Taking object in the database by his _id
     * Param1: _id of Taking object
     * Return: nothing
     */
    public void dbDeleteTaking(long takingID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = {"" + takingID};
        //delete row where _id=takingID:
        db.delete(TAKING_TABLE, "_id=?", args);

        //LOG CAT:
        Log.d("DB", "Table: " + TAKING_TABLE  + ". Record deleted. _id= " + takingID);
    }

    /**
     * dbDeleteReport
     * delete a Report object in the database by his _id
     * Param1: _id of Report object
     * Return: nothing
     */
    public void dbDeleteReport(long reportID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = {"" + reportID};
        //delete row where _id=reportID:
        db.delete(REPORT_TABLE, "_id=?", args);

        //LOG CAT:
        Log.d("DB", "Table: " + REPORT_TABLE  + ". Record deleted. _id= " + reportID);

    }

    /**
     * dbDeleteMessage
     * delete a Message object in the database by his _id
     * Param1: _id of Message object
     * Return: nothing
     */
    public void dbDeleteMessage(long messageID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = {"" + messageID};
        //delete row where _id=messageID:
        db.delete(MESSAGES_TABLE, "_id=?", args);

        //LOG CAT:
        Log.d("DB", "Table: " + MESSAGES_TABLE  + ". Record deleted. _id= " + messageID);

    }

    public String getDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        java.util.Date date = new java.util.Date();
        return dateFormat.format(date);
    }

    //==================================
    //     T E S T    M E T H O D S
    //==================================

    public void TEST_TableNames() {
        int iRecordNumber;  //tables counter
        SQLiteDatabase db = this.getWritableDatabase();
        //Wyświetlenie
        //DoseType dt = new DoseType();
        //dt.toString();
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type= 'table' AND name!='android_metadata' order by name", null);
        c.moveToFirst();
        //Log.d("DB", "List of tables.");
        Log.d("DB", "Number of tables: "+c.getCount());
        iRecordNumber = 1;
        while (!c.isAfterLast()){
            Log.d("DB", iRecordNumber +  ". Table name: " + c.getString(0));
            c.moveToNext();
            iRecordNumber += 1;
        }
        Log.d("DB", "-----------------");
    }

    public void TEST_DeleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MEDICINE_TABLE, null, null);
        db.delete(MEDICINE_TABLE, null, null);
        db.delete(DOSETYPE_TABLE, null, null);
        db.delete(STORE_TABLE, null, null);
        db.delete(TAKING_TABLE, null, null);
        db.delete(REPORT_TABLE, null, null);
        db.delete(MESSAGES_TABLE, null, null);
        Log.d("DB", "TEST_DeleteAllData-Wszystkie dane w tabelach zostały skasowane.");
        Log.d("DB", "-----------------");
    }

    public void TEST_TypeTableMedicine(){
        SQLiteDatabase db = this.getReadableDatabase();
        //Log.d("DB", "-----------------");
        Log.d("DB", "Content of Tables Medicine :");
        //Log.d("DB", "-----------------");
        for(Medicine m: dbGetAllMedicines() ){ Log.d("DB", m.toString());  }
        if(dbGetAllMedicines().isEmpty()){Log.d("DB", "Table empty");}
        Log.d("DB", "-----------------");
    }

    public void TEST_TypeTableDoseType(){
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("DB", "Content of Tables DoseType :");
        for(DoseType dt: dbGetAllDoseTypes() ){ Log.d("DB", dt.toString());  }
        if(dbGetAllDoseTypes().isEmpty()){Log.d("DB", "Table empty");}
        Log.d("DB", "-----------------");
    }

    public void TEST_TypeTableStore(){
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("DB", "Content of Tables Store :");
        for(Store st: dbGetAllStores() ){ Log.d("DB", st.toString());  }
        if(dbGetAllStores().isEmpty()){Log.d("DB", "Table empty");}
        Log.d("DB", "-----------------");
    }

    public void TEST_TypeTableTaking(){
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("DB", "Content of Tables Taking :");
        for(Taking t: dbGetAllTakings() ){ Log.d("DB", t.toString());  }
        if(dbGetAllTakings().isEmpty()){Log.d("DB", "Table empty");}
        Log.d("DB", "-----------------");
    }

    public void TEST_TypeTableReport(){
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("DB", "Content of Tables Report :");
        for(Report r: dbGetAllReports() ){ Log.d("DB", r.toString());  }
        if(dbGetAllReports().isEmpty()){Log.d("DB", "Table empty");}
        Log.d("DB", "-----------------");
    }


    public void TEST_TypeTableMessages(){
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("DB", "Content of Tables Messages :");
        for(Message m: dbGetAllMessages() ){ Log.d("DB", m.toString());  }
        if(dbGetAllMessages().isEmpty()){Log.d("DB", "Table empty");}
        Log.d("DB", "-----------------");
    }

    public void TEST_WriteTablesContent(DataBaseManager dbm) {
        //SQLiteDatabase db = dbm.getReadableDatabase();
        Log.d("DB", "-----------------");
        Log.d("DB", "DataBase Content:");
        Log.d("DB", "-----------------");
        Log.d("DB", "Tables Medicine :");
        for(Medicine m: dbm.dbGetAllMedicines() ){ Log.d("DB", m.toString());  }
        if(dbGetAllMedicines().isEmpty()){Log.d("DB", "EMPTY");}
        Log.d("DB", "-----");
        Log.d("DB", "Tables DoseType :");
        for(DoseType d: dbm.dbGetAllDoseTypes() ){ Log.d("DB", d.toString());  }
        if(dbGetAllDoseTypes().isEmpty()){Log.d("DB", "EMPTY");}
        Log.d("DB", "-----");
        Log.d("DB", "Tables Store :");
        for(Store s: dbm.dbGetAllStores() ){ Log.d("DB", s.toString());  }
        if(dbGetAllStores().isEmpty()){Log.d("DB", "EMPTY");}
        Log.d("DB", "-----");
        Log.d("DB", "Tables Taking :");
        for(Taking t: dbm.dbGetAllTakings() ){ Log.d("DB", t.toString());  }
        if(dbGetAllTakings().isEmpty()){Log.d("DB", "EMPTY");}
        Log.d("DB", "-----");
        Log.d("DB", "Tables Reports :");
        for(Report r: dbm.dbGetAllReports() ){ Log.d("DB", r.toString());  }
        if(dbGetAllReports().isEmpty()){Log.d("DB", "EMPTY");}
        Log.d("DB", "-----");
        Log.d("DB", "Tables Messages :");
        for(Message m: dbm.dbGetAllMessages()){ Log.d("DB", m.toString());  }
        if(dbGetAllMessages().isEmpty()){Log.d("DB", "EMPTY");}
        Log.d("DB", "-----------------");
    }

    public void TEST_WriteHeaderToLOG(String msg) {
        //write a header to LOG
        String Line_2 = "" + msg;
        Integer lenLine_2 = Line_2.length();
        String Line_1 = new String(new char[lenLine_2]).replace("\0", "=");
        Log.d("DB", Line_1);
        Log.d("DB", Line_2);
        Log.d("DB", Line_1);
    }


}
