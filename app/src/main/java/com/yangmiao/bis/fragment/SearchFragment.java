package com.yangmiao.bis.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yangmiao.bis.R;
import com.yangmiao.bis.db.account.AccountContentProvider;
import com.yangmiao.bis.model.AccountInfo;

import java.util.List;

public class SearchFragment extends BaseFragment {

    private RecyclerView search_recyclerview;

    private List<AccountInfo> list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (list == null) {
            list = AccountContentProvider.queryAll(getActivity());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, null);
        search_recyclerview = (RecyclerView) view.findViewById(R.id.search_recyclerview);
        search_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        search_recyclerview.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onFragmentSelected() {

    }

    private RecyclerView.Adapter mAdapter = new RecyclerView.Adapter() {

        @Override
        public int getItemCount() {
            int count = 0;
            if (list != null) {
                count = list.size();
            }
            return count;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.layout_search_list_item, null);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            AccountInfo accountInfo = list.get(position);
            myViewHolder.search_item_name.setText(accountInfo.name);

        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView search_item_name;

            public MyViewHolder(View itemView) {
                super(itemView);
                search_item_name = (TextView) itemView.findViewById(R.id.search_item_name);

            }
        }
    };

}
