package com.arcaneclash.arcane_clash_backend.model;

import java.util.ArrayList;
import java.util.List;

public class TradeZone {

    private List<Card> cardsInMiddle;  
    private List<Card> deck;  

    public TradeZone(List<Card> deck) {
        this.cardsInMiddle = new ArrayList<>();
        this.deck = deck;
        initializeTradeZone();
    }

    // Initialize the trade zone with 5 cards from the deck
    private void initializeTradeZone() {
        for (int i = 0; i < 5; i++) {
            if (!deck.isEmpty()) {
                cardsInMiddle.add(deck.remove(0));  // Add a card from the deck to the middle
            }
        }
    }


    public boolean buyCard(Player player, Card cardToBuy) {
    
        if (cardsInMiddle.contains(cardToBuy)) {
            // Check if the player can afford the card
            if (player.getGold() >= cardToBuy.getGoldCost() && player.getMana() >= cardToBuy.getManaCost()) {
                // Deduct the resources
                player.addGold(-cardToBuy.getGoldCost());
                player.addMana(-cardToBuy.getManaCost());

                // Add the card to the player's discard pile
                player.addCardToDiscard(cardToBuy);

                // Replace the card in the middle with a new card from the deck
                replaceCardInMiddle(cardToBuy);

                return true;  
            }
        }
        return false;  
    }


    private void replaceCardInMiddle(Card cardToReplace) {
        if (!deck.isEmpty()) {
            int index = cardsInMiddle.indexOf(cardToReplace);
            if (index != -1) {
                cardsInMiddle.set(index, deck.remove(0));  // Replace the card in the middle with a new one
            }
        }
    }


    public List<Card> getCardsInMiddle() {
        return cardsInMiddle;
    }

    public List<Card> getDeck() {
        return deck;
    }
}
