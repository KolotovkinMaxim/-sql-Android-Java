package ru.testscreen.booktest;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// создание класса для работы с БД
// в классе есть 2 поля для хранения имени БД и номера её версии
// в методе createTablesOfDB создаётся таблица, хранящая в себе id записи
// и два поля типа String
// поле _id INTEGER PRIMARY KEY AUTOINCREMENT должно быть первым у каждой таблицы

public class MaximDB extends SQLiteOpenHelper {
    private static final String DBName = "my_database_1";
    private static final int DBVersion = 1;

    MaximDB(Context context){
        super(context, DBName, null,  DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        createTablesOfDB(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int a, int b){
        createTablesOfDB(db);
    }

    private void createTablesOfDB(SQLiteDatabase db){
        String q = "CREATE TABLE T (_id INTEGER PRIMARY KEY AUTOINCREMENT, T1 TEXT, T2 TEXT);";
        db.execSQL(q);
    }
};
