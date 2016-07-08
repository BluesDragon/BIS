package com.yangmiao.bis.model;

import java.util.List;

public class HomeListItemInfo implements IHomeListItem{

    public int type;
    public String title;
    public List<HomeListItemAssetsChangeInfo> list;

    public HomeListItemInfo(String title, List<HomeListItemAssetsChangeInfo> list, int type) {
        this.title = title;
        this.list = list;
        this.type = type;
    }
}
