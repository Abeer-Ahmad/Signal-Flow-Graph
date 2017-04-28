package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TableSectionDesigner {

    public TableSectionDesigner(JScrollPane pathScroll, JTable pathTable,
                                JScrollPane nonTouchingScroll, JTable nonTouchingTable,
                                JScrollPane deltaScroll, JTable deltaTable, JFrame frame) {
        // Forward Paths and Loops
        pathScroll.setBounds(795, 36, 215, 137);
        frame.getContentPane().add(pathScroll);

        pathTable.setFont(new Font("Tahoma", Font.BOLD, 13));
        pathTable.setModel(new DefaultTableModel(new Object[][]{{null, null},
                {null, null}, {null, null}, {null, null}, {null, null},
                {null, null}, {null, null},}, new String[]{
                "ForwardPaths", "Loops"}));
        pathTable.getColumnModel().getColumn(0).setPreferredWidth(144);
        pathTable.getColumnModel().getColumn(1).setPreferredWidth(144);
        pathScroll.setViewportView(pathTable);

        // Non-Touching Loops
        nonTouchingScroll.setBounds(795, 212, 215, 137);
        frame.getContentPane().add(nonTouchingScroll);

        nonTouchingTable.setModel(new DefaultTableModel(new Object[][]{{null},
                {null}, {null}, {null}, {null}, {null}, {null},},
                new String[]{"Non-Touching Loops"}));
        nonTouchingTable.getColumnModel().getColumn(0).setPreferredWidth(149);
        nonTouchingScroll.setViewportView(nonTouchingTable);
        nonTouchingTable.setFont(new Font("Tahoma", Font.BOLD, 13));

        // Deltas
        deltaScroll.setBounds(795, 385, 215, 137);
        frame.getContentPane().add(deltaScroll);

        deltaTable.setModel(new DefaultTableModel(new Object[][]{{null},
                {null}, {null}, {null}, {null}, {null}, {null},},
                new String[]{"Deltas"}));
        deltaScroll.setViewportView(deltaTable);
        deltaTable.setFont(new Font("Tahoma", Font.BOLD, 13));
    }
}