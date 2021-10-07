package mx.edu.utez.model.office;

import mx.edu.utez.database.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//ANGEL YAZVECK ALCOCER DURÁN 4B DSM

public class DaoOffice {

    Connection con;
    PreparedStatement pstm;
    ResultSet rs;
    Statement stm;
    String query;

    public List<Office> findAll(){
        List<Office> listOffices = new ArrayList();
        try{
            con = ConnectionDB.getConnection();
            query = "SELECT * FROM offices;";
            stm = con.createStatement();
            rs = stm.executeQuery(query);
            while(rs.next()){
                Office o = new Office();
                o.setOfficeCode(rs.getString("officeCode"));
                o.setTerritory(rs.getString("territory"));
                o.setPhone(rs.getString("phone"));
                o.setAddressLine1(rs.getString("addressLine1"));
                o.setAddressLine2(rs.getString("addressLine2"));
                o.setCity(rs.getString("city"));
                o.setState(rs.getString("state"));
                o.setPostalCode(rs.getString("postalCode"));
                o.setCountry(rs.getString("country"));

                listOffices.add(o);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            closeConnections();
        }
        return listOffices;
    }

    public Office findByOfficeCode(String officeCode){
        Office o = null;
        try{
            con = ConnectionDB.getConnection();
            query = "SELECT * FROM offices WHERE officeCode = ?";
            pstm = con.prepareStatement(query);
            pstm.setString(1, officeCode);
            rs = pstm.executeQuery();
            if(rs.next()){
                o = new Office();
                o.setOfficeCode(rs.getString("officeCode"));
                o.setTerritory(rs.getString("territory"));
                o.setPhone(rs.getString("phone"));
                o.setAddressLine1(rs.getString("addressLine1"));
                o.setAddressLine2(rs.getString("addressLine2"));
                o.setCity(rs.getString("city"));
                o.setState(rs.getString("state"));
                o.setPostalCode(rs.getString("postalCode"));
                o.setCountry(rs.getString("country"));
            }
        }catch (SQLException | NullPointerException e){
            e.printStackTrace();
        }finally {
            closeConnections();
        }
        return o;
    }

    public boolean saveOffice(Office o, boolean isCreate){
        boolean state = false;
        try{
            con = ConnectionDB.getConnection();
            if(isCreate){
                query = "INSERT INTO offices VALUES (?,?,?,?,?,?,?,?,?)";
                pstm = con.prepareStatement(query);
                pstm.setString(1, o.getOfficeCode());
                pstm.setString(2, o.getCity());
                pstm.setString(3, o.getPhone());
                pstm.setString(4, o.getAddressLine1());
                pstm.setString(5, o.getAddressLine2());
                pstm.setString(6, o.getState());
                pstm.setString(7, o.getCountry());
                pstm.setString(8, o.getPostalCode());
                pstm.setString(9, o.getTerritory());
                state = pstm.executeUpdate() == 1;
            }else{
                query = "UPDATE offices SET city = ?, phone = ?, addressLine1 = ?, addressLine2 = ?, " +
                        "state = ?, country = ?, postalCode = ?, territory = ? WHERE officeCode = ?";
                pstm = con.prepareStatement(query);
                pstm.setString(9, o.getOfficeCode());
                pstm.setString(1, o.getCity());
                pstm.setString(2, o.getPhone());
                pstm.setString(3, o.getAddressLine1());
                pstm.setString(4, o.getAddressLine2());
                pstm.setString(5, o.getState());
                pstm.setString(6, o.getCountry());
                pstm.setString(7, o.getPostalCode());
                pstm.setString(8, o.getTerritory());
                state = pstm.executeUpdate() == 1;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnections();
        }
        return state;
    }

    public boolean deleteOffice(String officeCode){
        boolean state = false;
        try{
            con = ConnectionDB.getConnection();
            query = "DELETE FROM offices WHERE officeCode = ?;";
            pstm = con.prepareStatement(query);
            pstm.setString(1, officeCode);
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
