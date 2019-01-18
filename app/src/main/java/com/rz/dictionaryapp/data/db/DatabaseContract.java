package com.rz.dictionaryapp.data.db;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static String ENGLISH_TABLE = "english_table";
    public static String INDONESIAN_TABLE = "indonesian_table";

    static final class TableColums implements BaseColumns{
        static String WORD = "word";
        static String TRANSLATION = "translation";
    }
}
