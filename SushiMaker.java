public class SushiMaker {
    int userMoney = 0;

    String[] menu = new String[5];
    menu[0] =" ,;\'@@\';,"
    +"\n|\',_@@_,\'|"
    +"\n|        |"
    +"\n \'.____.\' ";

    menu[1] = " ------;;;;------"
    +"\n|______|;;|______|"
    +"\n  |    |;;|    |"
    +"\n   \'.__|;;|__.\'";

    menu[2] = ",\'\' ;  ;  ;  \'\'|||\\///"
    +"\n\',,_;__;__;__;,\'\'\'/\\\\\\"
    +"\n |            |"
    +"\n  \'.________.\'";

    menu[3] = "   ,;\'\'\'\'\'\'\'\';,"   
    + "\n ,\'  _o_o_o_o  \',"
    + "\n,,,;\'        \';,\'"
    + "\n   \'.________.\'" ;
    
    menu[4] =" ,;\'00\';,"
    +"\n|\',_00_,\'|"
    +"\n|        |"
    +"\n \'.____.\' ";

    public SushiMaker(int money){
        userMoney = money;
    }

    public Sushi make(int a){

        String name;
        int price;

        if (a == 0){
            name = "Tekka Maki Tuna";
            price = 2;
        } else if (a==1){
            name = "Tamago Cooked Egg";
            price = 4;
        } else if (a==2){
            name = "Ebi Shrimp";
            price = 6;
        } else if (a==3){
            name = "Tako Octopus";
            price = 8;
        } else if (a==4){
            name = "Kappa Maki Cucmber";
            price = 2;
        }

        Sushi sushi = new Sushi(name, menu[a], price);
        return sushi;
    }

}