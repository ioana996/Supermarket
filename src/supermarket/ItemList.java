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
public abstract class ItemList {
    
    static class Node<Item>{
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
        
        public Node(Item item){
            this.item = item;
            this.next = null;
            this.prev = null;
        }
        public Item getItem(){
            return this.item;
        }
        
        public Node<Item> getNext(){
            return this.next;
        }
        
        public Node<Item> getPrev(){
            return this.prev;
        }
        public void setNext(Node<Item> node){
            this.next = node;
        }
        public void setPrev(Node<Item> node){
            this.prev = node;
        }
        
        public void setItem(Item item){
            this.item = item;
        }
    }
    
    public int size;
    public Node<Item> first;
    public Node<Item> last;
    public Comparator<Item> myComp;
    
    public ItemList(Comparator<Item> c){
        this.size = 0;
        this.myComp = c;
    }
    
    
    public boolean add(Item item){
        if(item != null){
        Node<Item> newNode = new Node(item);
        if(isEmpty()){
            newNode.next = null;
            newNode.prev = null;
            this.first=newNode;
            this.last=newNode;
            this.size++;
            return true;
        }
        else{
            if(myComp.compare(newNode.item, first.item) <= 0){
                newNode.next = first;
                first.prev = newNode;
                first = newNode;
                this.size++;
                return true;
            }else if(myComp.compare(newNode.item,last.item) >= 0){
                last.next = newNode;
                newNode.prev = last;
                last = newNode;
                this.size++;
                return true;
            }else {
                Node<Item> front = first.next;
                Node<Item> back = first;
                while(myComp.compare(newNode.item, front.item) > 0){
                    back = front;
                    front = front.next;
                }
                back.next = newNode;
                newNode.prev = back;
                newNode.next = front;
                front.prev = newNode;
                this.size++;
                return true;
            }
                
        }
      }
        return false;
    }
    
    public Item getItem(int index){
        Node<Item> ptr = this.first;
        int contor = 0;
        while(contor != index){
                ptr = ptr.next;
                contor++;
            }       
        return ptr.item;
    }
    
    public Node<Item> getNode(int index){
        Node<Item> ptr = this.first;
        int contor = 0;
        while(contor != index){
                ptr = ptr.next;
                contor++;
        }
        return ptr;
    }
    
    public int indexOf(Item item){
        Node<Item> ptr = first;
        int contor = 0;
        while(myComp.compare(item, ptr.item) != 0){
            ptr = ptr.next;
            contor++;
        }
        return contor;
    }
    
    public int indexOf(Node<Item> node){
        Node<Item> ptr = first;
        int contor = 0;
        while(myComp.compare(node.item, ptr.item) != 0){
            ptr = ptr.next;
            contor++;
        }
        return contor;
    }
    
    public boolean contains(Node<Item> node){
        Node<Item> ptr = first;
        while(ptr != null){
            if(myComp.compare(node.item, ptr.item) != 0){
                ptr = ptr.next;
            }
            else return true;
        }
        return false;
    }
    
    public boolean contains(Item item){
        Node<Item> ptr = this.first;
        while (ptr != null){
            if(myComp.compare(item, ptr.item) != 0){
                ptr = ptr.next;
            }
            else return true;
        }
        return false;
    }
    
    public Item remove(int index){
        Node<Item> ptr;
        if(this.size == 0) throw new NoSuchElementException();
        else{
            if(index == 0){
                ptr = first;
                first = first.next;
                first.prev = null;
                this.size--;
            }
            else if(index == this.size-1){
                    ptr = last;
                    last = last.prev;
                    last.next = null;
                    this.size--;
                }
            else {
                int contor = 0;
                ptr = first;
                Node<Item> p;
                Node<Item> q;
                while( contor != index){
                    ptr = ptr.next;
                    contor++;
                }
                q = ptr.prev;
                p = ptr.next;
                q.next = p;
                p.prev = q;
                this.size--;
            }
        }
        return ptr.item;
    }
   
