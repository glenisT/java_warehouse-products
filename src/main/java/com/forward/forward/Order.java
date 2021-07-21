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

    public double getTotalCost() {
        for (ProductQuantity p:
             listOfQuantity)
        {
            totalCost = p.getQuantity() * p.getProduct().getPrice() + totalCost;
        }
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
