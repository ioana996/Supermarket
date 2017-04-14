/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.StringTokenizer;
import javax.swing.*;
import java.util.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import supermarket.Notification.NotificationType;
/**
 *
 * @author ioana
 */
class ListaDorinte extends JFrame{
    JList lista=new JList();
    JScrollPane scroll=new JScrollPane();
    DefaultListModel dlm=new DefaultListModel();
    DefaultListModel dlmn=new DefaultListModel();
    JButton b1;
    JButton b2;
    
    public ListaDorinte(String text,String name) {
        super(text);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1100,650));
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(null);
        
        lista=new JList(dlmn);
        scroll=new JScrollPane(lista);
        scroll.setBackground(Color.white);
        scroll.setBounds(50,50,500,500);
        add(scroll,BorderLayout.CENTER);
        
        Store store = Store.getInstance();
        for(Customer c:store.v_customers)
            if(c.Name.equals(name)){
                Iterator<Item> itr = c.wishList.listIterator();
                while(itr.hasNext())
                    dlm.addElement(itr.next());
            }
        String str = new String();
           for(int i = 0 ; i < dlm.size() ; i++) {
                Item itm = (Item)dlm.getElementAt(i);
                str = itm.getName() + " ...... " + itm.getPrice() + " ...... " + itm.getId()+""+Integer.parseInt(Integer.toString(itm.getId()).substring(0, 1));
                dlmn.addElement(str);
           }
           
        b1 = new JButton("Adaugare");
        b1.setFont(new Font("TimesNewRoman", Font.HANGING_BASELINE, 12));
        b1.setBounds(600,150,400,100);
        b1.setBackground(Color.lightGray);
        
        b2 = new JButton("Stergere");
        b2.setFont(new Font("TimesNewRoman", Font.HANGING_BASELINE, 12));
        b2.setBounds(600,350,400,100);
        b2.setBackground(Color.lightGray);
            
        add(b1);
        add(b2);
        
        show();
        pack();
    }
}


class Pagina2 extends JFrame{
    JList lista=new JList();
    JScrollPane scroll=new JScrollPane();
    DefaultListModel dlm=new DefaultListModel();
    DefaultListModel alfabetic=new DefaultListModel();
    
    JList listaCust=new JList();
    JScrollPane scrollCust=new JScrollPane();
    DefaultListModel dlmCust=new DefaultListModel();
    
    JButton b1;
    JButton b2;
    JButton b3;
    JButton b4;
    JButton b5;
    JButton b6;
    JButton b7;
    JButton b8;
    String name = "";
    Integer id;
    double price;
    String strn="" ;
    Double pr ;
    Integer in ;
    Integer did ;
    JLabel jl1 ;
    JTextField jtf1;
    JLabel jl2 ;;
    JTextField jtf2 ;
    JLabel jl3 ;
    JTextField jtf3 ;
    JLabel jl4;
    JTextField jtf4;
    Integer oldId;
    Item auxItem = null;
    JLabel jl5;
    JTextField jtf5;
    
