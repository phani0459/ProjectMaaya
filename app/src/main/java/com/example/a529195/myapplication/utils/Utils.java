package com.example.a529195.myapplication.utils;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.a529195.myapplication.room.StudentDatabase;

public class Utils {

    private static StudentDatabase studentDatabase;

    public static StudentDatabase getDatabase(Context context) {
        if (studentDatabase == null) {
            studentDatabase = Room.<StudentDatabase>databaseBuilder(context,
                    StudentDatabase.class, "test-database").allowMainThreadQueries().build();

        }
        return studentDatabase;
    }

    public static SharedPreferences getSharedPrefs(Context mContext) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("STUD_PREF", Context.MODE_PRIVATE);
        return sharedPreferences;
    }

}
