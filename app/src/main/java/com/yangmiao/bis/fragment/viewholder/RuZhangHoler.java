package com.yangmiao.bis.fragment.viewholder;

import android.view.View;
import android.widget.TextView;

import com.yangmiao.bis.R;
import com.yangmiao.bis.model.HomeListItemAssetsChangeInfo;
import com.yangmiao.bis.model.IHomeListItem;

public class RuZhangHoler extends BaseViewHolder {

    private TextView list_item_fragment_home_assets_item_name;
    private TextView list_item_fragment_home_assets_item_assets_change;
    private TextView list_item_fragment_home_assets_item_change_time;

    public RuZhangHoler(View itemView) {
        super(itemView);
        list_item_fragment_home_assets_item_name = (TextView) itemView.findViewById(R.id.list_item_fragment_home_assets_item_name);
        list_item_fragment_home_assets_item_assets_change = (TextView) itemView.findViewById(R.id.list_item_fragment_home_assets_item_assets_change);
        list_item_fragment_home_assets_item_change_time = (TextView) itemView.findViewById(R.id.list_item_fragment_home_assets_item_change_time);
    }

    @Override
    public void bindView(IHomeListItem item) {
        HomeListItemAssetsChangeInfo info = (HomeListItemAssetsChangeInfo) item;
        list_item_fragment_home_assets_item_name.setText(info.name);
        list_item_fragment_home_assets_item_assets_change.setText(info.assetsChangeDetail);
        list_item_fragment_home_assets_item_change_time.setText(info.changeTime);
    }
}
