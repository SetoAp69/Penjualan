/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.pemvis2.project1;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Lenovo
 */
public class transactionData extends javax.swing.JFrame {

    /**
     * Creates new form transactionData
     */
    String loginID, loginStatus;
    public transactionData(String receivedID, String receivedStatus) {
        initComponents();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x=screen.width/2-this.getSize().width/2;
        int y=screen.height/2-this.getSize().height/2;
        this.setLocation(x,y);
        
        loginID=receivedID;
        loginStatus=receivedStatus;
        
        Date date=new Date();
        SimpleDateFormat s= new SimpleDateFormat("dd-MM-yyyy");
        String date1 =s.format(date);
        
        for(int i=2006; i<=Integer.valueOf(date1.substring(6,10));i++){
            cb_year.addItem(String.valueOf(i));
        }
        
        transaction_data(Integer.valueOf(date1.substring(3,5)),
                Integer.valueOf(date1.substring(6,10)));
        
        
    }
    
    private void transaction_data(int month, int year){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/penjualan?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");    
            String query ="SELECT t.kode_transaksi, t.tgl_transaksi, p.id_pelanggan, p.nama,  \n"+
                    "sum((b.harga+(b.harga*b.persen_laba/100))*d.jumlah)'nilai_transaksi' from pelanggan p JOIN  transaksi T USING(id_pelanggan)  join detail_transaksi d USING(kode_transaksi) join\n"+
                    "barang b using (id_barang)\n"+
                    "GROUP BY t.kode_transaksi\n"+
                    "HAVING MONTH(t.tgl_transaksi)="+month+" AND YEAR(t.tgl_transaksi) = "+year;
            PreparedStatement pst=conn.prepareStatement(query);
            ResultSet rs=pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            
            totalCount(1);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void totalCount(int detail){
        int row=jTable1.getRowCount();
        float subTotal, totCount=0;
        for(int i=0; i<row;i++){
            if(detail ==1){
                subTotal=Float.parseFloat(jTable1.getValueAt(i,4).toString());
            }
            else{
                subTotal=Float.parseFloat(jTable1.getValueAt(i, 6).toString());
            }
            totCount=totCount+subTotal;
        }
        total_transaction.setText(String.valueOf(totCount));
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
        cb_month = new javax.swing.JComboBox<>();
        cb_year = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        show_btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        close_btn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        total_transaction = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Month");

        cb_month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "Desember" }));
        cb_month.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_monthActionPerformed(evt);
            }
        });

        cb_year.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_yearActionPerformed(evt);
            }
        });

        jLabel2.setText("Year");

        show_btn.setText("Show");
        show_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                show_btnActionPerformed(evt);
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        close_btn.setText("Close");
        close_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close_btnActionPerformed(evt);
            }
        });

        jLabel3.setText("Total Transaction");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(show_btn))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(total_transaction, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(close_btn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cb_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cb_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(show_btn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(close_btn)
                    .addComponent(jLabel3)
                    .addComponent(total_transaction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_monthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_monthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_monthActionPerformed

    private void cb_yearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_yearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_yearActionPerformed

    private void show_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_show_btnActionPerformed
        // TODO add your handling code here:
        int month, year;
        if(cb_month.getSelectedItem().toString().equals("January")){
        month=1;
        }
        else if(cb_month.getSelectedItem().toString().equals("February")){
            month=2;
        }
        else if(cb_month.getSelectedItem().toString().equals("March")){
            month=3;
        }
        else if(cb_month.getSelectedItem().toString().equals("April")){
            month=4;
        }
        else if(cb_month.getSelectedItem().toString().equals("May")){
            month=5;
        }
        else if(cb_month.getSelectedItem().toString().equals("June")){
            month=6;
        }
        else if(cb_month.getSelectedItem().toString().equals("July")){
            month=7;
        }
        else if(cb_month.getSelectedItem().toString().equals("August")){
            month=8;
        }
        else if(cb_month.getSelectedItem().toString().equals("September")){
            month=9;
        }
        else if(cb_month.getSelectedItem().toString().equals("October")){
            month=10;
        }
        else if(cb_month.getSelectedItem().toString().equals("November")){
            month=11;
        }else {
            month=12;
        }
        
        year=Integer.valueOf(cb_year.getSelectedItem().toString());
        transaction_data(month,year);
        
        
    }//GEN-LAST:event_show_btnActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int dat=jTable1.getSelectedRow();
        String kdTrans=jTable1.getModel().getValueAt(dat,0).toString();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/penjualan?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");    
            String query="SELECT t.kode_transaksi, p.id_pelanggan, p.nama,\n"+
                    "b.nama, b.harga+(b.harga*b.persen_laba/100) 'harga_jual',d.jumlah,\n"+
                    "FROM pelanggan p JOIN transaksi t using(id_pelanggan) join\n"+
                    "detail_transaksi d USING(kode_transaksi) JOIN\n"+
                    "barang b using(id_barang)\n"+
                    "WHERE t.kode_transaksi = '"+ kdTrans+"'";
            PreparedStatement pst =conn.prepareStatement(query);
            ResultSet rs=pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            totalCount(2);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void close_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close_btnActionPerformed
        // TODO add your handling code here:
        dispose();
        MainForm a=new MainForm(loginID,loginStatus);
        a.setVisible(true);
    }//GEN-LAST:event_close_btnActionPerformed

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
            java.util.logging.Logger.getLogger(transactionData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transactionData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transactionData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transactionData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transactionData("","").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cb_month;
    private javax.swing.JComboBox<String> cb_year;
    private javax.swing.JButton close_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton show_btn;
    private javax.swing.JTextField total_transaction;
    // End of variables declaration//GEN-END:variables
}
