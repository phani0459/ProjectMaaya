package com.example.a529195.myapplication.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a529195.myapplication.R;
import com.example.a529195.myapplication.room.Student;
import com.example.a529195.myapplication.utils.Utils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentsFragment extends Fragment {

    private Context mContext;
    private RecyclerView recyclerView;

    public StudentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recycler, container, false);

        mContext = getActivity();

        recyclerView = v.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        return v;
    }

    public void loadStudents(String section) {
        ArrayList<Student> students = (ArrayList<Student>) Utils.getDatabase(getActivity().getApplicationContext()).studentDao().getSectionStudents(section);
        if (students != null && students.size() > 0) {
            recyclerView.setAdapter(new StudentAdapter(students));
        } else {
            recyclerView.setAdapter(new StudentAdapter(students));
        }
    }

    public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

        private ArrayList<Student> students;

        public StudentAdapter(ArrayList<Student> students) {
            this.students = students;
        }

        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(getContext()).inflate(R.layout.item_student, viewGroup, false);
            return new StudentViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder viewHolder, int position) {
            viewHolder.name.setText(students.get(position).studentName);
        }

        @Override
        public int getItemCount() {
            return students.size();
        }

        public class StudentViewHolder extends RecyclerView.ViewHolder {
            TextView name;

            public StudentViewHolder(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.tv_student_name);
            }
        }

    }

}
