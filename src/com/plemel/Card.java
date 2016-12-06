package com.plemel;

//      Card has a int value which matched string name (except Ace)
//      but allows for use in string without parsing. also has String suit
//


public class Card {

//    final static String ACE = "A";
//    final static String JACK = "J";
//    final static String QUEEN = "Q";
//    final static String KING = "K";

    String suit;
    int value;
    String name;

    //Constructor (3 arguments)
    public Card(String s, int v, String n){
        this.value = v;
        this.suit = s;
        this.name = n;

    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String toString(){

        return (this.name + " of " + this.suit);


    }
//    public static String convertNumberToCardVal(int val) {
//
//        if (val == 1) {
//            return ACE;
//        }
//        else if (val == 11) {
//            return JACK;
//        }
//        else if (val == 12) {
//            return QUEEN;
//        }
//        else if (val == 13) {
//            return KING;
//        }
//        else {
//            return Integer.toString(val);
//        }
//      }


}
