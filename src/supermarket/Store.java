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
public class Store {

   public String storeName;
   public Vector<Department> v_department;
   public Vector<Customer> v_customers;
   
   private Store() {
    }
   
   private static Store instance = new Store();
   
   public static Store getInstance() {
	return instance;
    }
   
   public void initStore(String storeName){
      this.storeName=storeName;
      this.v_department = new Vector<Department>(4);
   }
   
   public void storeCustomers(int customersSize){
       this.v_customers = new Vector<Customer>(customersSize);
   }
   
   public boolean enter(Customer customer){
       if(v_customers.contains(customer))
           return false;
       else
           this.v_customers.add(customer);
       return true;
   }
   
   public void exit(Customer customer){
       this.v_customers.remove(customer);
   }
   
   public ShoppingCart getShoppingCart(Double budget){
       return new ShoppingCart(budget);
   }
   
   public Vector<Customer> getCustomers(){
       return v_customers;
   }
   
   public Vector<Department> getDepartments(){
       return v_department;
   }
   
   public boolean addDepartment(Department department){
       if(this.v_department.contains(department))
           return false;
       else
           this.v_department.add(department);
       return true;
   }
   
   public Department getDepartment(Integer Id){
       for(Department d : v_department)
           if(d.getId() == Id)
               return d;
       return null;
   }
     
}
