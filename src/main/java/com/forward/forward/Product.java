package com.forward.forward;

import java.util.Calendar;

public class Product
{

    private String description;
    private String type;
    private String capacity;
    private String processing;
    private double price;
    private int yearOfProduction;


    public Product(String description, String type, String capacity,
                   String processing, double price, int yearOfProduction)
    {
        this.description= description;
        this.type = type;
        this.capacity = capacity;
        this.processing = processing;
        this.price = price;
        this.yearOfProduction = yearOfProduction;
    }


    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        if(description == null || description.length() <= 0)
        {
            throw new IllegalArgumentException("Please provide a description for the product!");
        }
        this.description = description;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        if(type == null || type.length() <= 0)
        {
            throw new IllegalArgumentException("Type not valid!");
        }
        this.type = type;
    }

    public String getCapacity()
    {
        return capacity;
    }

    public void setCapacity(String capacity)
    {
        if(capacity == null || capacity.length() <= 0)
        {
            throw new IllegalArgumentException("Capacity cannot be below 0!");
        }
        this.capacity = capacity;
    }

    public String getProcessing()
    {
        return processing;
    }

    public void setProcessing(String processing)
    {
        if(processing == null || processing.length() <= 0)
        {
            throw new IllegalArgumentException("Processing cannot be below 0!");
        }
        this.processing = processing;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        if(price <= 0)
        {
            throw new IllegalArgumentException("Price cannot be below 0!");
        }
        this.price = price;
    }

    public int getYearOfProduction()
    {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction)
    {
        if((Calendar.getInstance().get(Calendar.YEAR) - yearOfProduction) > 10)
        {
            throw new IllegalArgumentException("Product expired!");
        }
        this.yearOfProduction = yearOfProduction;
    }

    @Override
    public String toString()
    {
        return "Product{" +
                "description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", capacity='" + capacity + '\'' +
                ", processing='" + processing + '\'' +
                ", price=" + price +
                ", yearOfProduction=" + yearOfProduction +
                '}';
    }  //made for testing
}
