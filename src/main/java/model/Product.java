package model;

import java.io.Serializable;

public class Product implements Serializable {


    private static final long serialVersionUID = 1L;
	
    private int productid;
    private double price;
    private String productname;
    private short quality;
    private int quantity = 0;
    
    

    public Product() {
    }
	
    public Product(int productid, String productname, short quality, double price) {
        this.productid = productid;
        this.price = price;
        this.productname = productname;
        this.quality = quality;
        
    }

    public int getProductid() {
        return this.productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductname() {
        return this.productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public short getQuality() {
        return this.quality;
    }

    public void setQuality(short quality) {
        this.quality = quality;
    }
    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}