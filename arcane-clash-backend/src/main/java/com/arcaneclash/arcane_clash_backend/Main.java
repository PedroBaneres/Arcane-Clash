package com.arcaneclash.arcane_clash_backend;

import com.arcaneclash.arcane_clash_backend.model.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Initialize players
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        // Create starting decks (8 Peasants, 2 Scouts)
        initializeStartingDeck(player1);
        initializeStartingDeck(player2);

        // Fetch cards from database
        DatabaseConnector db = new DatabaseConnector();
        List<Card> allCards = db.getAllCards();

        // Initialize game
        Game game = new Game(player1, player2, allCards);
        game.startGame();

        Player currentPlayer = player1;
        Player opponent = player2;

        while (true) {
            System.out.println("\n" + currentPlayer.getName() + "'s turn:");
            displayPlayerState(currentPlayer);

            // Player plays cards from hand
            System.out.println("Your hand: ");
            displayHand(currentPlayer);

            System.out.println("Enter the index of the card you want to play, or -1 to stop:");
            while (true) {
                int index = scanner.nextInt();
                if (index == -1) break;
                if (index >= 0 && index < currentPlayer.getHand().size()) {
                    Card card = currentPlayer.getHand().get(index);
                    currentPlayer.playCard(card);
                    System.out.println("Played: " + card.getName());
                } else {
                    System.out.println("Invalid choice.");
                }
            }

            // Buying phase
            System.out.println("Trade zone: ");
            displayTradeZone(game.getTradeZone());

            System.out.println("Enter the index of the card you want to buy, or -1 to stop:");
            while (true) {
                int index = scanner.nextInt();
                if (index == -1) break;
                if (index >= 0 && index < game.getTradeZone().getCardsInMiddle().size()) {
                    Card card = game.getTradeZone().getCardsInMiddle().get(index);
                    if (game.getTradeZone().buyCard(currentPlayer, card)) {
                        System.out.println("Bought: " + card.getName());
                    } else {
                        System.out.println("Not enough resources.");
                    }
                } else {
                    System.out.println("Invalid choice.");
                }
            }

            // End turn effects
            currentPlayer.attack(opponent);
            currentPlayer.heal();

            // Check win condition
            if (opponent.lifePoints <= 0) {
                System.out.println(currentPlayer.getName() + " wins!");
                break;
            }

            // Reset resources and draw cards
            currentPlayer.setGold(0);
            currentPlayer.setAttack(0);
            currentPlayer.setHeal(0);
            discardHand(currentPlayer);
            drawNewHand(currentPlayer);

            // Swap turns
            Player temp = currentPlayer;
            currentPlayer = opponent;
            opponent = temp;
        }

        scanner.close();
    }

    private static void initializeStartingDeck(Player player) {
        for (int i = 0; i < 8; i++) {
            player.addCardToDeck(new Card("Peasent",0,0,0,0,1,0,0,"",""));
        }
        for (int i = 0; i < 2; i++) {
            player.addCardToDeck(new Card("Scout", 0, 0, 0, 0, 0, 1, 0, null, null));
        }
        Collections.shuffle(player.getDeck());
    }

    private static void displayPlayerState(Player player) {
        System.out.println(player.getName() + " - Life: " + player.lifePoints + ", Gold: " + player.getGold() +
                ", Mana: " + player.getMana() + ", Attack: " + player.getAttack());
    }

    private static void displayHand(Player player) {
        List<Card> hand = player.getHand();
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i + ": " + hand.get(i).getName() + " (Gold: " + hand.get(i).getGold() +
                    ", Attack: " + hand.get(i).getAttack() + ")");
        }
    }

    private static void displayTradeZone(TradeZone tradeZone) {
        List<Card> tradeCards = tradeZone.getCardsInMiddle();
        for (int i = 0; i < tradeCards.size(); i++) {
            System.out.println(i + ": " + tradeCards.get(i).getName() + " (Cost: " + tradeCards.get(i).getGoldCost() + ")");
        }
    }

    private static void discardHand(Player player) {
        List<Card> hand = new ArrayList<>(player.getHand());
        for (Card card : hand) {
            player.addCardToDiscard(card);
        }
        player.getHand().clear();
    }

    private static void drawNewHand(Player player) {
        for (int i = 0; i < 5; i++) {
            player.drawCard();
        }
    }
}
