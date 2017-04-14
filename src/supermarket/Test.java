/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import supermarket.Notification.NotificationType;
/**
 *
 * @author ioana
 */
public class Test {
    public static void main(String args[]){
        
        String storeFile = "store.txt";
        String customersFile = "customers.txt";
        String eventsFile = "events.txt";
        Store store = Store.getInstance();
        NumberFormat formatter = new DecimalFormat("#0.00");
        String line;
        int i;
        String storeName;
        FileReader fr = null;
        LineNumberReader lnr = null;
        int contor ;
        BookDepartment bookDep;
        MusicDepartment musicDep;
        SoftwareDepartment softwareDep;
        VideoDepartment videoDep;
        int nrProd = 0;
        int j;
        Item item;
        int depNr = 0;
        Customer customer = null;
        String depName = "";
        Integer depId = 0;
        int nrCom = 0;
        String nameCom = "";
        int itemComId = 0;
        String listName = "";
        String custName = "";
        int departId = 0;
        double priceCom = 0.0;
        String itName = "";
        double oldPrice = 0.0;
        Item auxItem = null;
        Customer cust = null;
        
        try{
            fr = new FileReader(storeFile);
            lnr = new LineNumberReader(fr);
            while((line = lnr.readLine()) != null){
                contor=0;
                i = lnr.getLineNumber();
                StringTokenizer st = new StringTokenizer(line , ";");
                while(st.hasMoreElements()){
                    if(i == 1){
                        storeName = st.nextElement().toString();
                        store.initStore(storeName);
                    }else{
                        contor++;
                        st.nextElement();
                    }
                }
                if(contor != 0){
                    StringTokenizer aux = new StringTokenizer(line , ";");
                    while(aux.hasMoreElements()){
                        switch(contor){
                            case 1: 
                                nrProd = Integer.parseInt(aux.nextElement().toString());
                                if(depName.equals("BookDepartment")){
                                    bookDep = new BookDepartment(depName , depId , nrProd);
                                    store.addDepartment(bookDep);
                                }
                                else if(depName .equals("MusicDepartment")){
                                    musicDep = new MusicDepartment(depName , depId , nrProd);
                                    store.addDepartment(musicDep);
                                }
                                else if(depName .equals("SoftwareDepartment")){
                                    softwareDep = new SoftwareDepartment(depName , depId , nrProd);
                                    store.addDepartment(softwareDep);
                                }
                                else if(depName .equals("VideoDepartment")){
                                    videoDep = new VideoDepartment(depName , depId , nrProd);
                                    store.addDepartment(videoDep);
                                }
                                break;
                            case 2:
                                depNr++;
                                depName = aux.nextElement().toString();
                                depId = Integer.parseInt(aux.nextElement().toString());
                                break;
                            case 3:
                                String itemName = aux.nextElement().toString();
                                int itemId = Integer.parseInt(aux.nextElement().toString());
                                double itemPrice = Double.parseDouble(aux.nextElement().toString());
                                item = new Item(itemName, itemId , itemPrice);
                                store.v_department.get(depNr-1).addItem(item);
                                break;
                        }
                        
                    }
                }
                    
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(lnr != null)
                    lnr.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        i = 0;
        try{
            fr = new FileReader(customersFile);
            lnr = new LineNumberReader(fr);
            while((line = lnr.readLine()) != null){
                i = lnr.getLineNumber();
                StringTokenizer st = new StringTokenizer(line , ";");
                while(st.hasMoreElements()){
                    if(i == 1){
                        int customersSize = Integer.parseInt(st.nextElement().toString());
                        store.storeCustomers(customersSize);
                    }else{
                        String customerName = st.nextElement().toString();
                        double customerBudget = Double.parseDouble(st.nextElement().toString());
                        String strategie = st.nextElement().toString();
                        if(strategie.equals("A"))
                            customer = new Customer(customerName , customerBudget, new StrategyA());
                        else if(strategie.equals("B"))
                            customer = new Customer(customerName , customerBudget, new StrategyB());
                        else if(strategie.equals("C"))
                            customer = new Customer(customerName , customerBudget, new StrategyC());
                        store.enter(customer);
                    }
                }
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(lnr != null)
                    lnr.close();
            }catch(IOException e){
                        e.printStackTrace();
            }
            
        }
        i = 0;
        try{
            fr = new FileReader(eventsFile);
            lnr = new LineNumberReader(fr);
            while((line = lnr.readLine()) != null){
                i = lnr.getLineNumber();
                StringTokenizer st = new StringTokenizer(line , ";");
                while(st.hasMoreElements()){
                if(i == 1)
                    nrCom = Integer.parseInt(st.nextElement().toString());
                else{
                    nameCom = st.nextElement().toString();
                    switch(nameCom){
                        case "addItem":
                            itemComId = Integer.parseInt(st.nextElement().toString());
                            listName = st.nextElement().toString();
                            custName = st.nextElement().toString();
                            departId = Integer.parseInt(Integer.toString(itemComId).substring(0, 1));
                            for(Item itm : store.v_department.get(departId-1).items)
                                if(itm.getId() == itemComId){
                                    for(Customer c :store.v_customers )
                                        if(c.Name.equals(custName)){
                                            if(listName.equals("ShoppingCart")){
                                                c.cart.addItem(itm);
                                                store.v_department.get(departId-1).enter(c);
                                            }else if(listName.equals("WishList")){
                                                c.wishList.addItem(itm);
                                                store.v_department.get(departId-1).addObserver(c);
                                            }
                                        }
                                }
                            break;
                        case "delItem":
                            itemComId = Integer.parseInt(st.nextElement().toString());
                            listName = st.nextElement().toString();
                            custName = st.nextElement().toString();
                            departId = Integer.parseInt(Integer.toString(itemComId).substring(0, 1));
                            for(Item itm : store.v_department.get(departId-1).items)
                                if(itm.getId() == itemComId){
                                    /*for(Customer c : store.v_department.get(departId-1).clients)
                                        if(c.Name.equals(custName)){
                                            if(listName.equals("ShoppingCart")){
                                                c.cart.delItem(itm);
                                                store.v_department.get(departId-1).exit(c);
                                            }else if(listName.equals("WishList")){
                                                c.wishList.delItem(itm);
                                                store.v_department.get(departId-1).removeObserver(c);
                                            }
                                        }*/
                                   /* Iterator<Customer> itr = store.v_department.get(departId-1).clients.iterator();
                                    while(itr.hasNext()){
                                        cust = itr.next();
                                        if(cust.Name.equals(custName)){
                                            if(listName.equals("ShoppingCart")){
                                                cust.cart.delItem(itm);
                                                if(store.v_department.get(departId-1).exit(cust))
                                                    itr.remove();
                                            }else if(listName.equals("WishList")){
                                                cust.wishList.delItem(itm);
                                                store.v_department.get(departId-1).removeObserver(cust);
                                            }
                                        }
                                    }*/
                                    
                                    for(Customer c : store.v_customers)
                                        if(c.Name.equals(custName)){
                                            if(listName.equals("ShoppingCart")){
                                                c.cart.delItem(itm);
                                                store.v_department.get(departId-1).exit(c);
                                            }else if(listName.equals("WishList")){
                                                c.wishList.delItem(itm);
                                                store.v_department.get(departId-1).removeObserver(c);
                                            }
                                        }
                                }
                            break;
                        case "addProduct":
                            departId = Integer.parseInt(st.nextElement().toString());
                            itemComId = Integer.parseInt(st.nextElement().toString());
                            priceCom = Double.parseDouble(st.nextElement().toString());
                            itName = st.nextElement().toString();
                            Item item2 = new Item(itName , itemComId , priceCom);
                            store.v_department.get(departId-1).addItem(item2);
                            store.v_department.get(departId-1).notifyAllObservers(new Notification(departId , itemComId , NotificationType.ADD));
                            break;
                        case "modifyProduct":
                            departId = Integer.parseInt(st.nextElement().toString());
                            itemComId = Integer.parseInt(st.nextElement().toString());
                            priceCom = Double.parseDouble(st.nextElement().toString());
                            for(Item itm : store.v_department.get(departId-1).items)
                                if(itm.getId() == itemComId){
                                    oldPrice = itm.getPrice();
                                    itm.setPrice(priceCom);
                                }
                            store.v_department.get(departId-1).notifyAllObservers(new Notification(departId , itemComId , NotificationType.MODIFY));
                            for(Customer c : store.v_department.get(departId-1).clients)
                                for(int l = 0; l < c.cart.size; l++)
                                    if(c.cart.getItem(l).getId() == itemComId){
                                        c.cart.budget = c.cart.budget - oldPrice + priceCom;
                                }
                            break;
                        case "delProduct":
                            itemComId = Integer.parseInt(st.nextElement().toString());
                            departId = Integer.parseInt(Integer.toString(itemComId).substring(0, 1));
                            for(Item itm : store.v_department.get(departId-1).items){
                                if(itm.getId() == itemComId)
                                    auxItem = itm;;
                            }
                            for(Customer c : store.v_customers){
                                if(c.cart.contains(auxItem)){
                                    c.cart.delItem(auxItem);
                                    store.v_department.get(departId-1).exit(c);
                                }
                                if(c.wishList.contains(auxItem)){
                                    c.wishList.delItem(auxItem);
                                    store.v_department.get(departId-1).removeObserver(c);
                                }
                            }
                            store.v_department.get(departId-1).items.remove(auxItem);
                            store.v_department.get(departId-1).notifyAllObservers(new Notification(departId , itemComId , NotificationType.REMOVE));
                            break;
                        case "getItem": 
                            custName = st.nextElement().toString();
                            for(Customer c:store.v_customers){
                                if(c.Name.equals(custName)){
                                    Item itm = c.wishList.executeStrategy();
                                    c.cart.addItem(itm);
                                    departId = departId = Integer.parseInt(Integer.toString(itm.getId()).substring(0, 1));
                                    store.v_department.get(departId-1).removeObserver(c);
                                    System.out.println(itm.getName()+";"+itm.getId()+";"+formatter.format(Math.round( itm.getPrice() * 100.0 ) / 100.0));
                                }
                            }
                            break;
                        case "getItems":
                            listName = st.nextElement().toString();
                            custName = st.nextElement().toString();
                                for(Customer c : store.v_customers){
                                    if(c.Name.equals(custName)){
                                        if(listName.equals("ShoppingCart")){
                                            for(int l = 0; l < c.cart.size; l++){
                                                if(c.cart.size == 0)
                                                    System.out.println("[]");
                                                else if(c.cart.size == 1)
                                                    System.out.println("["+c.cart.getItem(l).getName()+";"+c.cart.getItem(l).getId()+";"+formatter.format(Math.round( c.cart.getItem(l).getPrice() * 100.00 ) / 100.0)+"]");
                                                else if(c.cart.size != 1){
                                                    if(l == 0)
                                                        System.out.print("["+c.cart.getItem(l).getName()+";"+c.cart.getItem(l).getId()+";"+formatter.format(Math.round( c.cart.getItem(l).getPrice() * 100.0 ) / 100.0)+", ");
                                                    else if(l == c.cart.size-1) 
                                                        System.out.print(c.cart.getItem(l).getName()+";"+c.cart.getItem(l).getId()+";"+formatter.format(Math.round( c.cart.getItem(l).getPrice() * 100.0 ) / 100.0)+"]"+"\n");
                                                    else
                                                        System.out.print(c.cart.getItem(l).getName()+";"+c.cart.getItem(l).getId()+";"+formatter.format(Math.round( c.cart.getItem(l).getPrice() * 100.0 ) / 100.0)+", ");
                                                }
                                            }
                                        }
                                         else if(listName.equals("WishList")){
                                            for(int l = 0; l < c.wishList.size; l++){
                                                if(c.wishList.size == 0)
                                                    System.out.println("[]");
                                                else if(c.wishList.size == 1)
                                                    System.out.println("["+c.wishList.getItem(l).getName()+";"+c.wishList.getItem(l).getId()+";"+formatter.format(Math.round( c.wishList.getItem(l).getPrice() * 100.0 ) / 100.0)+"]");
                                                else if(c.wishList.size != 1){
                                                    if(l == 0)
                                                        System.out.print("["+c.wishList.getItem(l).getName()+";"+c.wishList.getItem(l).getId()+";"+formatter.format(Math.round( c.wishList.getItem(l).getPrice() * 100.0 ) / 100.0)+", ");
                                                    else if(l == c.wishList.size-1)
                                                        System.out.print(c.wishList.getItem(l).getName()+";"+c.wishList.getItem(l).getId()+";"+formatter.format(Math.round( c.wishList.getItem(l).getPrice() * 100.0 ) / 100.0)+"]"+"\n");                                                     
                                                         else
                                                        System.out.print(c.wishList.getItem(l).getName()+";"+c.wishList.getItem(l).getId()+";"+formatter.format(Math.round( c.wishList.getItem(l).getPrice() * 100.0 ) / 100.0)+", ");
                                                }
                                            }
                                        }
                                    }
                                }
                                break;
                        case "getTotal":
                            listName = st.nextElement().toString();
                            custName = st.nextElement().toString();
                            for(Customer c : store.v_customers){
                                    if(c.Name.equals(custName)){
                                        if(listName.equals("ShoppingCart")){
                                            //System.out.println(df.format(Math.round(c.cart.getTotalPrice() * 100.0 ) / 100.0));
                                            System.out.println(Math.round(c.cart.getTotalPrice() * 100.0 ) / 100.0);
                                        }
                                        else if(listName.equals("WishList")){
                                            //System.out.println(df.format(Math.round( c.wishList.getTotalPrice() * 100.0 ) / 100.0));
                                            System.out.println(Math.round( c.wishList.getTotalPrice() * 100.0 ) / 100.0);
                                        }
                                    }
                            }
                            break;
                        case "accept":
                            departId = Integer.parseInt(st.nextElement().toString());
                            custName = st.nextElement().toString();
                            for(Customer c : store.v_customers)
                                if(c.Name.equals(custName))
                                    store.v_department.get(departId-1).accept(c.cart);
                            break;
                        case "getObservers":
                            departId = Integer.parseInt(st.nextElement().toString());
                            System.out.print("[");
                            for(int l = 0; l < store.v_department.get(departId-1).observers.size(); l++)
                                if(l != store.v_department.get(departId-1).observers.size()-1)
                                    System.out.print(store.v_department.get(departId-1).observers.get(l).Name+", ");
                                else
                                    System.out.print(store.v_department.get(departId-1).observers.get(l).Name);
                                System.out.print("]"+"\n");
                            break;
                        case "getNotifications":
                            custName = st.nextElement().toString();
                            System.out.print("[");
                            for(int l = 0; l < store.v_customers.size(); l++)
                                if(store.v_customers.get(l).Name.equals(custName))
                                    for(int z=0 ;z<store.v_customers.get(l).notifications.size();z++){
                                        Notification n = store.v_customers.get(l).notifications.get(z);
                                        if(z != store.v_customers.get(l).notifications.size()-1)
                                            System.out.print(n.notType+";"+n.itemId+";"+n.depId+", ");
                                        else
                                            System.out.print(n.notType+";"+n.itemId+";"+n.depId);
                                    }
                            System.out.print("]"+"\n");
                            break;
                    }
                }
                }      
           }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(lnr != null)
                    lnr.close();
            }catch(IOException e){
                        e.printStackTrace();
            }
            
        }
        
    }
    
}
