package com.yangmiao.bis.widget.expandable.viewholder;

import android.content.Context;
import android.view.View;

public abstract class ExpandableChildViewHolder extends ExpandableBaseViewHolder {

    public ExpandableChildViewHolder(Context context, View itemView) {
        super(context, itemView);
    }

    public abstract void build(Object o, int groupPosition, int childPosition);
}
