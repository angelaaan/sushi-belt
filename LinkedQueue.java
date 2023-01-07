public class LinkedQueue {
    private Sushi head;
    private Sushi rear;
    private int size;

    // Creates an empty queue.
    public LinkedQueue() {
        head = rear = null;
        size = 0;
    }

    // Adds the specified data to the rear of the queue.
    public void enQueue(Sushi node) {

        if (isEmpty()) {
            head = node;
        } else {
            rear.setNext(node);
        }
        rear = node;
        size++;
    }

    // Removes the data at the front of the queue and returns a
    // reference to it. Throws an Exception if the queue is empty.
    public String deQueue() throws Exception {

        //throw if thereare 0 items
        if (isEmpty()){
            throw new Exception("queue");
        }
            
        //holds onto head data to return later
        String data = head.getData();
        System.out.println(head.getImage());

        //erase the head and decerase size
        head = head.getNext();
        size--;

        //if it is now empty, set the rear back to null
        if (isEmpty()){
            rear = null;
        }

        //return data
        return data;
    }

    // Returns a reference to the data at the front of the queue.
    // The data is not removed from the queue. Throws an
    // Exception if the queue is empty.
    public String first() throws Exception {

        //throw error if there is nothing in the list
        if (isEmpty()){
            throw new Exception();
        }

        //return the data at the front
        System.out.println(head.getImage()+"\n");
        return head.getData();
    }

    public void sortList()
    {
 
        // Node current will point to head
        Sushi current = head;
        Sushi index = null;
 
        String tempName;
        String tempImage;
        int tempPrice;
 
        if (head == null) {
            return;
        }
        else {
            while (current != null) {
                // Node index will point to node next to
                // current
                index = current.getNext();
 
                while (index != null) {
                    // If current node's data is greater
                    // than index's node data, swap the data
                    // between them
                    if (current.getPrice() > index.getPrice()) {
                        tempName = current.getName();
                        tempImage = current.getImage();
                        tempPrice = current.getPrice();

                        current.setName(index.getName());
                        current.setImage(index.getImage());
                        current.setPrice(index.getPrice());

                        index.setName(tempName);
                        index.setImage(tempImage);
                        index.setPrice(tempPrice);
                    }
 
                    index = index.getNext();
                }
                current = current.getNext();
            }

            System.out.println(this.toString());
        }
    }

    public Sushi getHead(){
        return head;
    }

    // Returns true if this queue is empty and false otherwise.
    public boolean isEmpty() {
        return (size == 0);
    }

    // Returns the number of elements in this queue.
    public int size() {
        return size;
    }

    public void makeEmpty(){
        head = rear = null;
        size = 0;
    }

    // Returns a string representation of this queue.
    public String toString() {
        if (isEmpty()){
            System.out.println("this is awkward...you forgot to add sushis onto the belt..");
        }

        String images = "";
        String info = "";
        Sushi current = head;
        int i = 1;

        while (current != null) {
            System.out.println(current.getImage() + "\n");
            info += "[" + i +"] - "+current.getData() + "\n";
            current = current.getNext();
            i++;
        }

        return info;
    }

}