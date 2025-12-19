/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.storeapp;

import java.util.ArrayList;

/**
 *
 * @author ojobo
 */
public class StoreManager {
//Added 5 products products to the system already 

    private ArrayList<Product> products = new ArrayList<>();

    public StoreManager() {

        products.add(new Product("P001", "Hibiscus Leaves", "28/11/2025", 50));
        products.add(new Product("P002", "Sea Moss", "21/11/2025", 22));
        products.add(new Product("P003", "Chicken Breasts", "25/11/2025", 67));
        products.add(new Product("P004", "Lentils", "28/11/2025", 30));
        products.add(new Product("P005", "Breadfruit", "30/11/2025", 10));
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product p) {
        products.add(p);

        p.addActivity(new Activity(
                "ACT" + (p.getActivityLog().size() + 1),
                p.getProductName(),
                p.getQuantity(),
                p.getEntryDate(),
                "Product Added"
        ));
        System.out.println("BACKEND SIZE: " + products.size());//temporary code to see that the system is adding products not just to the GUI
    }

    public boolean addStock(String id, int amount) {

        for (Product p : products) {
            if (p.getProductId().equalsIgnoreCase(id)) {

                // update quantity
                int newQty = p.getQuantity() + amount;
                p.setQuantity(newQty);

                // log activity
                p.addActivity(new Activity(
                        "ACT" + (p.getActivityLog().size() + 1),
                        p.getProductName(),
                        amount,
                        p.getEntryDate(),
                        "Stock Added"
                ));

                return true;
            }
        }
        return false;
    }

//attempt to remove products with the same Product ID, but cannot get it to run
    public boolean removeProduct(String id) {

        for (Product p : products) {
            if (p.getProductId().equalsIgnoreCase(id)) {

                // trying to log activity before product removal
                p.addActivity(new Activity(
                        "ACT" + (p.getActivityLog().size() + 1),
                        p.getProductName(),
                        p.getQuantity(),
                        p.getEntryDate(),
                        "Product Removed"
                ));

                products.remove(p);
                return true;
            }
        }
        return false;
    }

    public Product searchProduct(String id) {
        ArrayList<String> ids = new ArrayList<>();
        for (Product p : products) {
            ids.add(p.getProductId());
        }

        LinearArray la = new LinearArray(ids);
        int index = la.linearSearch(id);

        return (index != -1) ? products.get(index) : null;
    }

    public void selectionSortProducts() {
        SelectionSort sorter = new SelectionSort(products);
        sorter.sort();
    }
}
