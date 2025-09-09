/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bd;

/**
 *
 * @author leayi
 */
import java.sql.*; 
public class BasePersona {
    Connection conn;
    private static final String URL = "jdbc:mysql://localhost:3306/umg?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    BasePersona()
    {
        conectar();
    }
    
    Connection conectar ()
    {
          try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD); 
            System.out.println("✅ Conexión establecida");
            return conn;
          }
          catch (Exception ex)
          {
              System.out.println();
              return null;
          }
    }
    
    public boolean InsertarPersona(int id, String Nombre, String Apellido,int edad )
    {
        try {
                String sqlInsert = "INSERT INTO persona  VALUES (?, ?,?,?)";          
                PreparedStatement psInsert = conn.prepareStatement(sqlInsert);
                psInsert.setInt(1, id);
                psInsert.setString(2, Nombre);
                psInsert.setString(3, Apellido);
                psInsert.setInt(4, edad);
                psInsert.executeUpdate();
                System.out.println("✅ Registro insertado");
                return true;
            }
        catch (Exception ex)
        {
            System.out.print(ex.getMessage());
         return false;   
        }
        
    }
    public boolean ActualizarPersona(int id, String nombre, String apellido, int edad) {
    try {
        String sqlUpdate = "UPDATE persona SET nombre=?, apellido=?, edad=? WHERE id=?";
        PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
        psUpdate.setString(1, nombre);
        psUpdate.setString(2, apellido);
        psUpdate.setInt(3, edad);
        psUpdate.setInt(4, id);
        psUpdate.executeUpdate();
        System.out.println("Registro actualizado");
        return true;
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
        return false;
    }
}

public ResultSet BuscarPersona(int id) {
    try {
        String sql = "SELECT * FROM persona WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps.executeQuery();  
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
        return null;
    }
}

    public void RetornarPersona ()
    {
        try{
          String sqlSelect = "SELECT * FROM persona";
          Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sqlSelect);
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + " | " +
                                       rs.getString("nombre") + " | " +
                                       rs.getString("APELLIDO"));
                }
        }
        catch(Exception ex)
        {
            
        }
            
    }
    
}