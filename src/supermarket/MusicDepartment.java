/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import java.util.ArrayList;

/**
 *
 * @author ioana
 */
public class MusicDepartment extends Department{
    
    public MusicDepartment(String depName , Integer Id , int arraySize){
        super(depName , Id , arraySize);
    }

    /**
     *
     * @param cart
     */
    @Override
    public void accept(ShoppingCart cart) {
        cart.visit(this);
    }

    
}
