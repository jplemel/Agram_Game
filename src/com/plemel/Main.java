package com.plemel;

public class Main {

    public static void main(String[] args) {

        //Create deck for the game
        Deck deck = new Deck();

        //Create players for game
        PlayerController playerMgmt = new PlayerController(2, deck);


        System.out.println("Agram Game: Six rounds total");
        System.out.println("- Starting player leads with card of their choice");
        System.out.println("- Second player follows with their card (same suit if possible)");
        System.out.println("- The player with highest card of the round's suit wins the round");
        System.out.println("Winner starts next round, whoever wins the sixth and last trick wins the game! \n");

        //Calls round method 6 times (per Agram rules)
        for (int z= 0;z<6;z++){
            playerMgmt.round();
        }
        //Tell user who won the game
        System.out.println("Player "+playerMgmt.getLastRoundWinner()+" won the game");
    }
//    public static int getNumberOfPlayers (){
//        int numOfPlayers;
//
//        System.out.println("How many players?");
//        numOfPlayers = scanner.nextInt();
//
//        //There can't be more than 5 players for 1 deck
//        if (numOfPlayers > 5){
//
//            System.out.println("RULE: There can't be more than five players");
//            System.out.println("How many players?");
//            numOfPlayers = scanner.nextInt();
//        }
//
//        return numOfPlayers;
//    }
}