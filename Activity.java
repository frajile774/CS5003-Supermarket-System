/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.storeapp;

/**
 *
 * @author ojobo
 */
public class Activity {

    private String activityId;
    private String name;
    private int qty;
    private String date;
    private String task;   //  extra attribute to see the tasks in the activity log for each products

    public Activity(String activityId, String name, int qty, String date, String task) {
        this.activityId = activityId;
        this.name = name;
        this.qty = qty;
        this.date = date;
        this.task = task;
    }

    public String getActivityId() {
        return activityId;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }

    public String getDate() {
        return date;
    }

    public String getTask() {
        return task;
    }   
}
