package com.example.android.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.android.inventoryapp.data.InventoryContract.ItemEntry;

public class InventoryDbHelper extends SQLiteOpenHelper  {

    public static final String LOG_TAG = InventoryDbHelper.class.getSimpleName();

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "stock.db";

    public InventoryDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_INVENTORIES_TABLE =  "CREATE TABLE " + ItemEntry.TABLE_NAME + " ("
                + ItemEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ItemEntry.COLUMN_ITEM_NAME + " TEXT NOT NULL, "
                + ItemEntry.COLUMN_ITEM_QUANTITY + " TEXT, "
                + ItemEntry.COLUMN_INVENTORY_GENDER + " INTEGER NOT NULL, "
                + ItemEntry.COLUMN_ITEM_PRICE + " INTEGER NOT NULL DEFAULT 0);";
        // Execute the SQL statement
        db.execSQL(SQL_CREATE_INVENTORIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }
}
