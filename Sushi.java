/* Name : Angela Nguyen
 * ICS4U
 * 2023/01/06
 * This program is the sushi node that holds the name, image, price and next Sushi node
 */
import java.io.Serializable;

public class Sushi implements Serializable{
    String name;
    String image;
    int price;
    private Sushi next;

    // Creates an empty sushi node.
    public Sushi(){
        next = null;
        name = "";
        image = "";
        price = 0;
    }

    // Creates a filled sushi node.
    public Sushi(String nom, String ASCII, int num) {
        next = null;
        name = nom;
        image = ASCII;
        price = num;
    }

    // Returns the sushi node that follows this one.
    public Sushi getNext() {
        return next;
    }

    //Returns the String of the sushi name
    public String getName(){
        return name;
    }

    //Returns this sushi's image
    public String getImage(){
        return image;
    }

    //Returns the price the sushi was bought for.
    public int getPrice(){
        return price;
    }    

    // Sets the sushi node that follows this one.
    public void setNext(Sushi node) {
        next = node;
    }

    // Sets the name of this node.
    public void setName(String nom){
        name = nom;
    }

    // Sets the image String of this node.
    public void setImage(String pic){
        image = pic;
    }
    
    // Sets the price of this node
    public void setPrice(int num){
        price = num;
    }

    // Returns the data stored in this node.
    public String getData() {
        String info = "Name: "+ name + " ~ Price: " + price;
        return info;
    }

}
