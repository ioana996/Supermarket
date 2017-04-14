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
public interface Subject {
    public void addObserver(Customer customer);
    public void removeObserver(Customer customer);
    public void notifyAllObservers(Notification notification);
    
}
