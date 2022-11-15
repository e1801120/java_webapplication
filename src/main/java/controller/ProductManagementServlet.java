package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.Product;
import repository.ProductDao;


@WebServlet("/ProductManagement")
public class ProductManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
      
    @Resource(name="jdbc/ExampleDB")
    private DataSource ds;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ProductDao productdao = new ProductDao(ds);
            List<Product> products = productdao.getProducts();
            request.setAttribute("products", products);
            if (request.getParameter("choice") != null) {
                int productid = Integer.parseInt(request.getParameter("choice"));
                Product aproduct = productdao.getProduct(productid);
                request.setAttribute("aproduct", aproduct);									
            }
            
        } catch (SQLException e) {
            request.setAttribute("error", "Problems. Try again later.");
            e.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/products.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        
    }
}