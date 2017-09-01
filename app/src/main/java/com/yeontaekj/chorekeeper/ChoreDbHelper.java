package com.yeontaekj.chorekeeper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yeontaekj on 8/31/2017.
 */

public class ChoreDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "chore.db";

    public ChoreDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static final String CREATE_ENTRIES = "CREATE TABLE " + ChoreContract.TABLE_NAME + " ("
            + ChoreContract.ChoreEntry._ID + " INTEGER PRIMARY KEY,"
            + ChoreContract.ChoreEntry.COLUMN_CHORE_NAME + " TEXT,"
            + ChoreContract.ChoreEntry.COLUMN_CHORE_DESCRIPTION + " TEXT,"
            + ChoreContract.ChoreEntry.COLUMN_CHORE_KEY_NAME + " TEXT" + ")";
}
