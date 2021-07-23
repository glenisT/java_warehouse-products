package com.forward.forward;

import java.util.Date;
import java.util.List;

public class Order{

    private int id;
    private List<ProductQuantity> listOfQuantity;
    private String description;
    private double totalCost = 0;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ProductQuantity> getListOfQuantity() {
        return listOfQuantity;
    }

    public void setListOfQuantity(List<ProductQuantity> listOfQuantity) {
        this.listOfQuantity = listOfQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", listOfQuantity=" + listOfQuantity +
                ", description='" + description + '\'' +
                ", totalCost=" + totalCost +
                ", date=" + date +
                '}';
    }

    //getTotalCost receives List parameter to avoid order info scrambling
    public double getTotalCost(List<Integer> quantitiesSaver) {
        for (int i = 0; i < listOfQuantity.size(); i++)
        {
            totalCost = quantitiesSaver.get(i) * listOfQuantity.get(i).getProduct().getPrice() + totalCost;
        }
        return totalCost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
