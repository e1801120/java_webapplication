package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

import model.User;


public class UserDao {

	private DataSource ds;
	
    public UserDao(DataSource ds) throws SQLException {
        this.ds = ds;
    }
    
    
	
    public void registerUser(User user) throws SQLException {
        String sql = "INSERT INTO user(username, name, password, rolename) VALUES(?, ?, ?, ?)";
        
        try (Connection conn = ds.getConnection()){
            try(PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                pstm.setString(1,  user.getUsername());
                pstm.setString(2, user.getName());
                pstm.setString(3, user.getPassword());
                pstm.setString(4, user.getRole());
                pstm.executeUpdate();				
                    }	
                }					
            System.out.println("Register successfull!!!");
    }
    
    public User loginUser(String username) throws SQLException {
        User user = null;
        String sql = "SELECT username, name, password, rolename " + "FROM user WHERE username=?";
        try (Connection conn = ds.getConnection()){
            try(PreparedStatement pstm = conn.prepareStatement(sql)){
                pstm.setString(1, username);
                try(ResultSet rs = pstm.executeQuery()){
                    if (rs.next()) {
                        user = new User(rs.getString(1), rs.getString(2), rs.getString(3));
                    }
                }
            }
            
        }
        
        return user;
    }
    
}
