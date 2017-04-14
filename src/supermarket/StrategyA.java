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
public class StrategyA implements Strategy{

    /**
     *
     * @param wishList
     * @return
     */
    @Override
    public Item execute(WishList wishList) {
        double minPrice = wishList.getItem(0).getPrice();
        Item item = wishList.getItem(0);
        Item aux;
        ListIterator<Item> wIt = wishList.listIterator();
        while(wIt.hasNext()){
            aux = wIt.next();
            double price = aux.getPrice();
            if(minPrice > price){
                minPrice = price;
                item = aux;
            }
        }
        return item;
    }
    
    public String toString(){
        return "A";
    }
}
