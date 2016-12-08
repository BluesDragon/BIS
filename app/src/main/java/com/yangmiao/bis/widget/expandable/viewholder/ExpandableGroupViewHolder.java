package com.yangmiao.bis.widget.expandable.viewholder;

import android.content.Context;
import android.view.View;

public abstract class ExpandableGroupViewHolder extends ExpandableBaseViewHolder {

    public ExpandableGroupViewHolder(Context context, View itemView) {
        super(context, itemView);
    }

    public abstract void build(Object o, int groupPosition);
}
