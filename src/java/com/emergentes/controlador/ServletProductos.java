package com.emergentes.controlador;

import com.emergentes.dao.productosDAO;
import com.emergentes.dao.productosDAOimp;
import com.emergentes.modelo.productos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletProductos", urlPatterns = {"/ServletProductos"})
public class ServletProductos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try{
            productosDAO dao = new productosDAOimp();
            int id;
            productos avi = new productos();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            
            switch(action){
                case "add":
                    request.setAttribute("Productos", avi);
                    request.getRequestDispatcher("frmproductos.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    avi = dao.getById(id);
                    System.out.println(avi);
                    request.setAttribute("Productos", avi);
                    request.getRequestDispatcher("frmproductos.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath()+"/ServletProductos");
                    break;
                case "view":
                    List<productos> lista = dao.getAll();
                    request.setAttribute("Productos", lista);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                default:    
                    break;
            }
        
        } catch (Exception ex){
            System.out.println("Error " + ex.getMessage());
        }
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String descripcion = request.getParameter("descripcion");
        int stock = Integer.parseInt(request.getParameter("stock"));
        
        productos avi = new productos();
        
        avi.setId(id);
        avi.setDescripcion(descripcion);
        avi.setStock(stock);
        
        if (id == 0){
            try{
                productosDAO dao = new productosDAOimp();
                dao.insert(avi);
                response.sendRedirect(request.getContextPath()+"/ServletProductos");
            
            } catch (Exception ex){
                System.out.println("Error" + ex.getMessage());
            }        
        }
        else{
            try {
                productosDAO dao = new productosDAOimp();
                dao.update(avi);
                response.sendRedirect(request.getContextPath()+"/ServletProductos");
            } catch (Exception ex){
                System.out.println("Error" + ex.getMessage());
            }          
            
        }        
    }
}
