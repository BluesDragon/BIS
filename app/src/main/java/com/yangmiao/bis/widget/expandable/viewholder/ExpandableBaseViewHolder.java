package com.yangmiao.bis.widget.expandable.viewholder;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;

public class ExpandableBaseViewHolder {

    protected Context mContext;
    public View itemView;

    public ExpandableBaseViewHolder(Context context, View itemView) {
        this.mContext = context;
        this.itemView = itemView;
    }

    protected String getString(int resId) {
        if (mContext != null) {
            Resources resources = mContext.getResources();
            if (resources != null) {
                return resources.getString(resId);
            }
        }
        return null;
    }

}
