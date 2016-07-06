package com.yangmiao.bis.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yangmiao.bis.R;
import com.yangmiao.bis.model.ProduceInfo;

import java.util.List;

public class HomeFragment extends BaseFragment {

    private RecyclerView home_recyclerview;
    private List<ProduceInfo> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        home_recyclerview = (RecyclerView) view.findViewById(R.id.home_recyclerview);
        home_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        home_recyclerview.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onFragmentSelected() {


    }

    private RecyclerView.Adapter mAdapter = new RecyclerView.Adapter() {

        @Override
        public int getItemCount() {
            if (list != null) {
                return list.size();
            }
            return 10;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.produce_content_item, null);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;

        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            public MyViewHolder(View itemView) {
                super(itemView);
            }
        }


    };
}
