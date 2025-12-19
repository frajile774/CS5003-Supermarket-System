/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.storeapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ojobo
 */
//class that allows staff members to login
public class StaffLogin implements ActionListener {

    private JTextField staffIdField;
    private JButton loginButton;

    public StaffLogin() {

        JFrame frame = new JFrame("Staff Login");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1, 10, 10));
        frame.setLocationRelativeTo(null);

        JLabel title = new JLabel("Enter Staff ID", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));

        staffIdField = new JTextField();
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        frame.add(title);
        frame.add(staffIdField);
        frame.add(loginButton);

        frame.setVisible(true);
    }
//checks if the Staff ID is correct, if not prints a message to the user

    @Override
    public void actionPerformed(ActionEvent e) {

        String id = staffIdField.getText().trim();
        String staffName = validateStaff(id);

        if (staffName == null) {
            JOptionPane.showMessageDialog(null, "Access Denied! Invalid Staff ID.");
            return;
        }

        JOptionPane.showMessageDialog(null, "Welcome " + staffName + "!");

        new StoreAppGUI(new StoreManager(), staffName);
    }

    private String validateStaff(String id) {
        return switch (id) {
            case "24010258" ->
                "Myeisha Johnson";//used our University Student IDs
            case "24008103" ->
                "Steven Hall";
            case "22007365" ->
                "Franklin Ojobo";
            case "00000000" ->
                "Temporary Staff";// temporary staff just to mimic a real life scenario of temporary staff members using the system and have not been given an ID
            default ->
                null;
        };
    }

    public static void main(String[] args) {
        new StaffLogin();
    }
}
