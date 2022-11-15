package controller;

import java.io.IOException;
import java.sql.SQLException;

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

/**
 * Servlet implementation class InsertBookServlet
 */
@WebServlet("/InsertProduct")
public class InsertProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/ExampleDB")
    private DataSource ds;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/insertproduct.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            ProductDao productdao = new ProductDao(ds);
            
            String productname = request.getParameter("productname");
            String sprice = request.getParameter("price"); // merkkijonona viel‰
            String squality = request.getParameter("quality");
            if (productname.trim().isEmpty() || sprice.trim().isEmpty() || squality.trim().isEmpty()) {
                request.setAttribute("error", "Fill in all fields!");
            } else {
                sprice = sprice.replace(',', '.'); //jos desimaalipilkku, korvataan pisteell‰
                @SuppressWarnings("deprecation")
                
				Double price = new Double(sprice);
                short quality = Short.parseShort(squality);
                Product aproduct = new Product(0, productname, quality, price);
                productdao.insertProduct(aproduct);
               
                response.sendRedirect("ProductManagement"); // lis‰ys onnistui, menn‰‰n alkuun
                return;
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Problems. Try again later.");
            e.printStackTrace();
        } catch (Exception e2) {
            request.setAttribute("error", "Check price and pages!");
        }

        // t‰nne tullaan jos ongelmia
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/insertproduct.jsp");
        rd.forward(request, response);
    }
}