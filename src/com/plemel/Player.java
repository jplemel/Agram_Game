package com.plemel;

import java.util.LinkedList;
/**
 * Created by Jennifer Plemel on 10/30/2016.
 */

public class Player {
    int playerName;
    LinkedList<Card> hand;

    //Constructor that takes an int as player name + initializes player hand

    public Player(int name){
        this.playerName= name;;
        this.hand= new LinkedList<>();
    }
}