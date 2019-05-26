package com.example.a529195.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.a529195.myapplication.fragment.SectionsFragment;
import com.example.a529195.myapplication.fragment.StudentsFragment;
import com.example.a529195.myapplication.utils.SetOnSectionClickListener;
import com.example.a529195.myapplication.utils.Utils;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SetOnSectionClickListener {

    private Context mContext;
    private TextView sectionNameTextView;
    private StudentsFragment studentsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        if (Utils.getSharedPrefs(mContext).getInt("SECTION", 0) == 0) {
            Utils.getSharedPrefs(mContext).edit().putInt("SECTION", new Random().nextInt((10 - 1) + 1) + 1).apply();
        }
        sectionNameTextView = findViewById(R.id.tv_sectionName);
        studentsFragment = (StudentsFragment) getSupportFragmentManager().findFragmentById(R.id.studentFag);

        onSectionClick("1");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_student, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_stud) {
            Intent intent = new Intent(mContext, AddStudentActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.add_sessins) {
            Utils.getSharedPrefs(mContext).edit().putInt("SECTION", new Random().nextInt((10 - 1) + 1) + 1).apply();
            Utils.getDatabase(mContext).studentDao().deleteStudents();
            Intent intent = new Intent(mContext, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSectionClick(String section) {
        studentsFragment.loadStudents(section);
        sectionNameTextView.setText("SECTION " + section);
    }
}
