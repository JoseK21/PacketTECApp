package com.example.kenne.packettecapp.List;

public class Node {
    
    private String data;
    private Node next;

    public Node(){
        next=null;
     }
    public Node(String p){
        next=null;
        data = p;
    }  
    public Node(String t, Node next){
        this.next=next;
        data = t;
    }     
    public String getData() {
        return data;
    }     
    public void setData(String data) {
        this.data = data;
    }
    
    public Node getNext() {
        return next;
    }
    
    public void setNext(Node next) {
        this.next = next;
    }     
}