    public Pagina2(String text){
        super(text);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1100,650));
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(null);
        
        
        Store store = Store.getInstance();
        for(Department d : store.v_department){
            for(Item i : d.items){
                dlm.addElement(i);
            }
        }
         String str = new String();
         DefaultListModel dlmn=new DefaultListModel();
           for(int i = 0 ; i < dlm.size() ; i++) {
                Item itm = (Item)dlm.getElementAt(i);
                str = itm.getName() + " ...... " + itm.getPrice() + " ...... " + itm.getId();
                dlmn.addElement(str);
           }
           
           for(Customer c : store.v_customers){
               dlmCust.addElement(c);
        }
         String strc = new String();
         DefaultListModel dlmnCust=new DefaultListModel();
           for(int i = 0 ; i < dlmCust.size() ; i++) {
                Customer cust = (Customer)dlmCust.getElementAt(i);
                strc = cust.Name;
                dlmnCust.addElement(strc);
           }
           
        jl1 = new JLabel("Nume");
        jl1.setBounds(360,290,80,20);
        jl1.setForeground(Color.red);
        jtf1 = new JTextField();
        jtf1.setBounds(360,310,150,20);
        jl2 = new JLabel("Pret");
        jl2.setBounds(360,350,80,20);
        jl2.setForeground(Color.red);
        jtf2 = new JTextField();
        jtf2.setBounds(360,370,150,20);
        jl3 = new JLabel("Id");
        jl3.setBounds(360,390,80,20);
        jl3.setForeground(Color.red);
        jtf3 = new JTextField();
        jtf3.setBounds(360,410,150,20);   
        jl4 = new JLabel("Id Vechi");
        jl4.setBounds(360,430,80,20);
        jl4.setForeground(Color.red);
        jtf4 = new JTextField();
        jtf4.setBounds(360,450,150,20); 

           
        lista=new JList(dlmn);
        scroll=new JScrollPane(lista);
        scroll.setBackground(Color.white);
        scroll.setBounds(50,50,300,500);
        add(scroll,BorderLayout.CENTER);
        
        listaCust=new JList(dlmnCust);
        scrollCust=new JScrollPane(listaCust);
        scrollCust.setBackground(Color.white);
        scrollCust.setBounds(900,50,150,500);
        add(scrollCust,BorderLayout.CENTER);
        
        b1 = new JButton("Alfabetic");
        b1.setFont(new Font("TimesNewRoman", Font.HANGING_BASELINE, 12));
        b1.setBounds(360,50,120,30);
        b1.setBackground(Color.lightGray);
        b1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               ArrayList<Item> vector = new ArrayList<>();
               for(int i = 0 ; i < dlm.size() ; i++) {
                Item itm = (Item)dlm.getElementAt(i);
                vector.add(itm);
                
           }
               //dlmn=new DefaultListModel();
               dlm.removeAllElements();
               Alfabetic a = new Alfabetic();
               Collections.sort(vector,a);
               for(Item i : vector){
                   dlm.addElement(i);
               }
            dlmn.removeAllElements();
            
            String str2 = new String();
            for(int i = 0 ; i < dlm.size() ; i++) {
                Item itm = (Item)dlm.getElementAt(i);
                str2 = itm.getName() + " ...... " + itm.getPrice() + " ...... " + itm.getId();
                dlmn.addElement(str2);
           }
            lista.setModel(dlmn);
            }
        
    });
        
        b2 = new JButton("Crescator");
        b2.setFont(new Font("TimesNewRoman", Font.HANGING_BASELINE, 12));
        b2.setBounds(360,90,120,30);
        b2.setBackground(Color.lightGray);
        b2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               ArrayList<Item> vector = new ArrayList<>();
               for(int i = 0 ; i < dlm.size() ; i++) {
                Item itm = (Item)dlm.getElementAt(i);
                vector.add(itm);
                
           }
               dlm.removeAllElements();
               Crescator a = new Crescator();
               Collections.sort(vector,a);
               for(Item i : vector){
                   dlm.addElement(i);
               }
            
            dlmn.removeAllElements();
            String str2 = new String();
            for(int i = 0 ; i < dlm.size() ; i++) {
                Item itm = (Item)dlm.getElementAt(i);
                str2 = itm.getName() + " ...... " + itm.getPrice() + " ...... " + itm.getId();
                dlmn.addElement(str2);
           }
            lista.setModel(dlmn);
            }
        
    });
        
        b3 = new JButton("Descrescator");
        b3.setFont(new Font("TimesNewRoman", Font.HANGING_BASELINE, 12));
        b3.setBounds(360,130,120,30);
        b3.setBackground(Color.lightGray);
        b3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               ArrayList<Item> vector = new ArrayList<>();
               for(int i = 0 ; i < dlm.size() ; i++) {
                Item itm = (Item)dlm.getElementAt(i);
                vector.add(itm);
                
           }
               dlm.removeAllElements();
               Descrescator a = new Descrescator();
               Collections.sort(vector,a);
               for(Item i : vector){
                   dlm.addElement(i);
               }
            dlmn.removeAllElements();
            
            String str2 = new String();
            DefaultListModel alf2=new DefaultListModel();
            for(int i = 0 ; i < dlm.size() ; i++) {
                Item itm = (Item)dlm.getElementAt(i);
                str2 = itm.getName() + " ...... " + itm.getPrice() + " ...... " + itm.getId();
                dlmn.addElement(str2);
           }
            lista.setModel(dlmn);
            }
        
    });
        
        
        
        
        
        b4 = new JButton("Editare");
        b4.setFont(new Font("TimesNewRoman", Font.HANGING_BASELINE, 12));
        b4.setBounds(360,170,120,30);
        b4.setBackground(Color.lightGray);
        b4.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                
              
                        strn = jtf1.getText();
                        pr = Double.parseDouble(jtf2.getText());
                        in = Integer.parseInt(jtf3.getText());
                        oldId = Integer.parseInt(jtf4.getText());
                        did = Integer.parseInt(Integer.toString(oldId).substring(0, 1));
                
                
                for(Item i : store.v_department.get(did-1).items)
                    if(i.getId() == oldId){
                        i.setName(strn);
                        i.setId(in);
                        i.setPrice(pr);
                    }
                dlm.removeAllElements();
            for(Department d : store.v_department){
               for(Item i : d.items){
                dlm.addElement(i);
            }
        }
            dlmn.removeAllElements();
            String str3 = new String();
           for(int i = 0 ; i < dlm.size() ; i++) {
                Item itm = (Item)dlm.getElementAt(i);
                str3 = itm.getName() + " ...... " + itm.getPrice() + " ...... " + itm.getId();
                dlmn.addElement(str3);
           }
                        
                lista.setModel(dlmn);
            }
            
        });
        
        b5 = new JButton("Stergere");
        b5.setFont(new Font("TimesNewRoman", Font.HANGING_BASELINE, 12));
        b5.setBounds(360,210,120,30);
        b5.setBackground(Color.lightGray);
        b5.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                oldId = Integer.parseInt(jtf4.getText());
                did = Integer.parseInt(Integer.toString(oldId).substring(0, 1));
                for(Item itm : store.v_department.get(did-1).items){
                                if(itm.getId() == oldId)
                                    auxItem = itm;;
                            }
                for(Customer c : store.v_customers){
                                if(c.cart.contains(auxItem)){
                                    c.cart.delItem(auxItem);
                                    store.v_department.get(did-1).exit(c);
                                }
                                if(c.wishList.contains(auxItem)){
                                    c.wishList.delItem(auxItem);
                                    store.v_department.get(did-1).removeObserver(c);
                                }
                            }
                Iterator<Item> itr = store.v_department.get(did-1).items.iterator();
                while(itr.hasNext()){
                    if(itr.next().getId() == oldId){
                        itr.remove();
                    }
                }
                
                dlm.removeAllElements();
                for(Department d : store.v_department){
                    for(Item i : d.items){
                        dlm.addElement(i);
                 }
            }
                dlmn.removeAllElements();
         String str3 = new String();
           for(int i = 0 ; i < dlm.size() ; i++) {
                Item itm = (Item)dlm.getElementAt(i);
                str3 = itm.getName() + " ...... " + itm.getPrice() + " ...... " + itm.getId();
                dlmn.addElement(str3);
           }
                        
                lista.setModel(dlmn);
            }
            
        });
        
        b6 = new JButton("Adaugare");
        b6.setFont(new Font("TimesNewRoman", Font.HANGING_BASELINE, 12));
        b6.setBounds(360,250,120,30);
        b6.setBackground(Color.lightGray);
        b6.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                strn = jtf1.getText();
                pr = Double.parseDouble(jtf2.getText());
                in = Integer.parseInt(jtf3.getText());
                did = Integer.parseInt(Integer.toString(in).substring(0, 1));
                Item item2 = new Item(strn , in , pr);
                store.v_department.get(did-1).addItem(item2);
                store.v_department.get(did-1).notifyAllObservers(new Notification(did , in , NotificationType.ADD));
                
                dlm.removeAllElements();
                for(Department d : store.v_department){
                    for(Item i : d.items){
                        dlm.addElement(i);
                 }
            }
                 dlmn.removeAllElements();
                String str4 = new String();
                for(int i = 0 ; i < dlm.size() ; i++) {
                    Item itm = (Item)dlm.getElementAt(i);
                    str4 = itm.getName() + " ...... " + itm.getPrice() + " ...... " + itm.getId();
                    dlmn.addElement(str4);
           }
                        
                lista.setModel(dlmn);
            }
            
        });
        
        b7 = new JButton("ShoppingCart");
        b7.setFont(new Font("TimesNewRoman", Font.HANGING_BASELINE, 12));
        b7.setBounds(770,50,120,30);
        b7.setBackground(Color.lightGray);
        
        b8 = new JButton("WishList");
        b8.setFont(new Font("TimesNewRoman", Font.HANGING_BASELINE, 12));
        b8.setBounds(770,100,120,30);
        b8.setBackground(Color.lightGray);
        b8.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                    name = listaCust.getModel().getElementAt(listaCust.getSelectedIndex()).toString();
                    dispose();
                    new ListaDorinte("WishList",name);
            }
            
        });
        
        add(jl1);
        add(jtf1);
        add(jl2);
        add(jtf2);
        add(jl3);
        add(jtf3);
        add(jl4);
        add(jtf4);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
        add(b7);
        add(b8);
        
            
        setVisible(true);
        show();
        pack();
    }
    }
  

public class Interface extends JFrame {
    private JButton button1;
    
    public Interface(String text){
        super(text);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1100,650));
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(null);
        button1 = new JButton("Enter Supermarket");
        button1.setFont(new Font("TimesNewRoman", Font.HANGING_BASELINE, 40));
        button1.setBounds(200,350,700,50);
        button1.setBackground(Color.lightGray);
        button1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
        String storeFile = "store.txt";
        String customersFile = "customers.txt";
        Store store = Store.getInstance();
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
        Item item;
        int depNr = 0;
        Customer customer = null;
        String depName = "";
        Integer depId = 0;
        
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
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }finally{
            try{
                if(lnr != null)
                    lnr.close();
            }catch(IOException ex){
                ex.printStackTrace();
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
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }finally{
            try{
                if(lnr != null)
                    lnr.close();
            }catch(IOException ex){
                        ex.printStackTrace();
            }
            
        }
            dispose();
            new Pagina2("Produse");
            
            }//actionPerformed;
        });//buttonactionListener;
        add(button1);
        
        show();
        pack();
    }
      public static void main(String args[]){
        Interface in = new Interface("Supermarket");
        
    }
    
}

    
    