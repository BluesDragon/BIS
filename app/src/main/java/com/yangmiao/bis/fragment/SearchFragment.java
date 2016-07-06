package com.yangmiao.bis.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yangmiao.bis.R;
import com.yangmiao.bis.db.account.AccountContentProvider;
import com.yangmiao.bis.model.AccountInfo;
import com.yangmiao.bis.util.LogUtil;

import java.util.List;

public class SearchFragment extends Fragment{

    private RecyclerView search_recyclerview;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<AccountInfo> list = AccountContentProvider.queryAll(getActivity());
        log(list);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, null);
        search_recyclerview = (RecyclerView) view.findViewById(R.id.search_recyclerview);
        return view;
    }

    private void log(Object log){
        LogUtil.d("SearchFragment-->" + log);
    }

}
