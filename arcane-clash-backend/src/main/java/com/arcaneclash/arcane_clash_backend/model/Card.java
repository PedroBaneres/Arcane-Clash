package com.arcaneclash.arcane_clash_backend.model;

import java.util.function.Consumer;

public class Card {
    
    private String name;
    private int goldCost;
    private int manaCost;
    private Faction faction;  // Updated to use enum
    private int gold;
    private int attack;
    private int mana;

    private Consumer<Player> ability;
    private Consumer<Player> comboAbility;

    public Card(String name, int goldCost, int manaCost, Faction faction, 
                int gold, int attack, int mana,
                Consumer<Player> ability, Consumer<Player> comboAbility) {
        this.name = name;
        this.goldCost = goldCost;
        this.manaCost = manaCost;
        this.faction = faction;  // Now using enum
        this.gold = gold;
        this.attack = attack;
        this.mana = mana;
        this.ability = ability;
        this.comboAbility = comboAbility;
    }

    // Play the card and apply its effects
    public void play(Player player) {
        player.addGold(gold);
        player.addMana(mana);
        player.increaseAttack(attack);

        if (ability != null) {
            ability.accept(player);
        }
    }

    // Play the card with a combo effect
    public void playWithCombo(Player player) {
        play(player);

        if (comboAbility != null) {
            comboAbility.accept(player);
        }
    }

    // Getters and Setters
    public String getName() { return name; }
    public int getGoldCost() { return goldCost; }
    public int getManaCost() { return manaCost; }
    public Faction getFaction() { return faction; }  // Updated getter
    public int getGold() { return gold; }
    public int getAttack() { return attack; }
    public int getMana() { return mana; }

    public void setName(String name) { this.name = name; }
    public void setGoldCost(int goldCost) { this.goldCost = goldCost; }
    public void setManaCost(int manaCost) { this.manaCost = manaCost; }
    public void setFaction(Faction faction) { this.faction = faction; }  // Updated setter
    public void setGold(int gold) { this.gold = gold; }
    public void setAttack(int attack) { this.attack = attack; }
    public void setMana(int mana) { this.mana = mana; }
}
