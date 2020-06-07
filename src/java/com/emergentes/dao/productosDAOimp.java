package com.emergentes.dao;

import com.emergentes.modelo.productos;
import com.emergentes.utiles.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class productosDAOimp extends conexion implements productosDAO{

    @Override
    public void insert(productos Productos) throws Exception {
       try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT into productos(descripcion, stock) values (?, ?)");
            ps.setString(1, Productos.getDescripcion());
            ps.setInt(2, Productos.getStock());
            ps.executeUpdate();
       }catch(Exception e){
           throw e;
       }finally{
           this.desconectar();
       }
    }

    @Override
    public void update(productos Productos) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE Productos set descripcion = ?, stock = ? where id = ?");
            ps.setString(1, Productos.getDescripcion());
            ps.setInt(2, Productos.getStock());
            ps.setInt(3, Productos.getId());
            ps.executeUpdate();
            
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
      try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM Productos WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
       }catch(Exception e){
           throw e;
       }finally{
           this.desconectar();
       }
    }

    @Override
    public productos getById(int id) throws Exception {
        productos avi = new productos();
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM Productos WHERE id = ? limit 1");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                avi.setId(rs.getInt("id"));
                avi.setDescripcion(rs.getString("descripcion"));
                avi.setStock(rs.getInt("stock"));
            }
       }catch(Exception e){
           throw e;
       }finally{
           this.desconectar();
       }
        return avi;
    }

    @Override
    public List<productos> getAll() throws Exception {
       List<productos> lista = null;
       try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM Productos");
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<productos>();
            while (rs.next()){
                productos avi = new productos();
                avi.setId(rs.getInt("id"));
                avi.setDescripcion(rs.getString("descripcion"));
                avi.setStock(rs.getInt("stock"));
                lista.add(avi);
            }
            rs.close();
            ps.close();
       }catch(Exception e){
           throw e;
       }finally{
           this.desconectar();
       }
        return lista;
       
    }
    
}
