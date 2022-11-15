package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Product;

public class ProductDao {
	
    private DataSource ds;
    	
    public ProductDao(DataSource ds) throws SQLException {
        this.ds = ds;
    }

   
    public List<Product> getProducts() throws SQLException {
        List<Product> products = new ArrayList<Product>();
        String sql = "SELECT productid, productname, quality, price FROM product";
        try (Connection conn = ds.getConnection()){
            try(PreparedStatement pstm = conn.prepareStatement(sql);
                       ResultSet rs = pstm.executeQuery()){
                while(rs.next()) {
                    Product p = new Product(rs.getInt(1), rs.getString(2), rs.getShort(3), rs.getDouble(4));
                    products.add(p);
                }
            }
        }
        return products;
    }
	
    
    public Product getProduct(int productid) throws SQLException {
        Product aproduct = null;
        String sql = "SELECT productid, productname, quality, price FROM product WHERE productid=?";
        try (Connection conn = ds.getConnection()){
            try(PreparedStatement pstm = conn.prepareStatement(sql)){
                pstm.setInt(1, productid);
                try(ResultSet rs = pstm.executeQuery()){
                    if (rs.next()) {
                        aproduct = new Product(rs.getInt(1), rs.getString(2), rs.getShort(3), rs.getDouble(4));
                    }
                }
            }
        }
        return aproduct;
    }
	
    
    public int insertProduct(Product aproduct) throws SQLException {
        String sql = "INSERT INTO product(productname, quality, price) VALUES(?, ?, ?)";
        int productid = 0;
        try (Connection conn = ds.getConnection()){
            try(PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                pstm.setString(1,  aproduct.getProductname());
                pstm.setShort(2, aproduct.getQuality());
                pstm.setDouble(3, aproduct.getPrice());
                pstm.executeUpdate();				
                try(ResultSet rs = pstm.getGeneratedKeys()){
                    if (rs.next()) {
                        productid = rs.getInt(1);
                    }	
                }					
            }
        }
        return productid;
    }
    
    public boolean deleteProduct(int productid) throws SQLException {
    	String sql = "DELETE FROM product WHERE productid=?";
        boolean rowDeleted;
        try (Connection conn = ds.getConnection()) {
        try(PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstm.setInt(1, productid);
            rowDeleted = pstm.executeUpdate() > 0;
        	}
        }
        return rowDeleted;
    }
    
    public boolean updateProduct(Product aproduct) throws SQLException {
    	String sql = "UPDATE product set productname=?, quality=?, price=? WHERE productid=?";
    	boolean rowUpdated;
    	try (Connection conn = ds.getConnection()){
    		try(PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
    			pstm.setString(1,  aproduct.getProductname());
                pstm.setShort(2, aproduct.getQuality());
                pstm.setDouble(3, aproduct.getPrice());
                pstm.setInt(4, aproduct.getProductid());
                
                rowUpdated = pstm.executeUpdate() > 0;
    		}
    	}
    	
    	return rowUpdated;
    }
}