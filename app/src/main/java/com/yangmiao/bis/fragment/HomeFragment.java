package com.yangmiao.bis.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yangmiao.bis.R;
import com.yangmiao.bis.factory.HomeListItemFactory;
import com.yangmiao.bis.fragment.viewholder.HomeListItemViewHolder;
import com.yangmiao.bis.fragment.viewholder.IViewHolder;
import com.yangmiao.bis.model.HomeListItemAssetsChangeInfo;
import com.yangmiao.bis.model.HomeListItemInfo;
import com.yangmiao.bis.model.IHomeListItem;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {

    private RecyclerView home_recyclerview;
    private List<HomeListItemInfo> list;
    private MyItemAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        list = new ArrayList<>();

        List<HomeListItemAssetsChangeInfo> assetsList1 = new ArrayList<>();
        assetsList1.add(new HomeListItemAssetsChangeInfo("张三", "入账1万", "2016.7.8"));
        assetsList1.add(new HomeListItemAssetsChangeInfo("李四", "入账1万", "2016.7.8"));
        assetsList1.add(new HomeListItemAssetsChangeInfo("王五", "入账1万", "2016.7.8"));
        list.add(new HomeListItemInfo("入账提醒：", assetsList1, HomeListItemFactory.TYPE_RUZHANG));

        List<HomeListItemAssetsChangeInfo> assetsList2 = new ArrayList<>();
        assetsList2.add(new HomeListItemAssetsChangeInfo("张三", "欠了300万", "2016.7.8"));
        assetsList2.add(new HomeListItemAssetsChangeInfo("李四", "欠了500万", "2016.7.8"));
        assetsList2.add(new HomeListItemAssetsChangeInfo("王五", "欠了1000万", "2016.7.8"));
        list.add(new HomeListItemInfo("可分期提醒：", assetsList2, HomeListItemFactory.TYPE_FENQI));

        List<HomeListItemAssetsChangeInfo> assetsList3 = new ArrayList<>();
        assetsList3.add(new HomeListItemAssetsChangeInfo("张三", "出账1万", "2016.7.8"));
        assetsList3.add(new HomeListItemAssetsChangeInfo("李四", "出账1万", "2016.7.8"));
        assetsList3.add(new HomeListItemAssetsChangeInfo("王五", "出账1万", "2016.7.8"));
        list.add(new HomeListItemInfo("走款提醒：", assetsList3, HomeListItemFactory.TYPE_CHUZHANG));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        home_recyclerview = (RecyclerView) view.findViewById(R.id.home_recyclerview);
        home_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        if (mAdapter == null) {
            mAdapter = new MyItemAdapter(list);
        }
        home_recyclerview.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onSelected() {


    }

    private class MyItemAdapter extends RecyclerView.Adapter {
        private List<HomeListItemInfo> mItemList;

        public MyItemAdapter(List<HomeListItemInfo> list) {
            this.mItemList = list;
        }

        @Override
        public int getItemCount() {
            if (mItemList != null) {
                return mItemList.size();
            }
            return 0;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.list_item_fragment_home, null);
            return new HomeListItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder == null) {
                return;
            }
            IViewHolder iViewHolder = (IViewHolder) holder;
            HomeListItemInfo item = mItemList.get(position);
            if (item == null) {
                return;
            }
            iViewHolder.bindView(item);
        }
    }
}