    public boolean remove(Item item){
        if(myComp.compare(item , first.item) == 0){
            first = first.next;
                first.prev = null;
                this.size--;
                return true;
        }else if(myComp.compare(item , last.item) == 0){
                last = last.prev;
                last.next = null;
                this.size--;
                return true;
        }else {
               Node<Item> ptr = first;
               Node<Item> p;
               Node<Item> q;
               while(ptr != last){
               if(myComp.compare(item, ptr.item) == 0){
                    q = ptr.prev;
                    p = ptr.next;
                    q.next = p;
                    p.prev = q;
                    this.size--;
                    return true;
                 }
                else 
                    ptr = ptr.next;
                }
            }
        return false;
    }
    
    public boolean isEmpty(){
        return this.size == 0;
    }
    
    public boolean addAll(Collection<? extends Item> c){
        if(c.isEmpty())
            return false;
        else{
            Iterator itr = c.iterator();
            while(itr.hasNext()){
                Object o = itr.next();
                if(!this.contains((Item)o))
                    this.add((Item)o);
            }
            
        }
        return true;
    }
    
    public boolean removeAll(Collection<? extends Item> c){
        if(c.isEmpty())
            return false;
        else{
            Iterator itr = c.iterator();
            while(itr.hasNext()){
                Object o = itr.next();
                if(this.contains((Item)o))
                    this.remove((Item)o);
            }
            
        }
        return true;
    }
    
    public ListIterator<Item> listIterator(){
        return new ItemIterator();
    }
    
    public ListIterator<Item> listIterator(int index){
        return new ItemIterator(index);
    }
    
    public class ItemIterator implements ListIterator<Item>{
        private Node<Item> current;
        private int nextIndex;
        private int prevIndex;
        private boolean deleteNode;
        public Node<Item> aux;
        
        public ItemIterator(){
            this.current = first;
            this.nextIndex = 1;
            this.prevIndex = -1;
        }
        
        public ItemIterator(int index){
            Node<Item> ptr = first;
            int contor = 0;
            while(contor != index){
                ptr = ptr.next;
                contor++;
            }
            this.current = ptr;
            this.nextIndex = index+1;
            this.prevIndex = index-1;
        }

        @Override
        public boolean hasNext() {
           return current != null;
           // return nextIndex < size;
            
        }

        @Override
        public Item next() {
            if(hasNext()){
                aux =current;
                current = current.next;
                nextIndex++;
                prevIndex++;
                deleteNode = true;
                return aux.item;
            }
            else throw new NoSuchElementException();
        }
        @Override
        public boolean hasPrevious() {
            return current.prev != null;
        }

        @Override
        public Item previous() {
            if(hasPrevious()){
                current = current.prev;
                nextIndex--;
                prevIndex--;
                deleteNode = true;
                return current.item;
            }
            return null;
        }

        @Override
        public int nextIndex() {
            if(current.next == null )
                return size;
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            if(current.prev == null)
                return -1;
            return prevIndex;
        }

        @Override
        public void remove() {
           /*if(deleteNode == true){
                   Node<Item> p;
                   Node<Item> q;
                   q = current.prev;
                   p = current.next;
                   q.next = p;
                   p.prev = q;
                   current = q;
                   size--;
           }
           else throw  new IllegalStateException();*/
            if(deleteNode == true){
                   Node<Item> p=null;
                   p = aux.prev;
                   p.next = current;
                   current.prev = p;
                   size--;
           }
           else throw  new IllegalStateException();
           deleteNode = false;
        }

        @Override
        public void set(Item e) {
           /* if(myComp.compare(e , first.item) == 0)
                return;
            else if(myComp.compare(e , last.item)== 0)
                last.item = e;*/
            current.item = e;
        }

        @Override
        public void add(Item e) {
            if(myComp.compare(e, current.item) <= 0 ){
            while(myComp.compare(e, current.item) <= 0 )
                current = current.prev;
            Node<Item> newNode = new Node(e);
            newNode.prev = current;
            newNode.next = current.next;
            current.next.prev = newNode;
            current.next = newNode;
            size++;
             }
            else if(myComp.compare(e, current.item) >= 0 ){
            while(myComp.compare(e, current.item) >= 0 )
                current = current.next;
            Node<Item> newNode = new Node(e);
            newNode.prev = current;
            newNode.next = current.next;
            current.next.prev = newNode;
            current.next = newNode;
            size++;
             }
        }

    }
    public abstract Double getTotalPrice();
    //public abstract boolean addAll(Collection<? extends Item> c);
   // public abstract boolean removeAll(Collection<? extends Item> collection);
}
