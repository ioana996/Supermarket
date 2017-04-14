/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

/**
 *
 * @author ioana
 */
public class Item {
    private String name;
    private int Id;
    private double price;
    
    public Item(String name , int Id , double price){
        this.name = name;
        this.Id = Id;
        this.price = price;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getId(){
        return this.Id;   
    }
    
    public double getPrice(){
        return this.price;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setId(int Id){
        this.Id = Id;
    }
    
    public void setPrice(double price){
        this.price = price;
    }
}
