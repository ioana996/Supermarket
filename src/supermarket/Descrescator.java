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
public class Descrescator implements Comparator<Item>{

    @Override
    public int compare(Item o1, Item o2) {
        if(o1.getPrice()-o2.getPrice()<0)
            return 1;
        else if(o1.getPrice()-o2.getPrice()>0)
            return -1;
        return 0;
    }
    
}
