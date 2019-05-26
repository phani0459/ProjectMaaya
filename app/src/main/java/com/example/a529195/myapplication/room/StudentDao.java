package com.example.a529195.myapplication.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("Select * from Student")
    List<Student> getAllStudents();

    @Query("Select * from Student WHERE section = :sectionName")
    List<Student> getSectionStudents(String sectionName);

    @Query("SELECT * FROM Student WHERE section = :section")
    List<Student> getClassStudents(String section);

    @Query("SELECT COUNT(sId) FROM Student WHERE section = :section")
    int getStudentCount(String section);

    @Query("SELECT AVG(marks) FROM Student WHERE section = :section")
    int getMarksAvg(String section);

    @Insert
    void insertStudent(Student student);

    @Query("DELETE FROM Student")
    void deleteStudents();
}
