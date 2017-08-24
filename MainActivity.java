package ru.testscreen.booktest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import android.content.pm.ActivityInfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private MaximDB maximDB = new MaximDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        LogClass obj = new LogClass();
        obj.write("Сработало событие onCreate");
    }

    // метод для добавления записи в БД
    public void saveMyData(View view){
        LogClass obj = new LogClass();
        obj.write("Была нажата кнопка");

        String s1 = ((EditText)findViewById(R.id.edittext_1)).getText().toString();
        String s2 = ((EditText)findViewById(R.id.edittext_2)).getText().toString();
        obj.write("Получены строки:  " + s1 + "  ______  " + s2);

        SQLiteDatabase db = maximDB.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("T1",s1);
        cv.put("T2",s2);
        long rowNumber = db.insert("T", null, cv);
        Log.i("Сообщение","Row number: " + rowNumber);
        db.close();
    }

    // метод для вывода всех записей на экран в порядке возрастания id
    public void showAll(View view){
        SQLiteDatabase db = maximDB.getReadableDatabase();
        Cursor c = db.query("T", null, null, null, null, null, "_id ASC");
        int rowsCount = 0;
        if(c.moveToFirst()){
           do {
               rowsCount++;
               int id = c.getInt(0);
               String t1 = c.getString(1);
               String t2 = c.getString(2);
               Log.i("Сообщение", "Row: " + id + " " + t1 + " " + t2);
           } while(c.moveToNext());
        }
        Log.i("Сообщение","Count of rows: " + rowsCount);
        c.close();
        db.close();
    }

    // метод для обновления всех записей, у которых T1 = nina
    public void changeRecord(View view){
        SQLiteDatabase db = maximDB.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("T1","lalala");
        cv.put("T2","gogogo");
        int number = db.update("T", cv, "T1 = ?", new String [] {"nina"});
        db.close();
    }

    // метод для удаления записей, у которых T1 = lalala и T2 = gogogo
    public void deleteRecord(View view){
        SQLiteDatabase db = maximDB.getWritableDatabase();
        int number = db.delete("T", "T1 = ? AND T2 = ?", new String [] {"lalala","gogogo"});
        db.close();
    }
}
