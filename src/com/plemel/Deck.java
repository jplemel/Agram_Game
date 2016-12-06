package com.plemel;

// Deck modified for Agram game, not normal deck

import java.util.*;


public class Deck {

    //final static Character[] suits = {9829, 9827, 9824, 9830};

    String[] suites = {"Hearts", "Spades", "Clubs", "Diamonds"};
    String[] numsNames = {"Ace", "3", "4", "5", "6", "7", "8", "9", "10"};
    int[] nums = {11, 3, 4, 5, 6, 7, 8, 9, 10};

    //store cards in a hash map to allow for repetitive suites and values

    LinkedList<Card> deck = new LinkedList<>();

    protected Random rng;

    //constructor for deck
    public Deck() {

        rng = new Random();

        //variable to count 52 cards
        int x = 0;

        //identify suite of card
        for(int y = 0; y<suites.length;y++) {
            //identify value of card
            for (int z = 0; z < nums.length; z++) {
                Card card = new Card(suites[y], nums[z], numsNames[z]);

                //add card to linkedlist
                deck.add(card);
                x++;

            }
        }
        //removes chief/not included in deck
        Card chief= null;
        for (Card cd:deck) {
            if (cd.suit.equals("Spades") && cd.value == 11) {
                chief = cd;
            }

        }
        if(chief!=null) {
            deck.remove(chief);
        }
    }

//    public static Deck agramDeck(Deck deck){
//
//        int decklength = deck.cardsLeft();
//
//        System.out.println(decklength + " starting");
//        //loop through deck
//        for (int x = 0; x < decklength ; x++){
//
//            System.out.println(decklength);
//            //remove kings, queens, jacks, the 2s of all suits and the ace of spades
//
//            Card card = deck.cards.get(x);
//
//            if (card.value == "Q" || card.value == "K" || card.value == "J" || card.value == "2" || card.value == "A" && card.suit == 9824){
//                System.out.println(card.value+card.suit);
//                deck.cards.remove(x);
//                x = x-1;
//                //System.out.println(deck.cardsLeft()+"!");
//                decklength = deck.cardsLeft();
//            }
//        }
//
//        System.out.println(deck.cardsLeft() + "<3");
//        return deck;
//    }

    //toString method for deck/used in testing
    public String toString(){
        String message="";
        for (Card crd:deck) {
            message += "\n"+crd.name + " of " + crd.suit;

        }
        return message;
    }
    //Deals card at random from what is left in the deck
    public Card deal(){
        int cardsLeft = this.deck.size();
        int cardpick = rng.nextInt(cardsLeft);
        return deck.remove(cardpick);

    }
}