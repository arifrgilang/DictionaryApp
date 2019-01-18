package com.rz.dictionaryapp.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.rz.dictionaryapp.data.model.DictModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.rz.dictionaryapp.data.db.DatabaseContract.TableColums.TRANSLATION;
import static com.rz.dictionaryapp.data.db.DatabaseContract.TableColums.WORD;

public class GetterHelper {
    private Context context;
    private String table;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public GetterHelper(Context context, String table) {
        this.context = context;
        this.table = table;
    }

    public GetterHelper open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public ArrayList<DictModel> getAllData() {
        Cursor cursor = db.query(this.table,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);

        cursor.moveToFirst();

        ArrayList<DictModel> arrayList = new ArrayList<>();

        DictModel model;

        if (cursor.getCount() > 0) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
                String word = cursor.getString(cursor.getColumnIndexOrThrow(WORD));
                String translation = cursor.getString(cursor.getColumnIndexOrThrow(TRANSLATION));

                model = new DictModel(id, word, translation);
                arrayList.add(model);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<DictModel> getDataByName(String query) {
        Cursor cursor = db.query(this.table,
                null,
                WORD + " LIKE ?",
                new String[]{query + "%"},
                null,
                null,
                _ID + " ASC",
                null);

        cursor.moveToFirst();
        ArrayList<DictModel> arrayList = new ArrayList<>();
        DictModel model;
        if (cursor.getCount() > 0) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
                String word = cursor.getString(cursor.getColumnIndexOrThrow(WORD));
                String translation = cursor.getString(cursor.getColumnIndexOrThrow(TRANSLATION));

                model = new DictModel(id, word, translation);
                arrayList.add(model);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrayList;
    }

    public long insert(DictModel model) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(WORD, model.getDict());
        initialValues.put(TRANSLATION, model.getTranslation());
        return db.insert(this.table, null, initialValues);
    }

    public void beginTransaction() {
        db.beginTransaction();
    }

    public void setTransactionSuccess() {
        db.setTransactionSuccessful();
    }

    public void endTransaction() {
        db.endTransaction();
    }

    public void insertTransaction(DictModel model) {
        String sql = String.format("INSERT INTO %s (%s, %s) VALUES(?, ?);",
                this.table,
                WORD,
                TRANSLATION);

        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.bindString(1, model.getDict());
        stmt.bindString(2, model.getTranslation());
        stmt.execute();
        stmt.clearBindings();
    }

    public int update(DictModel model) {
        ContentValues args = new ContentValues();
        args.put(WORD, model.getDict());
        args.put(TRANSLATION, model.getTranslation());
        return db.update(this.table,
                args,
                _ID + " = '" + model.getId() + "'",
                null);
    }

    public int delete(int id) {
        return db.delete(this.table,
                _ID + " = '" + id + "'",
                null);
    }
}
