/* Name : Angela Nguyen
 * ICS4U
 * 2023/01/06
 * This is the main code of the gourmet sushi belt which is a queue that implements linked lists
 */
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        //Calling on objects and seeting variables
        Scanner in = new Scanner(System.in);
        LinkedQueue sushiBelt = new LinkedQueue();
        File sushiBeltFile = new File("C:\\Users\\angel\\Downloads\\SushiBelt.txt");

        //colouring text codes
        String reset = "\u001B[0m";
        String yellow = "\u001B[33m";
        String blue = "\u001B[34m";
        String purple = "\u001B[35m";

        int total = 0;
        int choice = 0;
        int balance = 0;

        //Introductions
        System.out.println("\nWelcome Stranger!! To " + purple + " ANGELA'S GOURMET SUSHI BELT! "
                + reset + "\n ────RULES────"
                + "\n- fill in your balance every time you play"
                + "\n- you can only eat from the front of your belt"
                + "\n- you will pay as you add sushi's onto your belt");

        //if a sushi belt file exists, that means there is previous game data that can be restored
        if (sushiBeltFile.exists()) {

            //prompt whether user would like to be restore data
            System.out.println(blue + "\nWould you like to restore previous game data? [y/n]" + reset);
            String restore = in.nextLine();

            //if the user chooses yes
            if (restore.equalsIgnoreCase("y")) {

                //read the file contents and store the objects back into the linked queues
                try {
                    FileInputStream input = new FileInputStream(sushiBeltFile);
                    ObjectInputStream readSushi = new ObjectInputStream(input);

                    Sushi sushi1 = new Sushi();
                    do {
                        sushi1 = (Sushi) readSushi.readObject();
                        sushiBelt.enQueue(sushi1);
                    } while (sushi1.getNext() != null);

                } catch (IOException e) {
                    System.out.println("Problem with the input and output");
                    System.err.println("FileNotFoundException: " + e.getMessage());
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
            } else { //if user doesn't want to restore data, delete
                sushiBeltFile.delete();
            }
        }

        //determine user balance
        while (balance == 0) {
            System.out.println("\nHow much money do you have?");
            balance = in.nextInt();

            //create a limit so users can't buy too many sushis
            if (balance > 200) {
                balance = 0;
                System.out.println("It is dangerous to carry that much cash on you..");
            } else if (balance < 0) {
                balance = 0;
                System.out.println("If you are in debt, NO SUSHI FOR YOU");
            }
        }

        //Main Menu 
        while (!(choice == 10)) {

            System.out.println(purple + "[hit enter to view the menu]" + reset);
            in.nextLine();

            System.out.print("------------------" + yellow
                    + "\n[ M A I N   M E N U ]"
                    + "\n[1] - Fill Belt"
                    + "\n[2] - Eat From Belt"
                    + "\n[3] - See Balance"
                    + "\n[4] - See Belt"
                    + "\n[5] - See First"
                    + "\n[6] - See Belt Size"
                    + "\n[7] - Clear Belt"
                    + "\n[8] - Sort By Price"
                    + "\n[9] - Earn Money !!!!!"
                    + "\n[10] - Save Data & Leave"
                    + "\n[11] - Reset Data & Leave" + reset
                    + "\n\nOption Choice : ");

            choice = in.nextInt();

            if (choice == 1) { //Fills the Belt
                System.out.print(
                        "\nPlease pick from the menu a sushi type and indicate"
                                + "\nhow many you would like to add to the belt" + yellow
                                + "\n[ M E N U ]"
                                + "\n[0] - Tekka Maki Tuna $5"
                                + "\n[1] - Tamago Cooked Egg $20"
                                + "\n[2] - Ebi Shrimp $40"
                                + "\n[3] - Tako Octopus $65"
                                + "\n[4] - Kappa Maki Cucumber $80"
                                + "\n[5] - exit back to main menu"
                                + reset);

                int[] sushiPrices = { 5, 20, 40, 65, 80, 0 };

                int type = -1;
                int count = 0;
                int totalCost = 0;


                while ((type > 5) || (type < 0)) {
                    System.out.print("\nENTER OPTION : ");
                    in = new Scanner(System.in);
                    type = in.nextInt();

                    if ((type > 5) || (type < 0)) {
                        System.out.println("invalid input try again");
                        type = -1;
                    } else { //if users want to pick a sushi type that they cannot afford at least one of
                        //force them to pick another one
                        if (sushiPrices[type] > balance) {
                            System.out.println("you cannot even afford 1 of this sushi type :(");
                            type = -1;
                        }
                    }
                }

                //if users want to leave
                if (type == 5) {
                    System.out.println("...");
                } else { //if users picked a valid sushi option, they can choose a sushi count
                    while (totalCost == 0) {
                        System.out.print("ENTER SUSHI COUNT : ");
                        count = in.nextInt();
                        totalCost = count * sushiPrices[type];
                        System.out.println("Total Cost : " + totalCost + "\n\n──────────────────");
                        if (totalCost > balance) {
                            System.out.println(
                                    "the total is $" + totalCost + " and you cannot afford this much sushi with only $"
                                            + balance + " in your account... :(\n");
                            totalCost = 0;
                        }
                    }

                    //make a bunch of sushi objects and enQueue them into the linked lists
                    for (int i = 0; i < count; i++) {
                        sushiBelt.enQueue(make(type));
                    }

                    balance -= totalCost;
                    total += totalCost;

                    System.out.println("\n---YOUR SUSHIS---\n" + sushiBelt.toString()
                            + "\nSushi added succesfully!\nWhat would you like to do now?");
                }

            } else if (choice == 2) { //eats a sushi (deQueues one item in the list)
                try {
                    System.out.println("enjoy your sushi!\n\nSUSHI INFO : " + sushiBelt.deQueue());
                    ;
                } catch (Exception e) {
                    System.out.println("this is awkward...you just tried to eat from an empty belt...\n"
                            + "lets maybe add some yummy sushis on first :)"
                            + "\n(option 1 on the menu hehe)");
                }

            } else if (choice == 3) { //allows user to see their balance
                System.out.println("----------------"
                        + "\nYou have a balance of : " + balance
                        + "\n----------------");

            } else if (choice == 4) { //Sees the sushis on the belt
                System.out.println("Here is your belt \n----------------");
                System.out.println(sushiBelt.toString() + "");

            } else if (choice == 5) { //Sees the first sushi that users can actually dequeue
                try {
                    System.out.println("Here is your first sushi (the one that you can eat)!"
                            + "\nSUSHI INFO : " + sushiBelt.first());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    System.out.println("empty belt...please add sushi\n");
                }

            } else if (choice == 6) { //See how many sushis on the belt
                System.out.println("You have a belt size of " + sushiBelt.size() + " sushis!");

            } else if (choice == 7) { //Clears the belt of all the sushi
                System.out.println("Belt Emptied!"
                        + "────────────────");

            } else if (choice == 8) { //Sorts the belts by price using bubble sort
                System.out.println("Your belt will now be sorted by price! Here it is !!");
                sushiBelt.sortList();

            } else if (choice == 9) {//calls on the method
                balance += washDishes();

            } else if (choice == 10) {//saves game data to a file for users to continue later on

                // check if file exists
                if (!sushiBeltFile.exists()) {
                    System.out.println("File Not Found So Will Be Made");
                    try {
                        sushiBeltFile.createNewFile();
                    } catch (IOException e) {
                        System.err.println("Error : " + e.getMessage());
                    }
                }

                //writes onto a file
                try {
                    FileOutputStream out = new FileOutputStream(sushiBeltFile);
                    ObjectOutputStream writeSushi = new ObjectOutputStream(out);

                    Sushi current = sushiBelt.getHead();

                    //uses object serialisation as it goes through the linked queue
                    while (current != null) {
                        writeSushi.writeObject(current);
                        current = current.getNext();
                    }
                    writeSushi.close();
                } catch (IOException e) {
                    System.err.println("Error Caught : " + e.getMessage());
                }

                System.out.println("Your sushi belt has been saved! Come back and you can restore your sushis"
                        + "\nGoodbye for now user <3");

            } else if (choice == 11) { //Leave without saving
                choice = 10;
                if (sushiBeltFile.exists()) {
                    sushiBeltFile.delete();
                    System.out.print("Changes have been deleted! ");
                }
                System.out.println("Today you have spent " + total + " at our restaurant!"
                        + "\nYour Bill Has Been Paid & Thank You For Visiting!"
                        + "\n\nCome back soon!! <3");
            }
            in.nextLine();
        }

    }

    //Makes sushi objects to enqueue into the linked lists
    public static Sushi make(int a) {
        //string array of images 
        String[] menu = new String[5];
        menu[0] = " ,;\'@@\';,"
                + "\n|\',_@@_,\'|"
                + "\n|        |"
                + "\n \'.____.\' ";

        menu[1] = " ------;;;;------"
                + "\n|______|;;|______|"
                + "\n  |    |;;|    |"
                + "\n   \'.__|;;|__.\'";

        menu[2] = ",\'\' ;  ;  ;  \'\'|||\\///"
                + "\n\',,_;__;__;__;,\'\'\'/\\\\\\"
                + "\n |            |"
                + "\n  \'.________.\'";

        menu[3] = "   ,;\'\'\'\'\'\'\'\';,"
                + "\n ,\'  _o_o_o_o  \',"
                + "\n,,,;\'        \';,\'"
                + "\n   \'.________.\'";

        menu[4] = " ,;\'00\';,"
                + "\n|\',_00_,\'|"
                + "\n|        |"
                + "\n \'.____.\' ";

        String name = "";
        int price = 0;

        //making sushi objects depending on what the user chose
        if (a == 0) {
            name = "Tekka Maki Tuna";
            price = 5;
        } else if (a == 1) {
            name = "Tamago Cooked Egg";
            price = 20;
        } else if (a == 2) {
            name = "Ebi Shrimp";
            price = 40;
        } else if (a == 3) {
            name = "Tako Octopus";
            price = 65;
        } else if (a == 4) {
            name = "Kappa Maki Cucumber";
            price = 80;
        }

        Sushi sushi = new Sushi(name, menu[a], price);
        return sushi;
    }

    //Method that lets users play the mini game "KITCHEN" and allows them to earn money
    public static int washDishes(){

        Random rand = new Random();
        Scanner in = new Scanner(System.in);

        //introductions
        System.out.println("\nWELCOME BUDDY ! TO THE " +"\u001B[35m" + " KITCHEN!" + "\u001B[0m"
                + "\nYou will wash dishes and chopsticks for money!!");

        //randomizes a variable which then determines what tableware the user will be washing 
        int a = rand.nextInt(3);
        String tableware = "";
        switch (a) {
            case 0: tableware = "PLATES";
                    break;
            case 1: tableware = "CUPS";
                    break;
            case 2: tableware = "CHOPSTICKS";
                    break;
        }
        a = rand.nextInt(10)+1; //randomize how many scrubs they will need to wash the dishes successfully
        System.out.println("CLEAN THESE " + tableware + " NOW!!! \n[HIT ENTER "+a+" TIMES]" );

        for (int i = 0 ; i < a ; i ++){
            in.nextLine();
            System.out.println((i+1) +" scrub complete !"
                + "\n    ___________"
                + "\n  .;---------./|"
                + "\n // S O A P // |"
                + "\n|\'---------\'|  /"
                + "\n|           | /"
                + "\n\'-----------\'`");
        }

        a = rand.nextInt(90)+10; //randomize the award
        System.out.println("CONGRATULATIONS! YOU HAVE EARNED $" + a + "!!");
        return a;
    }
}