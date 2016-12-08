package com.yangmiao.bis.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.greendao.Account;
import com.yangmiao.bis.R;
import com.yangmiao.bis.db.AccountProvider;

import java.util.List;

public class IntegralFragment extends BaseFragment {

    private List<Account> list;
    private TextView fragment_integration_title;
    private RecyclerView fragment_integration_recyclerview;
    private MyAdapter myAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = AccountProvider.queryAllByIntegral();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_integration, null);
        fragment_integration_title = (TextView) view.findViewById(R.id.fragment_integration_title);
        fragment_integration_recyclerview = (RecyclerView) view.findViewById(R.id.fragment_integration_recyclerview);
        fragment_integration_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        if (myAdapter == null) {
            myAdapter = new MyAdapter();
        }
        fragment_integration_recyclerview.setAdapter(myAdapter);
        return view;
    }

    @Override
    public void onSelected() {

    }

    private class MyAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.list_item_fragment_integration_list_item, null);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;

            Account accountInfo = list.get(position);
            myViewHolder.integral_item_id.setText("" + (position + 1));
            myViewHolder.integral_item_name.setText("" + accountInfo.getName());
            myViewHolder.integral_item_integral.setText("" + accountInfo.getIntegral());

            int stringRes = R.string.account_level_putong;
            switch (accountInfo.getConsumer_grade()) {
                case AccountProvider.ConsumerGrade_NORMAL:
                    stringRes = R.string.account_level_putong;
                    break;
                case AccountProvider.ConsumerGrade_VIP:
                    stringRes = R.string.account_level_vip;
                    break;
                case AccountProvider.ConsumerGrade_CAIFU:
                    stringRes = R.string.account_level_caifu;
                    break;
                case AccountProvider.ConsumerGrade_SIHANG:
                    stringRes = R.string.account_level_sihang;
                    break;
            }
            myViewHolder.integral_item_level.setText(Html.fromHtml(getString(stringRes)));
        }

        @Override
        public int getItemCount() {
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        private class MyViewHolder extends RecyclerView.ViewHolder {
            TextView integral_item_id;
            TextView integral_item_name;
            TextView integral_item_level;
            TextView integral_item_integral;

            public MyViewHolder(View itemView) {
                super(itemView);
                integral_item_id = (TextView) itemView.findViewById(R.id.integral_item_id);
                integral_item_name = (TextView) itemView.findViewById(R.id.integral_item_name);
                integral_item_level = (TextView) itemView.findViewById(R.id.integral_item_level);
                integral_item_integral = (TextView) itemView.findViewById(R.id.integral_item_integral);
            }
        }
    }
}
