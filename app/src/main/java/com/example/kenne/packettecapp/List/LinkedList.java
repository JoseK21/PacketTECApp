package com.example.kenne.packettecapp.List;

public class LinkedList {
	private Node head;
	
    public Node getHead() {
		return head;
	}
	
	public void setHead(Node head) {
		this.head = head;
	}
	
    public LinkedList(){
        empty();
    }

    private void empty(){
        head = null;
    }
    public boolean isEmpty(){
        return head == null;
    }

    public void addFirst(String t){
        Node neW = new Node(t);
 
        if (!isEmpty()){
            neW.setNext(head);
        }
        head=neW;
         
    }

    public void addLast(String t){
 
        Node neW = new Node(t);
        Node aux;
 
        if (isEmpty()) {
            addFirst(t);
        }else {
            aux = head;

            while(aux.getNext() != null){
                aux=aux.getNext();
            }
            aux.setNext(neW);
        }
    }


    public int size(){
        Node aux;
        int numElementos=0;
        aux = head;
        while(aux != null){
            numElementos++;
            aux = aux.getNext();
        }
        return numElementos;
 
    }

    public String returnData(int pos){
        Node aux=head;
        int cont=0;
        String dato=null;
        if(pos<0 || pos>=size()){
            System.out.println("La posicion insertada no es correcta");
        }else{
            while(aux!=null){
                if (pos == cont){
                    dato=aux.getData();
                }
                aux=aux.getNext();
                cont++; 
            }
        }
        return dato;         
    }
    public Node returnNode(int pos){
        Node aux=head;
        int cont=0;
         
        if(pos<0 || pos>=size()){
            System.out.println("La posicion insertada no es correcta");
        }else{
            while(aux!=null){
                if (pos == cont){
                    return aux; 
                }
                aux=aux.getNext();
                cont++;
                 
            }
        }
        return aux;
         
    }
    public String mostrarLista(){
        Node aux=head;
        String chain="";
        while(aux!=null){
            String x = "[" + aux.getData() + "]";
            chain+=x;
            aux=aux.getNext();
        }
        return chain;
    }



}
