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
public class SelectionSort {

    private ArrayList<Product> data;

    public SelectionSort(ArrayList<Product> inputList) {
        data = inputList;
    }

    public void sort() {
        int smallest;

        for (int i = 0; i < data.size() - 1; i++) {
            smallest = i;

            for (int index = i + 1; index < data.size(); index++) {

                if (data.get(index).getProductName().compareToIgnoreCase(data.get(smallest).getProductName()) < 0) {

                    smallest = index;
                }
            }

            swap(i, smallest);
            printPass(i + 1, smallest);
        }
    }

    private void swap(int first, int second) {
        Product temp = data.get(first);
        data.set(first, data.get(second));
        data.set(second, temp);
    }

    private void printPass(int pass, int index) {

        System.out.print("After pass " + pass + ": ");

        // print all product names instead of numbers
        for (int i = 0; i < data.size(); i++) {
            System.out.print(data.get(i).getProductName() + " | ");
        }
        System.out.println();

        for (int i = 0; i < pass; i++) {
            System.out.print("-- ");
        }
        System.out.println("\n");
    }
}
