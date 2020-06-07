package com.emergentes.utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/bd_almacen";
    static String usuario = "root";
    static String password = "";
    
    protected Connection conn = null;
    public conexion() throws ClassNotFoundException{
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, password);
            if (conn != null){
                System.out.println("Conexion OK :" +conn);
            } 
        } catch (SQLException ex){
            System.out.println("Error de SQL: " + ex.getMessage());
        }   
    }
    
    public Connection conectar()
    {
        return conn;
    }
    
    public void desconectar()
    {
        System.out.println("Cerrando la BD :" +conn);
        try {
            conn.close();
        } catch (SQLException ex){
            System.out.println("Error de SQL: " + ex.getMessage());
        }
    }    
}
