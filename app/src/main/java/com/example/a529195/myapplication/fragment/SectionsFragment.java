package com.example.a529195.myapplication.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a529195.myapplication.MainActivity;
import com.example.a529195.myapplication.R;
import com.example.a529195.myapplication.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class SectionsFragment extends Fragment {


    private Context mContext;
    private RecyclerView recyclerView;
    private int sections;

    public SectionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recycler, container, false);

        mContext = getActivity();

        recyclerView = v.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));

        sections = Utils.getSharedPrefs(mContext).getInt("SECTION", 4);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.setAdapter(new SectionAdapter(sections));
    }

    public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.SectionViewHolder> {

        private int sections;

        public SectionAdapter(int sections) {
            this.sections = sections;
        }

        @NonNull
        @Override
        public SectionAdapter.SectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(getContext()).inflate(R.layout.item_section, viewGroup, false);
            return new SectionViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull SectionViewHolder viewHolder, final int position) {
            viewHolder.name.setText("Section " + (position + 1));
            String toatl = "Total " + Utils.getDatabase(mContext).studentDao().getStudentCount("" + (position + 1));
            viewHolder.total.setText(toatl);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) mContext).onSectionClick("" + (position + 1));
                }
            });
        }

        @Override
        public int getItemCount() {
            return sections;
        }

        public class SectionViewHolder extends RecyclerView.ViewHolder {
            TextView name;
            TextView total;

            public SectionViewHolder(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.tv_section);
                total = (TextView) itemView.findViewById(R.id.tv_total_students);
            }
        }

    }

}
