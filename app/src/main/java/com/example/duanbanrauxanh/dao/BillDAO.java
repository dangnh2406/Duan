package com.example.duanbanrauxanh.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanbanrauxanh.model.Bill;
import com.example.duanbanrauxanh.model.Product;
import com.example.duanbanrauxanh.mySQL.MySQLite;

import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    MySQLite mySQLite;
    SQLiteDatabase sqLiteDatabase;

    public BillDAO(Context context) {
        mySQLite = new MySQLite(context);
        sqLiteDatabase = mySQLite.getWritableDatabase();
    }

    public static final String TABLE_BILL = "bills";
    public static final String COL_ID_BILL = "idBill";
    public static final String COL_DATE_BILL = "dateBill";
    public static final String COL_NAME_CLIENT = "clientName";
    public static final String COL_TOTAL = "total";


    public static final String SQL_BILL = "create table " + TABLE_BILL + " ( " + COL_ID_BILL + " text , " + COL_NAME_CLIENT + " text , " + COL_DATE_BILL + " text , " + COL_TOTAL + " double )";

    public boolean insertBill(Bill bill) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID_BILL, bill.idBill);
        contentValues.put(COL_DATE_BILL, bill.dateAddBill);
        contentValues.put(COL_TOTAL, bill.total);
        contentValues.put(COL_NAME_CLIENT, bill.clientName);

        long kq = sqLiteDatabase.insert(TABLE_BILL, null, contentValues);
        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public long deleteBill(String Id) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_BILL, COL_ID_BILL + " = ?", new String[]{Id});

    }

    public boolean updateBill(Bill bill) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME_CLIENT, bill.clientName);
        contentValues.put(COL_TOTAL, bill.total);
        contentValues.put(COL_DATE_BILL, bill.dateAddBill);
        long udate = sqLiteDatabase.update(TABLE_BILL, contentValues, COL_ID_BILL + " = ?", new String[]{String.valueOf((bill.getIdBill()))});

        if (udate > 0) {
            return true;
        } else {
            return false;
        }

    }


    public List<Bill> getAllBill() {
        List<Bill> list = new ArrayList<>();
        String query = "select * from " + TABLE_BILL;
        Cursor cursor = mySQLite.getReadableDatabase().rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Bill bill = new Bill();
                bill.idBill = cursor.getString(cursor.getColumnIndex(COL_ID_BILL));
                bill.dateAddBill = cursor.getString(cursor.getColumnIndex(COL_DATE_BILL));
                bill.clientName = cursor.getString((cursor.getColumnIndex(COL_NAME_CLIENT)));
                bill.total = ((cursor.getDouble(cursor.getColumnIndex(COL_TOTAL))));
                list.add(bill);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }


}
