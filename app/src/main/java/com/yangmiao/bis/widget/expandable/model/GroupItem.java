package com.yangmiao.bis.widget.expandable.model;

import java.util.List;

public interface GroupItem<C extends ChildItem> extends IExpandableItem {
	
	boolean isExpand();

	void setIsExpand(boolean isExpand);

	List<C> getChildrenList();

	int getGroupType();

}
