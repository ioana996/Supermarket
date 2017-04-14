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
public class ShoppingCart extends ItemList implements Visitor {
    
    public Double budget;
    
    public ShoppingCart(Double budget){
        super(new myShoppingComparator());
        this.budget = budget;
    }
    
    public void addItem(Item item){
        if(item.getPrice() <= this.budget){
            this.add(item);
            this.budget = this.budget - item.getPrice();
        }
    }
    
    public void delItem(Item item){
        if(!this.isEmpty()){
            this.remove(item);
            this.budget = this.budget + item.getPrice();
        }
    }
    
    @Override
    public void visit(BookDepartment bookDepartment) {
        for(Item i : bookDepartment.items){
            if(this.contains(i)){
                this.getItem(this.indexOf(i)).setPrice(i.getPrice() - 0.1 * i.getPrice());
                this.budget = this.budget + 0.1 * i.getPrice();
            }
        }
        
    }

    @Override
    public void visit(MusicDepartment musicDepartment) {
        double addSum = 0;
        for(Item i : musicDepartment.items){
            if(this.contains(i))
                addSum = addSum + i.getPrice();
        }
        this.budget = this.budget + 0.1 * addSum;
    }

    @Override
    public void visit(SoftwareDepartment softwareDepartment) {
        double min = softwareDepartment.items.get(0).getPrice();
        double sum = 0;
        for(Item i : softwareDepartment.items){
            if(i.getPrice() < min)
                min = i.getPrice();
        }
        for(int i = 0; i<this.size; i++)
            sum = sum +this.getItem(i).getPrice();
        if(this.budget - sum < min){
            for(Item i : softwareDepartment.items){
                if(this.contains(i)){
                    this.getItem(this.indexOf(i)).setPrice(i.getPrice() - 0.2 * i.getPrice());
                    this.budget = this.budget + 0.2 * i.getPrice();
                }
            }
        }
    }

    @Override
    public void visit(VideoDepartment videoDepartment) {
        double max = videoDepartment.items.get(0).getPrice();
        double sum = 0;
        for(Item i : videoDepartment.items){
            if(i.getPrice() > max)
                max = i.getPrice();
        }
        for(Item i : videoDepartment.items){
            if(this.contains(i)){
                sum = sum + i.getPrice();
            }
        }
        this.budget = this.budget + 0.05 * sum;
        if(sum > max){
        for(Item i : videoDepartment.items){
            if(this.contains(i)){
                    this.getItem(this.indexOf(i)).setPrice(i.getPrice() - 0.15 * i.getPrice());
                    this.budget = this.budget + 0.15 * i.getPrice();
            }
        }
        }
    }
    @Override
    public Double getTotalPrice(){
        Double totalPrice = 0.0;
        for(int i = 0; i < this.size; i++){
            totalPrice = totalPrice + this.getItem(i).getPrice();
        }
        return totalPrice;
    }
 
}
