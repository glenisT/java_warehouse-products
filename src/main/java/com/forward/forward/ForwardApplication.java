package com.forward.forward;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
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

        Scanner in = new Scanner(System.in);

        ArrayList<String> productsList = new ArrayList<>();

        System.out.println("How many products would you like to purchase?");
        int numberOfProducts = in.nextInt();

        int i = 1;
        while (i <= numberOfProducts)
        {
            System.out.println("Add product to your list (" + (numberOfProducts - i + 1) + " left):");
            String customerNeed = in.nextLine();
            if (stock.getProductQuantity(customerNeed) == 0)
            {
                System.out.println("Product no longer in stock!");
                continue;
            }
            System.out.println("Available! Product added to list.");
            productsList.add(customerNeed);
            stock.storageMap.get(customerNeed).setQuantity(stock.storageMap.get(customerNeed).getQuantity() - 1);
            i++;
        }

        Collections.sort(productsList, String.CASE_INSENSITIVE_ORDER);
        ArrayList<ProductQuantity> productsOrdered = new ArrayList<>();
        for (i = 0; i < productsList.size(); i++)
        {
            productsOrdered.add(stock.storageMap.get(productsList.get(i)));
        }

        Order order = new Order();
        System.out.println("Your Order:");
        for(i = 0; i < productsOrdered.size(); i++)
        {
            System.out.println(productsOrdered.get(i).getProduct().getDescription());//description
            System.out.println("Left in stock: " + productsOrdered.get(i).getQuantity());//quantity
            System.out.println("Unit price: " + productsOrdered.get(i).getProduct().getPrice());//cost
        }

        System.out.println("Your orders: " + productsList + ".");
        System.out.println("Your orders FULL: " + productsOrdered + ".");

        //System.out.println(stock.getProductQuantity(productsList.get(1)));  //made for testing

    }
}
