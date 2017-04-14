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
public abstract class Department implements Subject {
    public String depName;
    public ArrayList<Item> items;
    public ArrayList<Customer> clients;
    public ArrayList<Customer> observers;
    public Integer Id;
    
    public Department(String depName, Integer Id , int arraySize){
        this.depName = depName;
        this.Id = Id;
        this.items = new ArrayList<>(arraySize) ;
        this.clients=new ArrayList<>();
        this.observers = new ArrayList<>();
    }
    
    public boolean enter(Customer customer){
        for(Item i : items){
            if(customer.cart.contains(i)){
                if(!this.clients.contains(customer)){
                    clients.add(customer);
                    return true;
                }
            }
        }
        return false;
    }
    
    public void exit(Customer customer){
        for(Item i : items){
            if(customer.cart.contains(i)){
                return;
            }
        }
        Iterator<Customer> itr = clients.iterator();
        while(itr.hasNext()){
            if(itr.next().Name.equals(customer.Name))
                   itr.remove();
        }
    }
    
    public ArrayList<Customer> getCustomers(){
        return this.clients;
    }
    
    public Integer getId(){
        return this.Id;
    }
    
    public boolean addItem(Item item){
        if(this.items.contains(item))
            return false;
        else
            this.items.add(item);
        return true;
    }
    
    public ArrayList<Item> getItems(){
        return this.items;
    }
    
    @Override
    public void addObserver(Customer customer){
        for(Item i: this.items)
            if(customer.wishList.contains(i))
                if(!observers.contains(customer))
                    observers.add(customer);
        return;
    }
    
    @Override
    public void removeObserver(Customer customer){
        for(Item i: items)
            if(customer.wishList.contains(i))
                return;
        Iterator<Customer> itr = observers.iterator();
        while(itr.hasNext()){
            if(itr.next().Name.equals(customer.Name))
                   itr.remove();
        }
        //observers.remove(customer);
        //return true;
    }        
            
    @Override
    public void notifyAllObservers(Notification notification){
        for(Customer c : observers)
            c.update(notification);
    }
   
    public abstract void accept(ShoppingCart cart);
    
}
