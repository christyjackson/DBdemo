package com.example.db_demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String STUDENT_NAME = "STUDENT_NAME";
    public static final String STUDENT_REGNO = "STUDENT_REGNO";
    public static final String ID = "ID";
    public static final String STUDENT_TABLE = "STUDENT_TABLE";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE " + STUDENT_TABLE + " (" + STUDENT_NAME + " TEXT, " + STUDENT_REGNO + " INT, " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT)";
        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(StudentModel studentModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(STUDENT_NAME, studentModel.getName());
        cv.put(STUDENT_REGNO,studentModel.getRegno());
        long insert = db.insert(STUDENT_TABLE, null, cv);
        if (insert == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean deleteOne(StudentModel studentModel){
        //find studentModel in the database and. If found delete it and return true else return false
        SQLiteDatabase db =  this.getWritableDatabase();
        String queryString = "DELETE FROM " + STUDENT_TABLE + " WHERE " + ID + " = " + studentModel.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }

    }

    public List<StudentModel> getveryone(){
        List<StudentModel> returnList =  new ArrayList<>(); // Empty List
        //get data from the database
        String queryString = "SELECT * FROM " + STUDENT_TABLE;
        //creating a reference to the database
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()){
            // loop through the cursor (result set) and create new student objects, and put them into the return List
            do {
                int studentID = cursor.getInt(2 );
                String studentname = cursor.getString(0);
                int studentRegno = cursor.getInt(1);
                StudentModel newStudent = new StudentModel(studentname,studentRegno,studentID);
                returnList.add(newStudent);
            }while (cursor.moveToNext());
        }
        else
        {

        }
        cursor.close();
        db.close();
        return returnList;

    }

}
