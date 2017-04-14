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
public class StrategyC implements Strategy{

    @Override
    public Item execute(WishList wishList) {
        return wishList.last.lastElement();
    }
    public String toString(){
        return "C";
    }
}
