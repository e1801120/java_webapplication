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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import model.User;
import repository.UserDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/ExampleDB")
    private DataSource ds;
       
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            UserDao userdao = new UserDao(ds);
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            User user = userdao.loginUser(username);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            
            
            
            if (username.trim().isEmpty() || password.trim().isEmpty()) {
                request.setAttribute("error", "Fill in all fields!");
            } 
            
            else if (user != null && passwordEncoder.matches(password, user.getPassword())) {  
            	HttpSession session = request.getSession();
            	session.setAttribute("user", user);
            	response.sendRedirect("ProductManagement"); // lis‰ys onnistui, menn‰‰n alkuun
                
            	System.out.println("Correct password!");
            	
            	return;
            	
            	}
            
            else {  request.setAttribute("error" , "Wrong username or password!"); 
            }
            
            
        } catch (SQLException e) {
            request.setAttribute("error", "Problems. Try again later.");
            e.printStackTrace();
        } catch (Exception e2) {
            request.setAttribute("error", "Check fields!");
        }

        // t‰nne tullaan jos ongelmia
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        rd.forward(request, response);
    }
}
