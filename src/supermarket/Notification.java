/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author ioana
 */
public class Notification {
    
    //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
   // Date date = new Date();
    public enum NotificationType{ADD , REMOVE , MODIFY};
    public Integer depId;
    public Integer itemId;
    public NotificationType notType;
    Date date;
    
    public Notification(Integer depId , Integer itemId , NotificationType notType){
        this.date = new Date();
        this.depId = depId;
        this.itemId = itemId;
        this.notType = notType;
    }
    
}
