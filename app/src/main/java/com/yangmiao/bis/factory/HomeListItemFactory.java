package com.yangmiao.bis.factory;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.yangmiao.bis.R;
import com.yangmiao.bis.fragment.viewholder.ChuZhangHoler;
import com.yangmiao.bis.fragment.viewholder.FenQiHoler;
import com.yangmiao.bis.fragment.viewholder.RuZhangHoler;

public class HomeListItemFactory {

    public static final int TYPE_RUZHANG = 1;
    public static final int TYPE_FENQI = 2;
    public static final int TYPE_CHUZHANG = 3;

    public static View getItemView(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case TYPE_RUZHANG:
                view = View.inflate(parent.getContext(), R.layout.list_item_fragment_home_assets_item_1, null);
                break;
            case TYPE_FENQI:
                view = View.inflate(parent.getContext(), R.layout.list_item_fragment_home_assets_item_2, null);
                break;
            case TYPE_CHUZHANG:
                view = View.inflate(parent.getContext(), R.layout.list_item_fragment_home_assets_item_1, null);
                break;
        }
        return view;
    }

    public static RecyclerView.ViewHolder getViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View view = getItemView(parent, viewType);
        switch (viewType) {
            case TYPE_RUZHANG:
                holder = new RuZhangHoler(view);
                break;
            case TYPE_FENQI:
                holder = new FenQiHoler(view);
                break;
            case TYPE_CHUZHANG:
                holder = new ChuZhangHoler(view);
                break;
        }
        return holder;
    }


}
