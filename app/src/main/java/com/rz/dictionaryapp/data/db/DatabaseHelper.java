package com.rz.dictionaryapp.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.rz.dictionaryapp.data.db.DatabaseContract.ENGLISH_TABLE;
import static com.rz.dictionaryapp.data.db.DatabaseContract.INDONESIAN_TABLE;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "db_dict";
    private static final int DB_VERSION = 1;
    private static String table(String type){
        return String.format("CREATE TABLE %s" +
                                " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                                " %s TEXT NOT NULL, " +
                                " %s TEXT NOT NULL)",
                        type,
                        DatabaseContract.TableColums._ID,
                        DatabaseContract.TableColums.WORD,
                        DatabaseContract.TableColums.TRANSLATION
                );
    }

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(table(ENGLISH_TABLE));
        db.execSQL(table(INDONESIAN_TABLE));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.ENGLISH_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.INDONESIAN_TABLE);
        onCreate(db);
    }
}
