package com.example.a529195.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a529195.myapplication.room.Student;
import com.example.a529195.myapplication.utils.Utils;

public class AddStudentActivity extends AppCompatActivity {

    private EditText studentNameEditText;
    private EditText marksEditText;
    private Context mContext;
    private int numSections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        mContext = this;

        marksEditText = findViewById(R.id.et_total_marks);
        studentNameEditText = findViewById(R.id.et_student_name);
        Button submitButton = findViewById(R.id.btn_submit);
        numSections = Utils.getSharedPrefs(mContext).getInt("SECTION", 4);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentName = studentNameEditText.getText().toString();
                String marks = marksEditText.getText().toString();

                if (TextUtils.isEmpty(studentName)) {
                    Toast.makeText(mContext, "Enter Student Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(marks)) {
                    Toast.makeText(mContext, "Enter Marks", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!TextUtils.isDigitsOnly(marks)) {
                    Toast.makeText(mContext, "Enter Valid Marks", Toast.LENGTH_SHORT).show();
                    return;
                }

                Student student = new Student();
                student.sectionName = getSectionName(Integer.parseInt(marks));
                student.studentName = studentName;
                student.totalMarks = Integer.parseInt(marks);

                Utils.getDatabase(getApplicationContext()).studentDao().insertStudent(student);

                Toast.makeText(mContext, "Student Added Successfully" + student.sectionName, Toast.LENGTH_SHORT).show();
                studentNameEditText.setText("");
                marksEditText.setText("");
            }
        });

    }

    public int getSectionName(int marks) {

        int minSec = 1;
        int minSecCount = -1;
        if (Utils.getDatabase(mContext).studentDao().getAllStudents().size() <= 0) {
            return 1;
        }
        minSecCount = Utils.getDatabase(mContext).studentDao().getStudentCount("" + 1);
        for (int i = 1; i < numSections; i++) {
            int studentCount = Utils.getDatabase(mContext).studentDao().getStudentCount("" + (i + 1));
            if (studentCount <= 0) {
                return (i + 1);
            }
            if (studentCount < minSecCount) {
                minSec = i + 1;
                minSecCount = studentCount;
            }
            int avg = Utils.getDatabase(mContext).studentDao().getMarksAvg("Section " + (i + 1));
        }

        Log.e("TAGTAGTAG", "getSectionName:::::: " + minSec);
        return minSec;
    }
}
