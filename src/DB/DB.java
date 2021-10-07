
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;


public class DB {
    
    private final String driver = "org.sqlite.JDBC";
    private final String url ="jdbc:sqlite:db/proje.db";
    
    
    private Connection conn;
    private PreparedStatement pre;


    public DB(){
        
        try {
            Class.forName(driver); 
            conn = DriverManager.getConnection(url); 
            System.out.println("Connection Success");
            
            
        } catch (Exception e) {
            System.err.println("Connection Error: " + e);
        }
    }
    

    
    
    
    
    
     
    public int getOrder(String name,String surname, String location, String all){
        int status = 0;
       
        
        try {
            String sql = " insert into siparisler values ( null, ?, ?, 'Hazırlanıyor' , ? ,?)";
            pre = conn.prepareStatement(sql);
            pre.setString(1, name);
            pre.setString(2, surname);
            pre.setString(3, location);
            pre.setString(4, all);
            status = pre.executeUpdate();
            
            
             
            
        } catch (Exception e) {
            System.err.println("Sipariş Update: " + e);
        }
     
        return status;
        
    }
    
     public int orderEdit(int customerid){
        int status = 0;
        
        try {
            String sql = "update siparisler set situation ='Yola Çıktı' where cid = ? ";
            pre = conn.prepareStatement(sql);
            pre.setInt(1,customerid);
            status = pre.executeUpdate();
         
        } catch (Exception e) {
            System.err.println("Yola çıktı Update: " + e);
        }
        
        
        return status;
        
    }
     
    
    public int customerSave(String name, String surname,String Tel,String location){
        int status =0;
        
        try {
            String sql = " insert into musteri values ( null, ?, ?, ?, ? )";
            pre = conn.prepareStatement(sql);
            pre.setString(1, name);
            pre.setString(2, surname);
            pre.setString(3, Tel);
            pre.setString(4, location);
            status = pre.executeUpdate();
            
            
        } catch (Exception e) {
             System.err.println("CustomerSave Error: " + e);
             if (e.toString().contains("SQLITE_CONSTRAINT_UNIQUE")){ 
                status = -1;
            }
             
        }
   
        return status;
        
    }
    

    
    public DefaultTableModel listCustomer(){
         DefaultTableModel cdtm = new DefaultTableModel();
         
        cdtm.addColumn("cID");
        cdtm.addColumn("Adı");
        cdtm.addColumn("Soyadı");
        cdtm.addColumn("Telefon");
        cdtm.addColumn("Adres");
        
        try {
            String sql = "select * from musteri";
            pre = conn.prepareStatement(sql);
            
            
            ResultSet rs = pre.executeQuery();
            
            while(rs.next()){
                int cid = rs.getInt("cid");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String Tel = rs.getString("Tel");
                String location = rs.getString("location");
                
                Object [] row = {cid,name,surname,Tel,location };
                cdtm.addRow(row);
            }
        } catch (Exception e) {
            System.err.println("allCustomer: " + e);
        }
         
        return cdtm;
    }
    
    
    public DefaultTableModel listCustomerOrder(){
         DefaultTableModel odtm = new DefaultTableModel();
         
         
        odtm.addColumn("cID");
        odtm.addColumn("Müşteri Adı");
        odtm.addColumn("Müşteri Soyadı");
        odtm.addColumn("Durum");
        odtm.addColumn("Adres");
        odtm.addColumn("Tutar");
        
        try {
            String sql = "select * from siparisler";
            pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            
            
            while(rs.next()){
                int cid = rs.getInt("cid");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String situation = rs.getString("situation");
                String location = rs.getString("location");
                String all = rs.getString("all");
                
                Object [] row = {cid,name,surname,situation,location, all };
                odtm.addRow(row);
                
            }
           System.err.println("getOrders: " ); 
        } catch (Exception e) {
            System.err.println("getOrder error: " + e);
            
        }
         return odtm;
    }
    
    
        //müşteri bul-müşteri ara
    public DefaultTableModel searchCustomer(String name,String surname){
        DefaultTableModel dt = new DefaultTableModel();
        
        dt.addColumn("cID");
        dt.addColumn("Adı");
        dt.addColumn("Soyadı");
        dt.addColumn("Telefon");
        dt.addColumn("Adres");
        
       
        try {
           
            String sql = "select * from musteri where name= ? and surname= ? ";
            pre = conn.prepareStatement(sql);
            pre.setString(1, name);
            pre.setString(2, surname);
            ResultSet rs = pre.executeQuery();
            
            
            while(rs.next()){
                int cid = rs.getInt("cid");
                String cname = rs.getString("name");
                String csurname = rs.getString("surname");
                String Tel = rs.getString("Tel");
                String location = rs.getString("location");
                
                Object [] row = {cid,cname,csurname,Tel,location };
                dt.addRow(row);
            }
            
        } catch (Exception e) {
            System.err.println("CustomerSearch: " + e);
        }
        
        
        return dt;
    }
    
   
    //////// SQLITE_CONSTRAINT_UNIQUE  unique olmalı
    public int customerEdit(String name, String surname, String Tel, String location,int cid){

        int status = 0;
        try {
             String sql = "update musteri set name = ?, surname = ?, Tel = ?, location = ? where cid = ? ";
             pre = conn.prepareStatement(sql);
             pre.setString(1, name);
             pre.setString(2, surname);
             pre.setString(3, Tel);
             pre.setString(4, location);
             pre.setInt(5, cid);
             status = pre.executeUpdate();
             
        } catch (Exception e) {
            System.err.println("CustomerEdit Error: " + e);
            if (e.toString().contains("SQLITE_CONSTRAINT_UNIQUE")){ 
            }
        }
        
        
        
        return status;
        
        
        
    }
    // müşteri silme
    public int customerDelete(int cid){
        int status = 0;
        
        try {
            String sql = "delete from musteri where cid = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, cid);
            status = pre.executeUpdate();
            System.out.println("Customer Deleted");
        } catch (Exception e) {
            System.err.println("Customer can not deleted: " + e);
        }
        
        
        return status;
        
    }
    
  
    
   
    /// 
    //Sipariş Gönderildi
    public int orderDelivered(int cid){
        int status = 0;
        
        try {
            String sql = "update siparisler set situation ='Teslim Edildi' where cid = ? ";
            pre = conn.prepareStatement(sql);
            pre.setInt(1,cid);
            status = pre.executeUpdate();
            
        } catch (Exception e) {
            System.err.println("Teslim Edildi Update: " + e);
        }
            
        
        return status;
    }
    
