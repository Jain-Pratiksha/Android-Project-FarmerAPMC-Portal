package com.example.farmerapmcportal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String db_name = "FarmerAPMCPortal.db";

    public DBHelper(Context context) {

        super(context, "FarmerAPMCPortal.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        MyDB.execSQL("create Table users(mob_no TEXT primary key, pass TEXT)");
        MyDB.execSQL("create Table products(id INTEGER PRIMARY KEY AUTOINCREMENT, farmer_name TEXT, " +
                "address TEXT, product_type TEXT, product_name TEXT, price TEXT, availability TEXT,product_image BLOB not null)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {

        MyDB.execSQL("drop Table if exists users");
//        MyDB.execSQL("drop Table if exists products");
    }

    public Boolean insertData(String mob_no, String pass){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mob_no", mob_no);
        contentValues.put("pass", pass);
        long result = MyDB.insert("users", null, contentValues);
        if (result==-1) return false;
        else
            return true;
    }

    public Boolean insertProductData(String farmer_name, String address, String product_type, String product_name,
                                     String price, byte[] product_image,String availability){
        SQLiteDatabase MyDB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("farmer_name", farmer_name);
        contentValues.put("address", address);
        contentValues.put("product_type", product_type);
        contentValues.put("product_name", product_name);
        contentValues.put("price", price);

        contentValues.put("product_image", product_image);
        contentValues.put("availability",availability);
        long result = MyDB.insert("products", null, contentValues);
        if (result==-1) return false;
        else
            return true;

    }

    public Boolean checkmob_no(String mob_no){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where mob_no = ?", new String[] {mob_no});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkmob_nopassword (String mob_no, String pass){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where mob_no = ? and pass = ?", new String[] {mob_no,pass});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }



    public Cursor fetch() {
        //Prof Indu Anoop :Fetch needs to have db in readable
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from products", null);
        return cursor;
    }

}
