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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import model.User;
import repository.UserDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "jdbc/ExampleDB")
    private DataSource ds;
	
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
    }


	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            UserDao userdao = new UserDao(ds);
            String username = request.getParameter("username");
            String name = request.getParameter("name"); // merkkijonona vielä
            String password = request.getParameter("password");
            String password2 = request.getParameter("password2");
            
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String salattu = passwordEncoder.encode(password);
            
            
            if (username.trim().isEmpty() || name.trim().isEmpty() || password.trim().isEmpty() || password2.trim().isEmpty()) {
                request.setAttribute("error", "Fill in all fields!");
            } else if(!password.equals(password2)) {
            	request.setAttribute("error", "Passwords don't match!");
            }
            else {
                User user= new User(username, name, salattu);
                userdao.registerUser(user);
                response.sendRedirect("Login"); // 
                return;
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Problems. Try again later.");
            e.printStackTrace();
        } catch (Exception e2) {
            request.setAttribute("error", "Check fields!");
        }

        // tänne tullaan jos ongelmia
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
        rd.forward(request, response);
    }

}
