//Mason Doherty
//CS 3345.503
//Data Structures and Algorithm Analysis
// 9/22/2023
// Project 1 | Doubly Linked Memory List

public class MemLinkedList{
    Node base = new Node(1000, null, null, false);                  //creates default 1000K unallocated node and sets it to head/tail
    private Node head = base;                                   
    private Node tail = base;
    private Node iterator;          
    int size = 1;

    public void request(int d){
        iterator = head;
        if (d > 1000 | d < 0)                                       //input validation
        {              
            System.out.println("Invalid request");
            return;
        }
        while (iterator != null){                                   //walks the iterator through the list until it either hits an unallocated 
            if (iterator.allocated == false && iterator.data >= d){ //node with >= the requested data or the end of the list
                if (iterator.data == d){                            
                    iterator.allocated = true;                      //if the iterator finds a node equal to d, simply set the 
                    return;
                } 
                else 
                {
                Node n = new Node(d,iterator.prev,iterator, true);
                size++;
                if (iterator != head)                               
                {
                    iterator.prev.next = n;
                }
                if (iterator.prev == null){                         //if no previous node, then n is set to head
                    head = n;                                       //side note: n cannot possibly be the tail, unless n is equal to the tail node, in which
                }                                                   //n "becomes" the tail node by setting its allocation boolean to true. (line 17)
                iterator.prev = n;
                
                iterator.data = iterator.data - d;
                return;
                }
            }
            else 
            {
                iterator = iterator.next;
            }
        }
        System.out.println("Insufficient free memory");             //error catch in case the while loop hits null without finding a possible node to allocate.
        return;
    }

    public void release(int d){
        iterator = head;
        while (iterator != null )                                           //iterator walks the list
        {
            if (iterator.allocated == true && iterator.data == d)           //iterator stops at node flagged as allocated and equal to size d
            {
            iterator.allocated = false;
            if (iterator != head && iterator.prev.allocated == false){      //if itr.prev is free, combine nodes
                iterator.prev.data += iterator.data;
                if (iterator != tail){
                iterator.prev.next = iterator.next;
                iterator.next.prev = iterator.prev;
                } else {
                    tail = iterator.prev;
                    iterator.prev.next = null;
                }
                iterator = iterator.prev;
                size--;
            }
            if (iterator != tail && iterator.next.allocated == false){      //if itr.next is free, combine nodes
                iterator.next.data += iterator.data;
                if (iterator != head){
                iterator.prev.next = iterator.next;
                iterator.next.prev = iterator.prev;
                } else {
                    head = iterator.next;
                    iterator.next.prev = null;
                }
                iterator = iterator.next;
                size--;
            }
            return;
            }
            iterator = iterator.next;
        }
        System.out.println("Node not found");                               //error catch
        return;
    }

    public void clear(){
        Node base = new Node(1000,null,null,false);                         //resets list to default singular 1000k free node.
        head = base;
        tail = base;
    }

    public void print(){
        iterator = head;                                                        //walks the list and prints each element
        System.out.println("Data| Allocated");
        while(iterator != null){
            System.out.println(iterator.data + " | " + iterator.allocated);
            iterator=iterator.next;
        }

    }
    
    public static class Node{
        int data;
        boolean allocated;
        Node next = null;
        Node prev = null;

        Node(int d, Node p, Node n, boolean a){                     //node constructor consisting of data int, prev reference, next reference, and flag for allocated or not
            data = d;
            prev = p;
            next = n;
            allocated = a;
        }
    }

    public static void main(String[] args){
        MemLinkedList list = new MemLinkedList();
        list.request(999);
        list.release(1);
        list.release(999);
        list.print();

    }
}