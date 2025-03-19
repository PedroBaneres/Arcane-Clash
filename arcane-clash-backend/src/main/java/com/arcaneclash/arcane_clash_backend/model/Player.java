


package com.arcaneclash.arcane_clash_backend.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;




public class Player {
    public int lifePoints = 40;
    private String name;
    private int gold;
    private int mana;
    private int attack;
    private int heal;
    private List<Card> discardPile;
    private List<Card> deck;
    private List<Card> hand;
    private List<Card> cardsInPlay;

    public Player(String name) {
        this.name = name;
        this.gold = 0;
        this.mana = 0;
        this.attack = 0;
        this.heal=0;
        this.deck = new ArrayList<>();
        this.discardPile = new ArrayList<>();
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
public void increaseHeal(int amount){
    this.heal += amount;
}
    public void addCardToDiscard( Card card){
    discardPile.add(card);
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
            card.play(this); 
            cardsInPlay.add(card);
        } 
    }

    public boolean hasFactionCards(Faction faction, int requiredCount) {
        long count = cardsInPlay.stream()
                                .filter(card -> card.getFaction() == faction)  // Filter cards by faction
                                .count();  // Count the number of matching cards
        return count >= requiredCount;  // Check if we have at least `requiredCount` cards
    }
    public void attack (Player player){
        player.lifePoints = player.lifePoints - this.attack;
        this.setAttack(0);    }
        public void heal(){
            this.lifePoints = this.lifePoints + this.heal;
            this.setHeal(0);    }
        

    
    public String getName() { return name; }
    public int getGold() { return gold; }
    public int getMana() { return mana; }
    public int getAttack() { return attack; }
    public int getLifePoints() {
return lifePoints;
    }
    public int getHeal() {
        return heal;
            }
    public List<Card> getDeck() { return deck; }
    public List<Card> getHand() { return hand; }
    public List<Card> getCardsInPlay() { return cardsInPlay; }
    public void setAttack(int attack){this.attack = attack;}
    public void setHeal(int heal){this.heal = heal;}
    public void setGold(int gold){this.gold = gold;}
    public void setMana(int mana){this.mana = mana;}
}
