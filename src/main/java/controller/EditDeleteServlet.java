package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.Product;
import repository.ProductDao;

/**
 * Bookshop home page
 */
@WebServlet("/EditDelete")
public class EditDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
      
    @Resource(name="jdbc/ExampleDB")
    private DataSource ds;

    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	String action = request.getParameter("action");
    	if(action == null) {
    		request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(request, response);
    	}
    	else {
    		HttpSession session = request.getSession();
    		
    		if(action.equalsIgnoreCase("EditDelete")) {	
    		
    			List<Product> editdelete = new ArrayList<Product>();
    			
    			if(session.getAttribute("editdelete") != null) {
    				editdelete = (List<Product>) session.getAttribute("editdelete");
    			}
    			
    			try {
    				ProductDao productdao = new ProductDao(ds);
    				
    				
    	            String productname = request.getParameter("productname");
    	            String sprice = request.getParameter("price"); // merkkijonona viel‰
    	            String squality = request.getParameter("quality");
    	            if (productname.trim().isEmpty() && sprice.trim().isEmpty() && squality.trim().isEmpty()) {
    	                request.setAttribute("error", "Fill in some of the fields!");
    	            }else {
    	                sprice = sprice.replace(',', '.'); //jos desimaalipilkku, korvataan pisteell‰
    	                @SuppressWarnings("deprecation")
    	                
    					Double price = new Double(sprice);
    	                short quality = Short.parseShort(squality);
    	                Product aproduct = new Product(0, productname, quality, price);
    	                productdao.updateProduct(aproduct);
    	               
    	                response.sendRedirect("ProductManagement"); // lis‰ys onnistui, menn‰‰n alkuun
    	                return;
    	            }
    				
    				
    			} catch (SQLException e) {
    				
    				e.printStackTrace();
    			}
    			
    			
    			
    		}
    		else if(action.equalsIgnoreCase("EditDelete")) {
    			session.removeAttribute(action);
    		}
    	}
    	
        request.getRequestDispatcher("ProductManagement").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(request, response);
    }
}