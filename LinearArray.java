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
public class LinearArray {

    private ArrayList<String> items;

    public LinearArray(ArrayList<String> items) {
        this.items = items;
    }

    public int linearSearch(String key) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).equalsIgnoreCase(key)) {
                return i;
            }
        }
        return -1;
    }
}
