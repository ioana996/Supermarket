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
public class myShoppingComparator implements Comparator<Item>{
        @Override
        public int compare(Item item1, Item item2) {
            if(item1.getPrice() - item2.getPrice() > 0.0)
                return 1;
            else if(item1.getPrice() - item2.getPrice() < 0.0)
                return -1;
            //else if(item1.getPrice() - item2.getPrice() == 0.0){
                return item1.getName().compareTo(item2.getName());
           // }
           // return 0;
        }
    }
