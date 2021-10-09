package mx.edu.utez.model.product;

import mx.edu.utez.database.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoProduct {

    Connection con;
    PreparedStatement pstm;
    ResultSet rs;
    Statement stm;
    String query;

    public List<Product> findAll(){
        List<Product> listProducts = new ArrayList();
        try{
            con = ConnectionDB.getConnection();
            query = "SELECT * FROM products;";
            stm = con.createStatement();
            rs = stm.executeQuery(query);
            while(rs.next()){
                Product p = new Product();
                p.setProductCode(rs.getString("productCode"));
                p.setProductName(rs.getString("productName"));
                p.setProductLine(rs.getString("productLine"));
                p.setProductScale(rs.getString("productScale"));
                p.setProductVendor(rs.getString("productVendor"));
                p.setProductDescription(rs.getString("productDescription"));
                p.setQuantityInStock(rs.getInt("quantityInStock"));
                p.setBuyPrice(rs.getDouble("buyPrice"));
                p.setMSRP(rs.getDouble("MSRP"));

                listProducts.add(p);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            closeConnections();
        }
        return listProducts;
    }

    public Product findByProductCode(String productCode){
        Product p = null;
        try{
            con = ConnectionDB.getConnection();
            query = "SELECT * FROM products WHERE productCode = ?";
            pstm = con.prepareStatement(query);
            pstm.setString(1, productCode);
            rs = pstm.executeQuery();
            if(rs.next()){
                p = new Product();
                p.setProductCode(rs.getString("productCode"));
                p.setProductName(rs.getString("productName"));
                p.setProductLine(rs.getString("productLine"));
                p.setProductScale(rs.getString("productScale"));
                p.setProductVendor(rs.getString("productVendor"));
                p.setProductDescription(rs.getString("productDescription"));
                p.setQuantityInStock(rs.getInt("quantityInStock"));
                p.setBuyPrice(rs.getDouble("buyPrice"));
                p.setMSRP(rs.getDouble("MSRP"));
            }
        }catch (SQLException | NullPointerException e){
            e.printStackTrace();
        }finally {
            closeConnections();
        }
        return p;
    }

    public boolean saveProduct(Product p, boolean isCreate){
        boolean state = false;
        try{
            con = ConnectionDB.getConnection();
            if(isCreate){
                query = "INSERT INTO products VALUES (?,?,?,?,?,?,?,?,?)";
                pstm = con.prepareStatement(query);
                pstm.setString(1, p.getProductCode());
                pstm.setString(2, p.getProductName());
                pstm.setString(3, p.getProductLine());
                pstm.setString(4, p.getProductScale());
                pstm.setString(5, p.getProductVendor());
                pstm.setString(6, p.getProductDescription());
                pstm.setInt(7, p.getQuantityInStock());
                pstm.setDouble(8, p.getBuyPrice());
                pstm.setDouble(9, p.getMSRP());
                state = pstm.executeUpdate() == 1;
            }else{
                query = "UPDATE products SET productName = ?, productLine = ?, productScale = ?, productVendor = ?, " +
                        "productDescription = ?, quantityInStock = ?, buyPrice = ?, MSRP = ? WHERE productCode = ?";
                pstm = con.prepareStatement(query);
                pstm.setString(9, p.getProductCode());
                pstm.setString(1, p.getProductName());
                pstm.setString(2, p.getProductLine());
                pstm.setString(3, p.getProductScale());
                pstm.setString(4, p.getProductVendor());
                pstm.setString(5, p.getProductDescription());
                pstm.setInt(6, p.getQuantityInStock());
                pstm.setDouble(7, p.getBuyPrice());
                pstm.setDouble(8, p.getMSRP());
                state = pstm.executeUpdate() == 1;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnections();
        }
        return state;
    }

    public boolean deleteProduct(String productCode){
        boolean state = false;
        try{
            con = ConnectionDB.getConnection();
            query = "DELETE FROM products WHERE productCode = ?;";
            pstm = con.prepareStatement(query);
            pstm.setString(1, productCode);
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
