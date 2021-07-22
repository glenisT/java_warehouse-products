package com.forward.forward;

import java.util.HashMap;

public class Warehouse
{

    public HashMap<String, ProductQuantity> storageMap = new HashMap<String, ProductQuantity>();

    public Warehouse() {
    }

    public void addProductQuantity(String description, ProductQuantity quantity)
    {
        if(description != quantity.getProduct().getDescription())
        {
            throw new IllegalArgumentException("<devMessage>Please make sure that description and Product description match" +
                    "when creating objects!");
        }
        storageMap.put(description, quantity);
    }

    public int getProductQuantity(String description)
    {
        if(!storageMap.containsKey(description))
        {
            System.out.println("Product is not present in warehouse!");
            return 0;
        }
        return storageMap.get(description).getQuantity();
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "storageMap=" + storageMap +
                '}';
    }
}
