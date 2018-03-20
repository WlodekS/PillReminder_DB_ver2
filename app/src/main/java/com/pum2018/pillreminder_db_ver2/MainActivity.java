package com.pum2018.pillreminder_db_ver2;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pum2018.pillreminder_db_ver2.DataBase.DBManager.DataBaseManager;
import com.pum2018.pillreminder_db_ver2.DataBase.DataModel.DoseType;
import com.pum2018.pillreminder_db_ver2.DataBase.DataModel.Medicine;
import com.pum2018.pillreminder_db_ver2.DataBase.DataModel.Message;
import com.pum2018.pillreminder_db_ver2.DataBase.DataModel.Report;
import com.pum2018.pillreminder_db_ver2.DataBase.DataModel.Store;
import com.pum2018.pillreminder_db_ver2.DataBase.DataModel.Taking;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //*******************
        // TEST OF DATABASE :
        //*******************
        DataBaseManager dbm = new DataBaseManager(this);

        //Delete All data in all tables:
        dbm.TEST_DeleteAllData();

        //Kontrolne wypisanie nazw tabel:
        dbm.TEST_WriteHeaderToLOG("List of tables :");
        dbm.TEST_TableNames();


        //------------------
        //Table: MEDICINES :
        //------------------
        dbm.TEST_WriteHeaderToLOG("Test of table MEDICINES :");

        //Add new Medicines:
        dbm.dbCreateMedicine(new Medicine(("Medicine 1")));
        dbm.TEST_TypeTableMedicine();       //Write to LOG: Contenet of table Medicine
        dbm.dbCreateMedicine(new Medicine(("Medicine 2")));
        dbm.TEST_TypeTableMedicine();       //Write to LOG: Contenet of table Medicine
        dbm.dbCreateMedicine(new Medicine(("Medicine 3")));
        dbm.TEST_TypeTableMedicine();       //Write to LOG: Contenet of table Medicine

        //Delete Medicines:
        dbm.dbDeleteMedicine(3);
        dbm.TEST_TypeTableMedicine();       //Write to LOG: Contenet of table Medicine

        //Update Medicines (_id=2):
        //GET Medicine object to modify. Param: _id of Medicine object:
        Medicine updateMed = dbm.dbGetMedicine(new Long(2));
        //Modyfying Name:
        String oldName = updateMed.getName();
        String newName = oldName +"-Modified !";
        updateMed.setName(newName);
        //Write to database:
        dbm.dbUpdateMedicine(updateMed);

        dbm.TEST_TypeTableMedicine();       //Write to LOG: Content of table Medicine

        //------------------
        //Table: DOSETYPE :
        //------------------
        // DoseType table columns:
        // _id
        // name
        dbm.TEST_WriteHeaderToLOG("Test of table DOSETYPE :");

        //Add new DoseType:
        dbm.dbCreateDoseType(new DoseType(("DoseType1")));
        dbm.TEST_TypeTableDoseType();       //Write to LOG: Contenet of table DoseType
        dbm.dbCreateDoseType(new DoseType(("DoseType2")));
        dbm.TEST_TypeTableDoseType();       //Write to LOG: Contenet of table DoseType
        dbm.dbCreateDoseType(new DoseType(("DoseType3")));
        dbm.TEST_TypeTableDoseType();       //Write to LOG: Contenet of table DoseType

        //Delete DoseType:
        dbm.dbDeleteDoseType(2);
        dbm.TEST_TypeTableDoseType();       //Write to LOG: Contenet of table DoseType

        //Update DoseType (_id=1):
        //GET DoseType object to modify. Param: _id of DoseType object:
        DoseType updateDT = dbm.dbGetDoseType(new Long(1));
        //Modyfying object:
        String oldDT_Name = updateDT.getName();
        String newDT_Name = oldDT_Name +"-Modified !";
        updateDT.setName(newDT_Name);
        //Write to database:
        dbm.dbUpdateDoseType(updateDT);

        dbm.TEST_TypeTableDoseType();       //Write to LOG: Contenet of table DoseType


        //--------------
        //Table: STORE :
        //--------------
        // Store table columns:
        // _id
        // medicine_id
        // quantity
        dbm.TEST_WriteHeaderToLOG("Test of table STORE :");
        //Add new Store (Param: medicine_id, quantity):
        dbm.dbCreateStore(1,10);
        dbm.TEST_TypeTableStore();       //Write to LOG: Contenet of table Store
        dbm.dbCreateStore(3,30);
        dbm.TEST_TypeTableStore();       //Write to LOG: Contenet of table Store
        dbm.dbCreateStore(3,50);
        dbm.TEST_TypeTableStore();       //Write to LOG: Contenet of table Store

        //Delete Store:
        dbm.dbDeleteStore(3);
        dbm.TEST_TypeTableStore();       //Write to LOG: Contenet of table Store

        //Update DoseType (_id=2):
        //GET Store object to modify. Param: _id of Store object:
        Store updateSto = dbm.dbGetStore(2);
        //Get old value and modyfy it:
        Integer oldQuntity = updateSto.getQuantity();
        Integer newQuantity = oldQuntity + 100;
        //Update object:
        updateSto.setQuantity(newQuantity);
        //Write to database:
        dbm.dbUpdateStore(updateSto);

        dbm.TEST_TypeTableStore();       //Write to LOG: Contenet of table Store

        //---------------
        //Table: TAKING :
        //---------------
        // Taking table columns:
        // _id
        // medicine_id
        // dose
        // dosetype_id
        // hour
        // minute
        dbm.TEST_WriteHeaderToLOG("Test of table TAKING :");

        //Add new Taking (Param: medicine_id, dose, dosetype_id, hour, minute):
        dbm.dbCreateTaking(1,1,1,10,0);
        dbm.TEST_TypeTableTaking();       //Write to LOG: Contenet of table Taking
        dbm.dbCreateTaking(2,2,1,15,15);
        dbm.TEST_TypeTableTaking();       //Write to LOG: Contenet of table Taking
        dbm.dbCreateTaking(1,2,1,20,20);
        dbm.TEST_TypeTableTaking();       //Write to LOG: Contenet of table Taking

        //Delete Taking:
        dbm.dbDeleteTaking(3);
        dbm.TEST_TypeTableTaking();       //Write to LOG: Contenet of table Taking

        //Update Taking (_id=2):
        //GET Taking object to modify. Param: _id of Taking object:
        Taking updateTak = dbm.dbGetTaking(2);
        //Modify same values:
        updateTak.setHour(13);
        updateTak.setMinute(13);

        //Write object to database:
        dbm.dbUpdateTaking(updateTak);

        dbm.TEST_TypeTableTaking();       //Write to LOG: Contenet of table Taking

        //---------------
        //Table: REPORT :
        //---------------
        // Report table columns:
        // _id
        // medicineName
        // date
        // plannedtime
        // actualtime

        dbm.TEST_WriteHeaderToLOG("Test of table REPORT :");

        //Add new Report (Param: medicine_name, date, plannedTime, actualTime):
        dbm.dbCreateReport("Medicine name 1", "2018-03-19", "10:00", "10:15");
        dbm.TEST_TypeTableReport();       //Write to LOG: Contenet of table Report
        dbm.dbCreateReport("Medicine name 2", "2018-03-19", "10:00", "10:30");
        dbm.TEST_TypeTableReport();       //Write to LOG: Contenet of table Report
        //New entity with actual time:
        //Date Time tests:
        Date datetime = new Date();
        long time = datetime.getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = df.format(time);
        DateFormat tf = new SimpleDateFormat("HH:mm");
        String timeStr = tf.format(time);
        dbm.dbCreateReport("Medicine name 1", dateStr, "10:00", timeStr);
        dbm.TEST_TypeTableReport();       //Write to LOG: Contenet of table Report

        //Delete Report:
        dbm.dbDeleteReport(3);
        dbm.TEST_TypeTableReport();       //Write to LOG: Contenet of table Report

        //Update Report (_id=2):
        //GET Report object to modify. Param: _id of Taking object:
        Report updateRep = dbm.dbGetReport(2);
        //Modify same values:
        updateRep.setActualTime("15:10");

        //Write object to database:
        dbm.dbUpdateReport(updateRep);

        dbm.TEST_TypeTableReport();       //Write to LOG: Contenet of table Report


        //-----------------
        //Table: MESSAGES :
        //-----------------
        // Messages table columns:
        // _id
        // creatingDateTime
        // text
        // readit

        dbm.TEST_WriteHeaderToLOG("Test of table MESSAGES :");

        //Add new Message (Param: sDateTime, sText):
        String sDateTime = dbm.getDateTime();           //time for creating message (as String)
        dbm.dbCreateMessage(sDateTime, "Message 1");
        dbm.TEST_TypeTableMessages();     //Write to LOG: Contenet of table Message
        dbm.dbCreateMessage(sDateTime, "Message 2");
        dbm.TEST_TypeTableMessages();     //Write to LOG: Contenet of table Message
        dbm.dbCreateMessage("1960-12-25 14:00:00", "Message 3");
        dbm.TEST_TypeTableMessages();     //Write to LOG: Contenet of table Message

