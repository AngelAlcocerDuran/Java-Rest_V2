package mx.edu.utez.model.customer;

import mx.edu.utez.database.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoCostumer {

    Connection con;
    PreparedStatement pstm;
    ResultSet rs;
    Statement stm;
    String query;

    public List<Customer> findAll(){
        List<Customer> listCustomers = new ArrayList();
        try{
            con = ConnectionDB.getConnection();
            query = "SELECT * FROM customers;";
            stm = con.createStatement();
            rs = stm.executeQuery(query);
            while(rs.next()){
                Customer c = new Customer();
                c.setCustomerNumber(rs.getInt("customerNumber"));
                c.setCustomerName(rs.getString("customerName"));
                c.setContactLastName(rs.getString("contactLastName"));
                c.setContactFirstName(rs.getString("contactFirstName"));
                c.setPhone(rs.getString("phone"));
                c.setAddressLine1(rs.getString("addressLine1"));
                c.setAddressLine2(rs.getString("addressLine2"));
                c.setCity(rs.getString("city"));
                c.setState(rs.getString("state"));
                c.setPostalCode(rs.getString("postalCode"));
                c.setCountry(rs.getString("country"));
                c.setSalesRepEmployeeNumber(rs.getInt("salesRepEmployeeNumber"));
                c.setCreditLimit(rs.getDouble("creditLimit"));

                listCustomers.add(c);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            closeConnections();
        }
        return listCustomers;
    }

    public Customer findByCustomerNumber(int customerNumber){
        Customer c = null;
        try{
            con = ConnectionDB.getConnection();
            query = "SELECT * FROM customers WHERE customerNumber = ?";
            pstm = con.prepareStatement(query);
            pstm.setInt(1, customerNumber);
            rs = pstm.executeQuery();
            if(rs.next()){
                c = new Customer();
                c.setCustomerNumber(rs.getInt("customerNumber"));
                c.setCustomerName(rs.getString("customerName"));
                c.setContactLastName(rs.getString("contactLastName"));
                c.setContactFirstName(rs.getString("contactFirstName"));
                c.setPhone(rs.getString("phone"));
                c.setAddressLine1(rs.getString("addressLine1"));
                c.setAddressLine2(rs.getString("addressLine2"));
                c.setCity(rs.getString("city"));
                c.setState(rs.getString("state"));
                c.setPostalCode(rs.getString("postalCode"));
                c.setCountry(rs.getString("country"));
                c.setSalesRepEmployeeNumber(rs.getInt("salesRepEmployeeNumber"));
                c.setCreditLimit(rs.getDouble("creditLimit"));
            }
        }catch (SQLException | NullPointerException e){
            e.printStackTrace();
        }finally {
            closeConnections();
        }
        return c;
    }

    public boolean saveCustomer(Customer c, boolean isCreated){
        boolean state = false;
        try{
            con = ConnectionDB.getConnection();
            if(isCreated){
                query = "INSERT INTO customers(customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2," +
                        "city, state, postalCode, country, salesRepEmployeeNumber, creditLimit) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
                pstm = con.prepareStatement(query);
                pstm.setInt(1, c.getCustomerNumber());
                pstm.setString(2, c.getCustomerName());
                pstm.setString(3, c.getContactLastName());
                pstm.setString(4, c.getContactFirstName());
                pstm.setString(5, c.getPhone());
                pstm.setString(6, c.getAddressLine1());
                pstm.setString(7, c.getAddressLine2());
                pstm.setString(8, c.getCity());
                pstm.setString(9, c.getState());
                pstm.setString(10, c.getPostalCode());
                pstm.setString(11, c.getCountry());
                pstm.setInt(12, c.getSalesRepEmployeeNumber());
                pstm.setDouble(13, c.getCreditLimit());
                state = pstm.executeUpdate() == 1;
            }else{
                query = "UPDATE customers SET customerName = ?, contactLastName = ?, contactFirstName = ?, phone = ?, addressLine1 = ?, " +
                        "addressLine2 = ?, city = ?, state = ?, postalCode = ?, country = ?, salesRepEmployeeNumber = ?, creditLimit = ?" +
                        "WHERE customerNumber = ?";
                pstm = con.prepareStatement(query);
                pstm.setInt(13, c.getCustomerNumber());
                pstm.setString(1, c.getCustomerName());
                pstm.setString(2, c.getContactLastName());
                pstm.setString(3, c.getContactFirstName());
                pstm.setString(4, c.getPhone());
                pstm.setString(5, c.getAddressLine1());
                pstm.setString(6, c.getAddressLine2());
                pstm.setString(7, c.getCity());
                pstm.setString(8, c.getState());
                pstm.setString(9, c.getPostalCode());
                pstm.setString(10, c.getCountry());
                pstm.setInt(11, c.getSalesRepEmployeeNumber());
                pstm.setDouble(12, c.getCreditLimit());
                state = pstm.executeUpdate() == 1;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnections();
        }
        return state;
    }

    public boolean deleteCustomer(int customerNumber){
        boolean state = false;
        try{
            con = ConnectionDB.getConnection();
            query = "DELETE FROM customers WHERE customerNumber = ?;";
            pstm = con.prepareStatement(query);
            pstm.setInt(1, customerNumber);
            state = pstm.executeUpdate() == 1;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnections();
        }
        return state;
    }

    public void closeConnections(){
        try{
            if(con != null){
                con.close();
            }
            if(pstm != null){
                pstm.close();
            }
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
