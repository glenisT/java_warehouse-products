package com.forward.forward;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

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

        ArrayList<String> productsList = new ArrayList<>();     //list of customer products for ease of alphabetical re-arrangement and element wandering through cycles

        //printing available products and quantities to avoid throwing IllegalArgumentException from ProductQuantity
        System.out.println("Available Products:");
        System.out.println("1." + stock.storageMap.get("Fender Jazz Bass").getProduct().getDescription()
                + " - " + stock.storageMap.get("Fender Jazz Bass").getQuantity() + " units available");
        System.out.println("2." + stock.storageMap.get("Fender Jaguar").getProduct().getDescription()
                + " - " + stock.storageMap.get("Fender Jaguar").getQuantity() + " units available");
        System.out.println("3." + stock.storageMap.get("Fender Mustang").getProduct().getDescription()
                + " - " + stock.storageMap.get("Fender Mustang").getQuantity() + " units available");
        System.out.println("4." + stock.storageMap.get("Jackson Rhodes V").getProduct().getDescription()
                + " - " + stock.storageMap.get("Jackson Rhodes V").getQuantity() + " units available");
        System.out.println("5." + stock.storageMap.get("Vox VT20+").getProduct().getDescription()
                + " - " + stock.storageMap.get("Vox VT20+").getQuantity() + " units available"
        + "\n ------------------------------------------");

        System.out.println("How many products would you like to purchase?");
        int numberOfProducts = in.nextInt();
        in.nextLine();

        int i = 0;
        int customerQuantity = 0;   //product quantity ordered - info received from user
        Vector<Integer> quantitiesSaver = new Vector<>();   //quantities are saved in a vector, so that alphabetical re-arrangement of productsList doesn't scramble the order info
        String customerNeed = ""; //customer's ordered product saved in customerNeed, to be used as key to access storageMap HashMap
        while (i < numberOfProducts)       //loop to show messages and receive info based on quantity provided in first message
        {   //MISTAKE OF OUTPUT HERE!
            System.out.println("Add product to your list (" + (numberOfProducts - i) + " left):");
            customerNeed = in.nextLine();
            if (stock.getProductQuantity(customerNeed) == 0)
            {
                System.out.println("Product no longer in stock!");
                continue;
            }
            System.out.println("Available! Product added to list.");
            productsList.add(customerNeed);
            Collections.sort(productsList, String.CASE_INSENSITIVE_ORDER);  //productsList ordered alphabetically
            i++;
        }

        for(i = 0; i < productsList.size(); i++)    //product quantities asked for AFTER alphabetical rearrangement, so quantities belong to appropriate products
        {
            System.out.println("Please provide quantity of purchase for " + productsList.get(i) + ":");
            customerQuantity = in.nextInt();
            stock.storageMap.get(productsList.get(i)).setQuantity(stock.storageMap.get(productsList.get(i)).getQuantity()
                    - customerQuantity); //for more detailed info display on order.txt
            quantitiesSaver.add(customerQuantity);  //quantities now ordered in same way as the products list
        }


        List<ProductQuantity> productsOrdered = new ArrayList<>();  //list type to correspond with Order class structure
        for (i = 0; i < productsList.size(); i++)
        {
            productsOrdered.add(stock.storageMap.get(productsList.get(i)));
        }

        Order order = new Order();
        String output = "---Your Order---"; //output to send on order.txt

        //creating order object
        order.setListOfQuantity(productsOrdered);
        order.setId((int)(Math.random() * 1000 + 1));
        order.setDescription( "" + productsList);
        Date today = new Date();
        order.setDate(today);

        //first giving individual products ordered and other info
        for(i = 0; i < order.getListOfQuantity().size(); i++)
        {
            output = output + "\n -" + order.getListOfQuantity().get(i).getProduct().getDescription() +
                    "\n Left in stock: " + order.getListOfQuantity().get(i).getQuantity() +
                    "\n Unit price: $" + productsOrdered.get(i).getProduct().getPrice() +
                    "\n Units purchased: " + quantitiesSaver.get(i) +
                    ".";
        }

        //adding final order info
        output = output + "\n---Order Details---" +
                "\n Order ID: " + order.getId() +
                "\n Order Description: " + order.getDescription() +
                "\n Order Date: " + order.getDate() +
                "\n Total Cost: " + order.getTotalCost(quantitiesSaver);

        //send application output to order.txt file
        try {
            Files.writeString(Path.of("order.txt"), output);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //system.out used for testing
        //System.out.println(quantitiesSaver);
    }
}
