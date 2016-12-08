package com.yangmiao.bis.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.test.greendao.Account;
import com.yangmiao.bis.R;
import com.yangmiao.bis.db.AccountProvider;
import com.yangmiao.bis.util.Validator;

import java.util.List;

public class SearchFragment extends BaseFragment implements View.OnClickListener {

    private List<Account> list;

    private RecyclerView search_recyclerview;
    private TextView search_btn;
    private EditText search_et;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, null);
        search_btn = (TextView) view.findViewById(R.id.search_btn);
        search_et = (EditText) view.findViewById(R.id.search_et);
        search_recyclerview = (RecyclerView) view.findViewById(R.id.search_recyclerview);
        search_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        search_btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_btn:
                if (search_et != null) {
                    String text = search_et.getText().toString();
                    list = null;
                    if (Validator.isMobile(text)) {
                        list = AccountProvider.queryAllByCulumnsTel(text);
                    } else if (Validator.isIDCard(text)) {
                        list = AccountProvider.queryAllByCulumnsCardId(text);
                    }
                    if (list != null) {
                        search_recyclerview.setAdapter(mAdapter);
                    } else {
                        Toast.makeText(getContext(), "请输入正确的号码！", Toast.LENGTH_LONG).show();
                    }
                }

                break;
        }
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
            View view = View.inflate(parent.getContext(), R.layout.list_item_fragment_search, null);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            Account accountInfo = list.get(position);
            myViewHolder.search_item_name.setText(accountInfo.getName());
            myViewHolder.search_item_sex.setText(accountInfo.getSex());
            myViewHolder.search_item_tel.setText(accountInfo.getTel());
            myViewHolder.search_item_card_id.setText(accountInfo.getCard_id());
            myViewHolder.search_item_assets_type.setText(AccountProvider.getAssetsTypeText(accountInfo.getAssets_type(), getContext()));
            myViewHolder.search_item_assets_consumer_grade.setText(Html.fromHtml(AccountProvider.getConsumerGradeText(accountInfo.getConsumer_grade(), getContext())));
            myViewHolder.search_item_integral.setText("" + accountInfo.getIntegral());
            myViewHolder.search_item_attribution.setText(accountInfo.getAttribution());
            myViewHolder.search_item_address.setText(accountInfo.getAddress());
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView search_item_name;
            TextView search_item_sex;
            TextView search_item_tel;
            TextView search_item_card_id;
            TextView search_item_assets_type;
            TextView search_item_assets_consumer_grade;
            TextView search_item_integral;
            TextView search_item_attribution;
            TextView search_item_address;

            public MyViewHolder(View itemView) {
                super(itemView);
                search_item_name = (TextView) itemView.findViewById(R.id.search_item_name);
                search_item_sex = (TextView) itemView.findViewById(R.id.search_item_sex);
                search_item_tel = (TextView) itemView.findViewById(R.id.search_item_tel);
                search_item_card_id = (TextView) itemView.findViewById(R.id.search_item_card_id);
                search_item_assets_type = (TextView) itemView.findViewById(R.id.search_item_assets_type);
                search_item_assets_consumer_grade = (TextView) itemView.findViewById(R.id.search_item_assets_consumer_grade);
                search_item_integral = (TextView) itemView.findViewById(R.id.search_item_integral);
                search_item_attribution = (TextView) itemView.findViewById(R.id.search_item_attribution);
                search_item_address = (TextView) itemView.findViewById(R.id.search_item_address);

            }
        }
    };

}
