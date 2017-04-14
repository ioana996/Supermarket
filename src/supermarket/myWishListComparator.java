/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import java.util.Comparator;

/**
 *
 * @author ioana
 */
public class myWishListComparator implements Comparator<Item>{

    @Override
    public int compare(Item item1, Item item2) {
       return item1.getName().compareTo(item2.getName());
    }
    
}
