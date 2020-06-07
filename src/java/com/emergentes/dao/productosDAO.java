
package com.emergentes.dao;

import com.emergentes.modelo.productos;
import java.util.List;

public interface productosDAO {
    public void insert(productos Productos) throws Exception;
    public void update(productos Productos) throws Exception;
    public void delete(int id) throws Exception;
    public productos getById(int id) throws Exception;
    public List<productos> getAll() throws Exception;
}