//        //New entity with actual time:
//        //Date Time tests:
//        Date datetime = new Date();
//        long time = datetime.getTime();
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        String dateStr = df.format(time);
//        DateFormat tf = new SimpleDateFormat("HH:mm");
//        String timeStr = tf.format(time);
//        dbm.dbCreateReport("Medicine name 1", dateStr, "10:00", timeStr);
//        dbm.TEST_TypeTableReport();       //Write to LOG: Contenet of table Report

        //Delete Message:
        dbm.dbDeleteMessage(3);
        dbm.TEST_TypeTableMessages();     //Write to LOG: Contenet of table Message

        //Update Message (_id=2):
        //GET Message object to modify. Param: _id of Taking object:
        Message updateMes = dbm.dbGetMessage(2);
        //Modify same values:
        updateMes.setText("Message modified!!!");

        //Write object to database:
        dbm.dbUpdateMessage(updateMes);

        dbm.TEST_TypeTableMessages();     //Write to LOG: Contenet of table Message




//        //Wypisz wszystkie Medicines:
//        for(Medicine m: dbm.dbGetAllMedicines() ){
//            Log.d("DB", m.toString());
//        }


        dbm.TEST_WriteTablesContent(dbm);      //Write to LOG: Content of all tables


        //Close the database:
        dbm.close();


    }


}
