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
public class Customer implements Observer {
    public String Name;
    public ShoppingCart cart ;
    public WishList wishList ;
    public ArrayList<Notification> notifications ;
    Strategy strategy;
    
    public Customer(String Name , double budget ,Strategy strategy){
        this.Name = Name;
        this.cart = new ShoppingCart(budget);
        this.wishList = new WishList(strategy);
        this.notifications = new ArrayList<Notification>();
        this.strategy = strategy;
        
    }

    @Override
    public void update(Notification notification) {
        Store store = Store.getInstance();
        
        switch(notification.notType){
            case ADD: 
                this.notifications.add(notification);
                break;
            case MODIFY: 
                this.notifications.add(notification);
                int index = 0;
                double beforePrice = 0;
                ListIterator<Item> it = this.cart.listIterator();
                while(it.hasNext()){
                    Item next = it.next();
                    if(next.getId() == notification.itemId){
                        for(int i = 0 ;i < store.v_department.size(); i++)
                            if(i+1 == notification.depId){
                                index = i;
                                break;
                            }
                        for(int i = 0; i < store.v_department.get(index).items.size(); i++)
                            if(store.v_department.get(index).items.get(i).getId() == notification.itemId){
                                next.setPrice(store.v_department.get(index).items.get(i).getPrice());
                                break;
                            }
                    }
                    
                }
                ListIterator<Item> wL = this.wishList.listIterator();
                while(wL.hasNext()){
                    Item next=wL.next();
                    if(next.getId() == notification.itemId){
                        for(int i = 0 ;i < store.v_department.size(); i++)
                            if(i+1 == notification.depId){
                                index = i;
                                break;
                            }
                        for(int i = 0; i < store.v_department.get(index).items.size(); i++)
                            if(store.v_department.get(index).items.get(i).getId() == notification.itemId){
                                next.setPrice(store.v_department.get(index).items.get(i).getPrice());
                                break;
                            }
                    }
                    
                }
                
                break;
            case REMOVE:
                this.notifications.add(notification);
                ListIterator<Item> rit = this.cart.listIterator();
                while(rit.hasNext()){
                    //Item del = rit.next();
                    if(rit.next().getId() == notification.itemId)
                        rit.remove();
                }
                ListIterator<Item> rwL = this.wishList.listIterator();
                while(rwL.hasNext()){
                    //Item del = rwL.next();
                    if(rwL.next().getId() == notification.itemId)
                        rwL.remove();
                }
                break;
        }
    }
}
