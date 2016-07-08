package com.yangmiao.bis.fragment.viewholder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yangmiao.bis.R;
import com.yangmiao.bis.factory.HomeListItemFactory;
import com.yangmiao.bis.model.HomeListItemInfo;
import com.yangmiao.bis.model.IHomeListItem;

public class HomeListItemViewHolder extends BaseViewHolder {

    private TextView list_item_fragment_home_title;
    private RecyclerView list_item_fragment_home_recyclerview;
    private HomeListItemInfo info;
    private MyAdapter myAdapter;

    public HomeListItemViewHolder(View itemView) {
        super(itemView);
        list_item_fragment_home_title = (TextView) itemView.findViewById(R.id.list_item_fragment_home_title);
        list_item_fragment_home_recyclerview = (RecyclerView) itemView.findViewById(R.id.list_item_fragment_home_recyclerview);
        list_item_fragment_home_recyclerview.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
    }

    @Override
    public void bindView(IHomeListItem item) {
        info = (HomeListItemInfo) item;
        list_item_fragment_home_title.setText(info.title);
        if (myAdapter == null) {
            myAdapter = new MyAdapter();
        }
        list_item_fragment_home_recyclerview.setAdapter(myAdapter);
    }

    private class MyAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return HomeListItemFactory.getViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder == null) {
                return;
            }
            IViewHolder iViewHolder = (IViewHolder) holder;
            IHomeListItem item = info.list.get(position);
            if (item == null) {
                return;
            }
            iViewHolder.bindView(item);
        }

        @Override
        public int getItemCount() {
            return info.list.size();
        }

        @Override
        public int getItemViewType(int position) {
            return info.type;
        }
    }
}
