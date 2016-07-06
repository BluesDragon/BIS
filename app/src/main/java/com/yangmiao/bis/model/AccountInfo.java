package com.yangmiao.bis.model;

public class AccountInfo {

    public static final int AssetsType_OTHER = 0;
    public static final int AssetsType_LICAI = 1;
    public static final int AssetsType_HUOQI = 2;
    public static final int AssetsType_BAOXIAN = 3;
    public static final int AssetsType_DINGQI = 4;
    public static final int AssetsType_JIJIN = 5;

    public static final int ConsumerGrade_NORMAL = 1;
    public static final int ConsumerGrade_VIP = 2;
    public static final int ConsumerGrade_CAIFU = 3;
    public static final int ConsumerGrade_SIHANG = 4;

    public String name;// 姓名
    public String sex;// 性别
    public String tel;// 电话
    public String address;// 地址
    public String company;// 单位
    public int assetsType = AssetsType_OTHER;// 资产类型
    public int consumerGrade = ConsumerGrade_NORMAL;// 客户等级
    public String cardId;// 身份证号
    public String flag;// 标记
    public String attribution;// 归属地
    public int integral;// 积分

    public AccountInfo() {

    }

    public AccountInfo(String name, String sex, String tel, String address, String company, int assetsType, int consumerGrade, String cardId, String flag, String attribution, int integral) {
        this.name = name;
        this.sex = sex;
        this.tel = tel;
        this.address = address;
        this.company = company;
        this.assetsType = assetsType;
        this.consumerGrade = consumerGrade;
        this.cardId = cardId;
        this.flag = flag;
        this.attribution = attribution;
        this.integral = integral;
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", company='" + company + '\'' +
                ", assetsType=" + assetsType +
                ", consumerGrade=" + consumerGrade +
                ", cardId='" + cardId + '\'' +
                ", flag='" + flag + '\'' +
                ", attribution='" + attribution + '\'' +
                ", integral=" + integral +
                '}';
    }
}
