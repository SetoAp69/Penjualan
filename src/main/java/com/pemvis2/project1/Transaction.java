/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.pemvis2.project1;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Lenovo
 */
public class Transaction extends javax.swing.JFrame {

    /**
     * Creates new form Transaction
     */
    private DefaultTableModel model;
    String loginID, loginStatus;
    
    public void totalCount(){
        int totalRow = jTable1.getRowCount();
        float price, total_count=0;
        int total_product;
        
        for(int i=0; i<totalRow;i++){
            total_product=Integer.parseInt(jTable1.getValueAt(i,4).toString());
            price=Float.parseFloat(jTable1.getValueAt(i,3).toString());
            total_count=total_count+(total_product*price);
        }
        total_transaction.setText(String.valueOf(total_count));
    }
    public void buy_list(){
        DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{
            id_transaction.getText(),
            id_product_trans.getText(),
            product_name_trans.getText(),
            item_price_trans.getText(),
            item_count_trans.getText(),
            total_transaction.getText()
        });
    }
    
    public void empty_list(){
        DefaultTableModel model =(DefaultTableModel) jTable1.getModel();
        while(model.getRowCount()>0){
            model.removeRow(0);
        }
    }
    
    public void fres(){
        id_product_trans.setText("");
        product_name_trans.setText("");
        item_price_trans.setText("");
        item_count_trans.setText("");
    }
    public void addItem(){
        int count;
        float itemPrice, totalPrice;
        count=Integer.valueOf(item_count_trans.getText());
        itemPrice=Float.parseFloat(item_price_trans.getText());
        totalPrice=count*itemPrice;
        total_transaction.setText(String.valueOf(totalPrice));
        
        buy_list();
        totalCount();
        fres();
        id_product_trans.requestFocus();
    }
    public Transaction(String receivedId, String receivedStatus) {
        initComponents();
        Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
        int x=screen.width/2-this.getSize().width/2;
        int y =screen.height/2-this.getSize().width/2;
        this.setLocation(x,y);
        
        loginID=receivedId;
        loginStatus=receivedStatus;
        
        try{
             Class.forName("com.mysql.cj.jdbc.Driver");
             Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/penjualan?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");    
             String transactionQuery="select count(kode_transaksi)'jumlah' from transaksi";
             PreparedStatement pst=conn.prepareStatement(transactionQuery);
             ResultSet rs=pst.executeQuery();
             if(rs.next()){
                 int transactionCount = Integer.parseInt(rs.getString(1))+1;
                 String transId;
                 if(transactionCount<10)
                     transId="J00"+String.valueOf(transactionCount);
                 else if(transactionCount<100)
                     transId="J0"+String.valueOf(transactionCount);
                 else
                     transId="J"+String.valueOf(transactionCount);
                 id_transaction.setText(transId);
             }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            
        }
        model = new DefaultTableModel();
        jTable1.setModel(model);
        model.addColumn("Transaction ID");
        model.addColumn("Product ID");
        model.addColumn("Name");
        model.addColumn("Price");
        model.addColumn("total");
        model.addColumn("Total Prices");
        
        Date date=new Date();
        SimpleDateFormat s= new SimpleDateFormat("dd-MM-yyyy");
        transaction_date.setText(s.format(date));
        
        total_transaction.setText("0");
        changes.setText("0");
        id_customer_trans.requestFocus();
        
        id_customer_trans.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    item_count_trans.requestFocus();
                    e.consume();
                }
            }
        });
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        id_transaction = new javax.swing.JTextField();
        transaction_date = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        customer_name_trans = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        id_customer_trans = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        item_price_trans = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        id_product_trans = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        product_name_trans = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        item_count_trans = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        del_btn = new javax.swing.JButton();
        add_btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        total_transaction = new javax.swing.JTextField();
        paid = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        changes = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        save_btn = new javax.swing.JButton();
        exit_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Transaction ID");

        transaction_date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transaction_dateActionPerformed(evt);
            }
        });

        jLabel3.setText("Date");

        jLabel4.setText("Customer");

        id_customer_trans.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                id_customer_transKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                id_customer_transKeyReleased(evt);
            }
        });

        jLabel5.setText("Customer ID");

        jLabel6.setText("Price");

        id_product_trans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_product_transActionPerformed(evt);
            }
        });
        id_product_trans.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                id_product_transKeyPressed(evt);
            }
        });

        jLabel7.setText("Product ID");

        jLabel8.setText("Product");

        item_count_trans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_count_transActionPerformed(evt);
            }
        });

        jLabel9.setText("Total");

        del_btn.setBackground(new java.awt.Color(255, 102, 102));
        del_btn.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        del_btn.setForeground(new java.awt.Color(255, 255, 255));
        del_btn.setText("Delete");
        del_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                del_btnActionPerformed(evt);
            }
        });

        add_btn.setBackground(new java.awt.Color(51, 204, 255));
        add_btn.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        add_btn.setForeground(new java.awt.Color(255, 255, 255));
        add_btn.setText("Add");
        add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_btnActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Total Transaction");

        paid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paidActionPerformed(evt);
            }
        });

        jLabel10.setText("Paid");

        jLabel11.setText("Changes");

        save_btn.setBackground(new java.awt.Color(51, 204, 255));
        save_btn.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        save_btn.setForeground(new java.awt.Color(255, 255, 255));
        save_btn.setText("Save");
        save_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_btnActionPerformed(evt);
            }
        });

        exit_btn.setBackground(new java.awt.Color(255, 102, 102));
        exit_btn.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        exit_btn.setForeground(new java.awt.Color(255, 255, 255));
        exit_btn.setText("Exit");
        exit_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(total_transaction, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(paid, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(changes, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(save_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(exit_btn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(id_transaction, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(product_name_trans, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(id_product_trans, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(id_customer_trans, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(transaction_date, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(customer_name_trans, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(item_price_trans, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(item_count_trans, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(del_btn)
                        .addGap(12, 12, 12)
                        .addComponent(add_btn)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(id_transaction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel3)))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(id_customer_trans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel4)))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(id_product_trans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel6)))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(product_name_trans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(item_count_trans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(transaction_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(customer_name_trans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(item_price_trans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(del_btn)
                    .addComponent(add_btn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(total_transaction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(paid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel11))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(changes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(save_btn)
                            .addComponent(exit_btn))))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void id_customer_transKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_customer_transKeyPressed
        // TODO add your handling code here:
        String idCustomer=id_customer_trans.getText();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/penjualan?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");    
            String customerQuery="SELECT nama FROM pelanggan WHERE id_pelanggan ='"+
                    idCustomer+"'";
            PreparedStatement pst=conn.prepareStatement(customerQuery);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                customer_name_trans.setText(rs.getString(1));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_id_customer_transKeyPressed

    private void id_product_transActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_product_transActionPerformed
        // TODO add your handling code here:
        String productID=id_product_trans.getText();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/penjualan?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");    
            String productQuery="SELECT nama,harga+(harga*(persen_laba/100)) 'harga_jual' FROM barang WHERE id_barang ='"+
                    productID+"'";
            PreparedStatement pst=conn.prepareStatement(productQuery);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                product_name_trans.setText(rs.getString(1));
                item_price_trans.setText(rs.getString(2));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_id_product_transActionPerformed

    private void id_product_transKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_product_transKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_id_product_transKeyPressed

    private void item_count_transActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_count_transActionPerformed
        // TODO add your handling code here:
        addItem();
    }//GEN-LAST:event_item_count_transActionPerformed

    private void del_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_del_btnActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
        int row=jTable1.getSelectedRow();
        model.removeRow(row);
        totalCount();
        changes.setText("0");
        
    }//GEN-LAST:event_del_btnActionPerformed

    private void add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_btnActionPerformed
        // TODO add your handling code here:
        addItem();
    }//GEN-LAST:event_add_btnActionPerformed

    private void paidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paidActionPerformed
        // TODO add your handling code here:
        float float_total, float_paid, float_changes;
        float_total=Float.valueOf(total_transaction.getText());
        float_paid=Float.valueOf(paid.getText());
        if(float_total>float_paid){
            JOptionPane.showMessageDialog(null,"");
            
        }else{
            float_changes=float_paid-float_total;
            changes.setText(String.valueOf(float_changes));
        }
    }//GEN-LAST:event_paidActionPerformed

    private void save_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_btnActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model =(DefaultTableModel)jTable1.getModel();
        String id_trans=id_transaction.getText();
        String id_cus=id_customer_trans.getText();
        
        Date date= new Date();
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/penjualan?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");    
            String transQuery="INSERT INTO transaksi values (?, ?, ?, ?)";
            PreparedStatement pst1=conn.prepareStatement(transQuery);
            pst1.setString(1, id_trans);
            pst1.setString(2, s.format(date));
            pst1.setString(3, id_cus);
            pst1.setString(4, loginID);
            pst1.executeUpdate();
            pst1.close();
           
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error occured 1");
        }
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/penjualan?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");    
            int row=jTable1.getRowCount();
            for(int i=0; i<row;i++){
                String detailTransQuery="INSERT INTO detail_transaksi values('"+
                        jTable1.getValueAt(i, 0)+"','"+
                        jTable1.getValueAt(i,1)+"','"+
                        jTable1.getValueAt(i, 4)+"')";
                PreparedStatement pst2=conn.prepareStatement(detailTransQuery);
                pst2.executeUpdate();
                pst2.close();

            }
           
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error Occured");
        }
        JOptionPane.showMessageDialog(null, "Detail Saved");
        dispose();
        MainForm a=new MainForm(loginID,loginStatus);
        a.setVisible(true);
    }//GEN-LAST:event_save_btnActionPerformed

    private void exit_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit_btnActionPerformed
        // TODO add your handling code here:
        dispose();
        MainForm a= new MainForm(loginID,loginStatus);
        a.setVisible(true);
    }//GEN-LAST:event_exit_btnActionPerformed

    private void id_customer_transKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_customer_transKeyReleased
        // TODO add your handling code here:
        

    }//GEN-LAST:event_id_customer_transKeyReleased

    private void transaction_dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transaction_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_transaction_dateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaction("","").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_btn;
    private javax.swing.JTextField changes;
    private javax.swing.JTextField customer_name_trans;
    private javax.swing.JButton del_btn;
    private javax.swing.JButton exit_btn;
    private javax.swing.JTextField id_customer_trans;
    private javax.swing.JTextField id_product_trans;
    private javax.swing.JTextField id_transaction;
    private javax.swing.JTextField item_count_trans;
    private javax.swing.JTextField item_price_trans;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField paid;
    private javax.swing.JTextField product_name_trans;
    private javax.swing.JButton save_btn;
    private javax.swing.JTextField total_transaction;
    private javax.swing.JTextField transaction_date;
    // End of variables declaration//GEN-END:variables
}
