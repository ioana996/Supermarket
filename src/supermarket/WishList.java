/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;
import java.util.*;
/**
 *
 * @author ioana
 */
public class WishList extends ItemList{
    
    public Strategy strategy;
    public Vector<Item> last ;
    
    public WishList(Strategy strategy){
        super(new myWishListComparator());
        this.strategy = strategy;
        this.last = new Vector<Item>();
    }
    
    public void addItem(Item item){
        this.add(item);
        this.last.add(item);
    }
    
    public void delItem(Item item){
        this.remove(item);
    }

    public Item executeStrategy(){
        Item selected = strategy.execute(this);
        this.remove(selected);
        return selected;
    }
    
    @Override
    public Double getTotalPrice() {
        Double totalPrice = 0.0;
        for(int i = 0; i < this.size; i++){
            totalPrice = totalPrice + this.getItem(i).getPrice();
        }
        return totalPrice;
    }
    
}
