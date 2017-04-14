/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

/**
 *
 * @author ioana
 */
public class StrategyB implements Strategy{

    @Override
    public Item execute(WishList wishList) {
        return wishList.getItem(0);
    }
    public String toString(){
        return "B";
    }
}