    public int ordersEditDelete(int cid){
        int status = 0;
        
        try {
            String sql = "delete from siparisler where cid = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1,cid);
            status = pre.executeUpdate();
            
        } catch (Exception e) {
            System.err.println("Order Delete: " + e);
        }
        
        return status;
    }
    
    
    
    public DefaultTableModel getOrder(){
        DefaultTableModel dtm = new DefaultTableModel();
        
        dtm.addColumn("cID");
        dtm.addColumn("Müşteri Adı");
        dtm.addColumn("Müşteri Soyadı");
        dtm.addColumn("Durum");
        dtm.addColumn("Adres");
        dtm.addColumn("Tutar");
        
        try {
            String sql = "select * from siparisler where situation = 'Hazırlanıyor'";
            pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            
             while(rs.next()){
                int cid = rs.getInt("cid");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String situation = rs.getString("situation");
                String location = rs.getString("location");
                String all = rs.getString("all");
                
                Object [] row = {cid,name,surname,situation,location, all };
                dtm.addRow(row);
                 
             }
    
            
        } catch (Exception e) {
            
            System.err.println("allToday Orders: " + e);
        }
    
        return dtm;
        
        
    }
    
    
       
        
     public void close(){
        
        try {
            if( conn != null && !conn.isClosed()){ 
                conn.close();
                
            }
            if ( pre != null ){ 
                pre.close();
            }
        } catch (Exception e) {
            System.err.println("Close error: " + e);
        }
        
    }   
        
        
        
        
        
        
        
        
        
        
        
        
    }


















