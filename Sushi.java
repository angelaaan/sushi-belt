import java.io.Serializable;

public class Sushi implements Serializable{
    String name;
    String image;
    int price;
    private Sushi next;

    // Creates an empty node.
    public Sushi(){
        next = null;
        name = "";
        image = "";
        price = 0;
    }

    //filled constructor
    public Sushi(String nom, String ASCII, int num) {
        next = null;
        name = nom;
        image = ASCII;
        price = num;
    }

    // Returns the node that follows this one.
    public Sushi getNext() {
        return next;
    }

    public String getName(){
        return name;
    }

    public String getImage(){
        return image;
    }

    public int getPrice(){
        return price;
    }    

    // Sets the node that follows this one.
    public void setNext(Sushi node) {
        next = node;
    }

    public void setName(String nom){
        name = nom;
    }

    public void setImage(String pic){
        image = pic;
    }
    
    public void setPrice(int num){
        price = num;
    }


    // Returns the data stored in this node.
    public String getData() {
        String info = "Name: "+ name + " ~ Price: " + price;
        return info;
    }



}
