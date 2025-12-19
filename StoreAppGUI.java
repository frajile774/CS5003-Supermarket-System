/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.storeapp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author ojobo
 */
public class StoreAppGUI implements ActionListener {

    private StoreManager manager;
    private String staffName;

    private JTextField ProductIDField, ProductNameField, ProductQuantityField, ProductDateField;
    private DefaultTableModel Display;
    private JTable ProductTable;

    private JButton AddtoStockButton, RemovefromStockButton, ClearButton, ActivityLogButton;
    private JButton SortButton, SearchButton;

    public StoreAppGUI(StoreManager manager, String staffName) //constructor
    {
        this.manager = manager;
        this.staffName = staffName;

        JFrame frame = new JFrame("Simply Fresh Market - Logged in as: " + staffName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950, 550);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel topWrapper = new JPanel(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Logged in as: " + staffName);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel NewProduct = new JPanel(new GridLayout(4, 2, 6, 6));

        NewProduct.add(new JLabel("Product ID (P001 or 1):"));
        ProductIDField = new JTextField();
        NewProduct.add(ProductIDField);

        NewProduct.add(new JLabel("Product Name:"));
        ProductNameField = new JTextField();
        NewProduct.add(ProductNameField);

        NewProduct.add(new JLabel("Product Quantity:"));
        ProductQuantityField = new JTextField();
        NewProduct.add(ProductQuantityField);

        NewProduct.add(new JLabel("Entry Date (dd/mm/yyyy):"));
        ProductDateField = new JTextField();
        NewProduct.add(ProductDateField);

        topWrapper.add(welcomeLabel, BorderLayout.NORTH); //smaller box to hold log in staff member and have products below
        topWrapper.add(NewProduct, BorderLayout.CENTER);

        String[] columnNames = {"ID", "Name", "Quantity", "Date"};//creation of tables
        Display = new DefaultTableModel(columnNames, 0);
        ProductTable = new JTable(Display);
        JScrollPane tableScroll = new JScrollPane(ProductTable);

        // buttons
        JPanel Buttons = new JPanel(new FlowLayout());// buttons

        AddtoStockButton = new JButton("Add to Stock");
        RemovefromStockButton = new JButton("Remove from Stock");
        ClearButton = new JButton("Clear Fields");
        ActivityLogButton = new JButton("Activity Log");
        SortButton = new JButton("Sort A–Z");
        SearchButton = new JButton("Search");

        AddtoStockButton.addActionListener(this);
        RemovefromStockButton.addActionListener(this);
        ClearButton.addActionListener(this);
        ActivityLogButton.addActionListener(this);
        SortButton.addActionListener(this);
        SearchButton.addActionListener(this);

        Buttons.add(AddtoStockButton);
        Buttons.add(RemovefromStockButton);
        Buttons.add(ClearButton);
        Buttons.add(ActivityLogButton);
        Buttons.add(SortButton);
        Buttons.add(SearchButton);

        frame.add(topWrapper, BorderLayout.NORTH); // allows all the buttons to be viewed in one panel
        frame.add(tableScroll, BorderLayout.CENTER);
        frame.add(Buttons, BorderLayout.SOUTH);

        refreshTableFromManager();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void refreshTableFromManager() //allows user to clear text fields and shows when a product has been added/removed
    {
        Display.setRowCount(0);
        for (Product p : manager.getProducts()) {
            Display.addRow(new Object[]{
                p.getProductId(),
                p.getProductName(),
                p.getQuantity(),
                p.getEntryDate()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        switch (command) //checks which button is pressed to ensure the right method is run
        {
            case "Add to Stock" ->
                addProductGUI();
            case "Remove from Stock" ->
                removeProduct();
            case "Clear Fields" ->
                clearFields();
            case "Activity Log" ->
                showActivityLog();
            case "Sort A–Z" ->
                sortProducts();
            case "Search" ->
                searchProductGUI();
        }
    }

    private void addProductGUI() {    //gets the users inputs
        String idInput = ProductIDField.getText().trim();// trim removes the spaces
        String nameInput = ProductNameField.getText().trim();
        String qtyInput = ProductQuantityField.getText().trim();
        String dateInput = ProductDateField.getText().trim();
        //makes sure that the user puts something in all fields to add stock
        if (idInput.isEmpty() || nameInput.isEmpty() || qtyInput.isEmpty() || dateInput.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill ALL fields.");
            return;
        }

        int qty;
        try {
            qty = Integer.parseInt(qtyInput);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Quantity must be a number.");
            return; //exeception handling to ensure user inputs a number for the qty field
        }
        if (qty < 0) {
            JOptionPane.showMessageDialog(null, "Quantity cannot be negative.");
            return; //exeception handling to ensure user inputs a positive number for the qty field
        }
        //Add Product
        String id = normaliseId(idInput);

        Product p = new Product(id, nameInput, dateInput, qty);
        manager.addProduct(p);

        refreshTableFromManager();

        JOptionPane.showMessageDialog(null, "Product Added Successfully!");
    }
    //Remove Product
    private void removeProduct() { 
    String idRaw = ProductIDField.getText().trim(); 
 
    if (idRaw.isEmpty()) { 
        JOptionPane.showMessageDialog(null, "Enter an ID to remove."); 
        return; //user must enter ProductID for remove product function 
    } 


        String id = normaliseId(idRaw);

        boolean removed = manager.removeProduct(id);

        refreshTableFromManager();

        JOptionPane.showMessageDialog(null,
                removed ? "Product Removed." : "Product Not Found.");//tells the user that the product is removed
    }

    //connects to linearArray Class
    private void searchProductGUI() {

        String idRaw = ProductIDField.getText().trim();
        if (idRaw.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter a Product ID to search.");
            return;
        }

        String id = normaliseId(idRaw);
        Product p = manager.searchProduct(id);

        if (p == null) {
            JOptionPane.showMessageDialog(null, "Product Not Found.");
        } else {
            JOptionPane.showMessageDialog(null,
                    "PRODUCT FOUND:\n\n"
                    + "ID: " + p.getProductId() + "\n"
                    + // "\n" prints new lines to make the format look easily readable
                    "Name: " + p.getProductName() + "\n"
                    + "Quantity: " + p.getQuantity() + "\n"
                    + "Date: " + p.getEntryDate());
        }
    }

    //Activity Log
    private void showActivityLog() {
        String idRaw = ProductIDField.getText().trim();

        if (idRaw.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter a product ID first.");
            return;
        }

        String id = normaliseId(idRaw);
        Product p = manager.searchProduct(id);

        if (p == null) {
            JOptionPane.showMessageDialog(null, "Product not found.");
            return;
        } else {
            p.addActivity(new Activity(
                    "ACT" + (p.getActivityLog().size() + 1),
                    p.getProductName(),
                    p.getQuantity(),
                    p.getEntryDate(),
                    "Product Searched"
            ));

            JFrame actFrame = new JFrame("Activity Log for " + p.getProductName());
            actFrame.setSize(500, 300);

            String[] columns = {"Activity ID", "Name", "Qty", "Date", "Task"};
            DefaultTableModel model = new DefaultTableModel(columns, 0);

            for (Activity a : p.getActivityLog())//showcases every activity recorded
            {
                model.addRow(new Object[]{
                    a.getActivityId(),
                    a.getName(),
                    a.getQty(),
                    a.getDate(),
                    a.getTask()
                });
            }

            JTable table = new JTable(model);
            actFrame.add(new JScrollPane(table));

            actFrame.setVisible(true);
             System.out.println("Activities for " + p.getProductId() + ": " + p.getActivityLog().size()); 
        }
    }

    //connects SelectionSort Class
    private void sortProducts() {

        manager.selectionSortProducts();
        refreshTableFromManager();

        JOptionPane.showMessageDialog(null,
                "Products sorted A–Z.\nSuccessfully.");
    }

    private void clearFields() {
        ProductIDField.setText("");
        ProductNameField.setText("");
        ProductQuantityField.setText("");
        ProductDateField.setText("");
    }

    private String normaliseId(String raw) {
        if (raw.matches("\\d+")) {
            int number = Integer.parseInt(raw);
            return "P" + String.format("%03d", number);//trying to format the raw input but can't seem to get it working
        }
        return raw.toUpperCase();
    }// method works now

}
