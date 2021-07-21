package com.forward.forward;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ForwardApplication {

    public static void main(String[] args)
    {

        Product product1 = new Product("Fender Jazz Bass", "Bass", "Relative", "None", 400, 2016);
        Product product2 = new Product("Fender Jaguar", "Guitar", "Relative", "None", 300, 2020);
        Product product3 = new Product("Fender Mustang", "Guitar", "Relative", "None", 200, 2018);
        Product product4 = new Product("Jackson Rhodes V", "Guitar", "Relative", "None", 1000, 1998);
        Product product5 = new Product("Vox VT20+", "Amplifiers", "Relative", "None", 250, 2015);

        ProductQuantity product1Quantity = new ProductQuantity(product1, 2);
        ProductQuantity product2Quantity = new ProductQuantity(product2, 4);
        ProductQuantity product3Quantity = new ProductQuantity(product3, 3);
        ProductQuantity product4Quantity = new ProductQuantity(product4, 0);
        ProductQuantity product5Quantity = new ProductQuantity(product5, 10);

        Warehouse stock = new Warehouse();
        stock.addProductQuantity("Fender Jazz Bass", product1Quantity);
        stock.addProductQuantity("Fender Jaguar", product2Quantity);
        stock.addProductQuantity("Fender Mustang", product3Quantity);
        stock.addProductQuantity("Jackson Rhodes V", product4Quantity);
        stock.addProductQuantity("Vox VT20+", product5Quantity);

        System.out.println("Search for product availability: ");
        Scanner in = new Scanner(System.in);
        String description = in.next();


        ArrayList<Warehouse> productsList = new ArrayList<>();
        productsList.add(stock);
        for (Warehouse w:
             productsList) {
            if (w.getProductQuantity(description) == 0)
            {
                System.out.println("Product unavailable!");
            }
            else
            {
                System.out.println("Available!");
            }
        }

        //System.out.println(productsList);  //made for testing

    }

}
