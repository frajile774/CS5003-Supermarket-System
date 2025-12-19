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
public class Product {

    private String productId;
    private String productName;
    private String entryDate;
    private int quantity;

    private ArrayList<Activity> activityLog = new ArrayList<>();

    public Product(String id, String name, String date, int qty) {
        this.productId = id;
        this.productName = name;
        this.entryDate = date;
        this.quantity = qty;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int q) {
        quantity = q;
    }


    public void addActivity(Activity a) {
        activityLog.add(a);
        while (activityLog.size() > 4) //trying to hold the last 4 tasks of each product
        {
            activityLog.remove(0);
        }
    }

    public ArrayList<Activity> getActivityLog() {
        return activityLog;
    }

    @Override
    public String toString() {
        return productId + " | " + productName + " | " + entryDate + " | Qty: " + quantity;
    }
}
