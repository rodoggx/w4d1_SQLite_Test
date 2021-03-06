package com.example.sqlitetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

//step 2 create new  activity
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTAG_";
    private EditText mName;
    private EditText mAge;
    private String name;
    private String age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void insertMagic(View view) {
        StudentsHelper studentsHelper = new StudentsHelper(this);
        SQLiteDatabase sqLiteDatabase = studentsHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        mName =(EditText)findViewById(R.id.eName);
        mAge =(EditText)findViewById(R.id.eAge);
        name = mName.getText().toString();
        age = mAge.getText().toString();

        values.put(StudentsHelper.COLUMN_NAME, name);
        values.put(StudentsHelper.COLUMN_AGE, age);

        sqLiteDatabase.insert(StudentsHelper.TABLE_NAME, null, values);
        sqLiteDatabase.close();
    }

    public void loadMagic(View view) {
        StudentsHelper studentsHelper = new StudentsHelper(this);
        SQLiteDatabase sqLiteDatabase = studentsHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + StudentsHelper.TABLE_NAME, null);

        cursor.moveToFirst();

        do {
            int id = cursor.getInt(cursor.getColumnIndex(StudentsHelper.COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(StudentsHelper.COLUMN_NAME));
            String age = cursor.getString(cursor.getColumnIndex(StudentsHelper.COLUMN_AGE));

            Log.d(TAG, "LoadMagic: " + id + " " + name + " " + age);

        } while (cursor.moveToNext());
        cursor.close();
    }
}
