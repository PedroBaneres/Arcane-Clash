


package com.arcaneclash.arcane_clash_backend.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.smartcardio.Card;

public class Player {
    private String name;
    private int gold;
    private int mana;
    private int attack;
    private List<Card> discardPile;
    private List<Card> deck;
    private List<Card> hand;
    private List<Card> cardsInPlay;

    public Player(String name) {
        this.name = name;
        this.gold = 0;
        this.mana = 0;
        this.attack = 0;
        this.deck = new ArrayList<>();
        this.discard = new ArrayList<>();
        this.hand = new ArrayList<>();
        this.cardsInPlay = new ArrayList<>();
    }

   
    public void addGold(int amount) {
        this.gold += amount;
    }

    public void addMana(int amount) {
        this.mana += amount;
    }

    public void increaseAttack(int amount) {
        this.attack += amount;
    }


    public void addCardToDeck(Card card) {
        deck.add(card);
    }

    public void drawCard() {
    if (!deck.isEmpty()) {
        // Draw normally
        Card drawnCard = deck.remove(0);
        hand.add(drawnCard);
    } else if (!discardPile.isEmpty()) {
        
        // Shuffles discard pile into deck and draw again
        deck.addAll(discardPile);
        discardPile.clear();
        Collections.shuffle(deck);
        drawCard(); 
    } else {
        // If discard is empty we dont draw anymore, this prevents looping, not implemented yet
        
    }
}

    public void playCard(Card card) {
        if (hand.contains(card)) {
            hand.remove(card);
            cardsInPlay.add(card);
            card.play(this); 
        } 
    }

    public boolean hasFactionCard(String faction) {
        return cardsInPlay.stream().anyMatch(c -> c.getFaction().equals(faction));
    }

    
    public String getName() { return name; }
    public int getGold() { return gold; }
    public int getMana() { return mana; }
    public int getAttack() { return attack; }
    public List<Card> getDeck() { return deck; }
    public List<Card> getHand() { return hand; }
    public List<Card> getCardsInPlay() { return cardsInPlay; }
}
