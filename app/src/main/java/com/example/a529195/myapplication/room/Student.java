package com.example.a529195.myapplication.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Student {
    @PrimaryKey(autoGenerate = true)
    public int sId;

    @ColumnInfo(name = "student_name")
    public String studentName;

    @ColumnInfo(name = "section")
    public int sectionName;

    @ColumnInfo(name = "marks")
    public int totalMarks;
}
