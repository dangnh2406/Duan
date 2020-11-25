package com.example.duanbanrauxanh.mySQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.duanbanrauxanh.dao.AccountDAO;
import com.example.duanbanrauxanh.dao.BillDAO;
import com.example.duanbanrauxanh.dao.BillDetaisDAO;
import com.example.duanbanrauxanh.dao.ProductDAO;

public class MySQLite extends SQLiteOpenHelper {

    public static final String DATA_NAME = "duan.db";
    public static final int VERSION = 1;


    public MySQLite(@Nullable Context context)
    {
        super(context, DATA_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ProductDAO.SQL_PRODUCT);
        db.execSQL(BillDAO.SQL_BILL);
        db.execSQL(BillDetaisDAO.SQL_BILL_DETAI);
        db.execSQL(AccountDAO.SQL_ACCOUNT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
