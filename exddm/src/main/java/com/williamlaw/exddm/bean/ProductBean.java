package com.williamlaw.exddm.bean;



/**
 * Created by Administrator on 2017/8/28.
 */
public class ProductBean {

    private int id;

    private String name;

    private String iconUrl;

    private String desc;

    private String produceDate;

    private String company;

    private float price;

    private int inventory;

    private int comments;

    public ProductBean(int id, String name, String iconUrl, String desc,
                       String produceDate, String company, float price,
                       int inventory, int comments) {
        this.id = id;
        this.name = name;
        this.iconUrl = iconUrl;
        this.desc = desc;
        this.produceDate = produceDate;
        this.company = company;
        this.price = price;
        this.inventory = inventory;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getDesc() {
        return desc;
    }

    public String getProduceDate() {
        return produceDate;
    }

    public String getCompany() {
        return company;
    }

    public float getPrice() {
        return price;
    }

    public int getInventory() {
        return inventory;
    }

    public int getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", desc='" + desc + '\'' +
                ", produceDate='" + produceDate + '\'' +
                ", company='" + company + '\'' +
                ", price=" + price +
                ", inventory=" + inventory +
                ", comments=" + comments +
                '}';
    }
}
