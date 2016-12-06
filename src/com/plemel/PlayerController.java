package com.plemel;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

/**
    Only allows for 1 human player and 1 computer player -- was trying to make it for more players,
    but couldn't get the rounds to work correctly

 */
public class PlayerController {

    //Initialize playercontroller properties

    LinkedList<Player> players = new LinkedList<>();

    private int starter;
    private int round = 1;


    public int getLastRoundWinner() {
        return lastRoundWinner;
    }
    private int lastRoundWinner;

    //Constructor for # of players and deck

    public PlayerController(int num, Deck deck) {
        for (int x = 1; x <= num; x++) {
            Player ply = new Player(x);
            players.add(ply);
        }

        int dealRounds = 0;
        //deals players hands
        while (dealRounds <= num) {
            for (Player player : players) {
                player.hand.add(deck.deal());
                player.hand.add(deck.deal());
                player.hand.add(deck.deal());
                dealRounds++;
            }
        }
        this.starter=0;
    }

    //Methods to play rounds of Agram game

    public void round() {

        //Initialize LinkList of Card used in the round
        LinkedList<Card> roundCardList = new LinkedList<>();

        //Identifies the card that sets suit
        Card leadCard;

        //Player 1 = Human Player
        Player human = players.get(0);

        //Makes clone of player list for the round
        LinkedList<Player> roundList = new LinkedList<>(this.players);

        //Get the player who starts the round
        Player plr = roundList.get(starter);

        //If 1st round is Human or Computer
        if (!Objects.equals(plr.playerName, human.playerName)) {

            //Lead Card Method (Strategy)
            leadCard = lead(plr);
            roundCardList.add(leadCard);
            System.out.println("Player " + plr.playerName + " played " + leadCard);

            //Remove player from list, so they only play once
            roundList.remove(plr);

        } else {



            System.out.println("Enter the number of the card you want to play");
            int x = 0;

            //Provides information to user about available cards
            for (Card ob : human.hand) {
                System.out.println(x + ". " + ob);
                x++;
            }
            //User input validation method
            int select = isValidData(human.hand);

            //Sets the lead card for round
            leadCard = human.hand.get(select);
            roundCardList.add(leadCard);
            roundList.remove(human);
        }
        //Gets computer card (if it didn't start the round)
        if (!roundList.contains(human)) {

            for (int x = 2; (roundList.size() != 0); (x)++) {

                LinkedList<Player> flwList = new LinkedList<>(roundList);

                for (Player pl : flwList) {

                    //Calls follow method
                    Card flw = follow(pl, leadCard);
                    //Provide card information to user
                    System.out.println("Player " + pl.playerName + " played " + flw);
                    //Add card to round list for winner evaluation
                    roundCardList.add(flw);
                    //Remove player from round
                    roundList.remove(pl);

                    if (flw.value > leadCard.value && Objects.equals(flw.suit, leadCard.suit)) {
                        //Changes winning card
                        leadCard = flw;
                    }
                }
            }
            //Gets user card (if they didn't start the round)
        } else {
            LinkedList<Card> playerRoundCardList = new LinkedList<>();
            System.out.println("Enter the number of the card you want to play");
            int display = 0;
            //identifies playable cards/follows rules
            for (Card c : human.hand) {
                if (Objects.equals(c.suit, leadCard.suit)) {
                    //adds cards to list
                    playerRoundCardList.add(c);
                }
            }
            //Displays playable cards
            for (Card c : playerRoundCardList) {
                System.out.println(display + ". " + c);
                display++;
            }
            //Displays all cards --  if player doesn't have any cards of the right suit then player loses
            if (playerRoundCardList.isEmpty()) {
                for (Card c : human.hand) {
                    System.out.println(display + ". " + c);
                    display++;
                    //adds cards to list
                    playerRoundCardList.add(c);
                }
            }
            //Gets valid data from user for card selection
            int select = isValidData(playerRoundCardList);

            //Gets card from user input
            Card selection = playerRoundCardList.get(select);
            roundCardList.add(selection);
            roundList.remove(human);


            //Identifies winning card
            if (selection.value > leadCard.value && Objects.equals(selection.suit, leadCard.suit)) {
                leadCard = selection;
            }
        }
        int winValue = 0;
        //Reviews card for winning value
        for (Card crd : roundCardList) {
            if (Objects.equals(crd.suit, leadCard.suit)) {
                if (crd.value > winValue) {
                    winValue = crd.value;
                }
            }
        }
        for (Player o : players) {

            for (Card a : o.hand) {
                //Finds player who has winning card in their hand
                if (a.value == winValue && Objects.equals(a.suit, leadCard.suit)) {
                    System.out.println("Player " + o.playerName + " won round " + round);

                    round++;

                    //Provides game winner value from final round
                    this.lastRoundWinner = o.playerName;
                    //Sets starter for next round
                    this.starter = o.playerName - 1;
                }
            }
        }
        //Remove played cards from player hands
        for (Player p : players) {
            for (Card cardd : roundCardList) {
                if (p.hand.contains(cardd)) {
                    p.hand.remove(cardd);
                }
            }
        }
    }


    //Determines card that has same suit as round leading card
    public Card follow(Player player, Card leadCard){
        LinkedList<Card> playerRoundCardList = new LinkedList<>();
        Card flw= player.hand.get(0);
        for (Card c:player.hand) {
            if (c.suit.equals(leadCard.suit)){
                playerRoundCardList.add(c);
            }
        }
        //Plays card with matching suit
        if (playerRoundCardList.size()!=0) {
            //tries to win round
            for (Card l : playerRoundCardList) {
                if (l.value > leadCard.value) {
                    flw = l;
                }
                //Plays lowest value of playable cards if win is not possible
                else {
                    int min = 12;
                    for (Card n : playerRoundCardList) {
                        if (n.value < min) {
                            min = n.value;
                            flw = n;
                        }
                    }
                }
            }
        }
        else {
            //Get lowest possible card value from hand to sacrifice
            for (Card m:player.hand){
                if (m.value<flw.value)
                {flw=m;}
            }
        }
        return flw;
    }


    //Strategy to get rid of low cards early/set suits
    //Last round determines who wins -- Player will have 1 card left

    public Card lead(Player player){
        int min=12;
        Card leadCard= player.hand.get(0);
        for (Card c:player.hand){
            if (c.value<min)
            {
                min=c.value;
                leadCard=c;
            }
        }
        return leadCard;
    }

    //Data validation --- checks that is valid number and is in the list displayed
    private int isValidData(LinkedList<Card>test) {
        Scanner scanner = new Scanner(System.in);

        String message="Invalid Entry! Please choose a number shown in the list";
        while (true) {
            try {
                String stringInput = scanner.nextLine();
                int intInput = Integer.parseInt(stringInput);
                Card tester=test.get(intInput);
                if (intInput >= 0) {
                    return intInput;
                } else {
                    System.out.println(message);
                    continue;
                }
            } catch (NumberFormatException ime) {
                System.out.println(message);
            }
            catch(IndexOutOfBoundsException iob){
                System.out.println(message);
            }
        }
    }
}
