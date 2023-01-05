public class Linked {
    private Node head;

    public Linked(){
        head = null;
    }

    public void addToFront(String n){
        Node newNode = new Node (n);
        newNode.setNext(head);
        head = newNode;
    }

    public void remove(){

        Node newNode = head.getNext(); 
        head = newNode;

    }

    public String toString(){
        String result = "";

        Node current = head;

            while(current.getNext() != null) {
                result += ( current.getData() + ", ") ;
                current = current.getNext();
            }
            result += ( current.getData() + ", ") ;
            
        return result;
    }

    private class Node {

        private String data;
        private Node next; //recursive link

        //make a constructor to help initialize the data and the recursive link
        public Node(String n){
            data = n ;
            next = null;
        }

        //make nextNode
        public void setNext(Node newNode){
            next = newNode;
        }

        //return the Node
        public Node getNext(){
            return next;
        }

        public String getData(){
            return data;
        }


    }
}
