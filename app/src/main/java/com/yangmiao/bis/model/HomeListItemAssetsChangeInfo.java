package com.yangmiao.bis.model;

public class HomeListItemAssetsChangeInfo implements IHomeListItem {

    public String name;
    public String assetsChangeDetail;
    public String changeTime;

    public HomeListItemAssetsChangeInfo(String name, String assetsChangeDetail, String changeTime) {
        this.name = name;
        this.assetsChangeDetail = assetsChangeDetail;
        this.changeTime = changeTime;
    }

    @Override
    public String toString() {
        return "HomeListItemAssetsChangeInfo{" +
                "name='" + name + '\'' +
                ", assetsChangeDetail='" + assetsChangeDetail + '\'' +
                ", changeTime='" + changeTime + '\'' +
                '}';
    }
}
