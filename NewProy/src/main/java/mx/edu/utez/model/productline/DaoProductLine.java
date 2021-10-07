package mx.edu.utez.model.productline;

import mx.edu.utez.database.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//ANGEL YAZVECK ALCOCER DURÁN 4B DSM

public class DaoProductLine {

    Connection con;
    PreparedStatement pstm;
    ResultSet rs;
    Statement stm;
    String query;

    public List<ProductLine> findAll(){
        List<ProductLine> listProductLines = new ArrayList();
        try{
            con = ConnectionDB.getConnection();
            query = "SELECT * FROM productlines;";
            stm = con.createStatement();
            rs = stm.executeQuery(query);
            while(rs.next()){
                ProductLine p = new ProductLine();
                p.setProductLine(rs.getString("productLine"));
                p.setTextDescription(rs.getString("textDescription"));

                listProductLines.add(p);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            closeConnections();
        }
        return listProductLines;
    }

    public ProductLine findByProductLine(String productLine){
        ProductLine p = null;
        try{
            con = ConnectionDB.getConnection();
            query = "SELECT * FROM productlines WHERE productLine = ?";
            pstm = con.prepareStatement(query);
            pstm.setString(1, productLine);
            rs = pstm.executeQuery();
            if(rs.next()){
                p = new ProductLine();
                p.setProductLine(rs.getString("productLine"));
                p.setTextDescription(rs.getString("textDescription"));
            }
        }catch (SQLException | NullPointerException e){
            e.printStackTrace();
        }finally {
            closeConnections();
        }
        return p;
    }

    public boolean saveProductLine(ProductLine p, boolean isCreate){
        boolean state = false;
        try{
            con = ConnectionDB.getConnection();
            if(isCreate){
                query = "INSERT INTO productlines VALUES (?,?)";
                pstm = con.prepareStatement(query);
                pstm.setString(1, p.getProductLine());
                pstm.setString(2, p.getTextDescription());
                state = pstm.executeUpdate() == 1;
            }else{
                query = "UPDATE productlines SET textDescription = ? WHERE productLine = ?;";
                pstm = con.prepareStatement(query);
                pstm.setString(2, p.getProductLine());
                pstm.setString(1, p.getTextDescription());
                state = pstm.executeUpdate() == 1;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnections();
        }
        return state;
    }

    public boolean deleteProductLine(String productLine){
        boolean state = false;
        try{
            con = ConnectionDB.getConnection();
            query = "DELETE FROM productlines WHERE productLine = ?;";
            pstm = con.prepareStatement(query);
            pstm.setString(1, productLine);
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
