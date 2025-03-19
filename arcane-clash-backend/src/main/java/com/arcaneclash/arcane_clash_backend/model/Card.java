package com.arcaneclash.arcane_clash_backend.model;

import java.util.function.Consumer;

public class Card {
    
    private String name;
    private int goldCost;
    private int manaCost;
    private Faction faction;  
    private int gold;
    private int attack;
    private int mana;
    private int heal;
    private boolean usedAbility;
    private boolean usedCombo;
    private Consumer<Player> ability;
    private Consumer<Player> comboAbility;
    private int factionRequirement;

    
    public Card(String name, int goldCost, int manaCost, Faction faction, 
                int gold, int attack, int mana,
                Consumer<Player> ability, Consumer<Player> comboAbility) {
        this.name = name;
        this.goldCost = goldCost;
        this.manaCost = manaCost;
        this.faction = faction;  
        this.gold = gold;
        this.attack = attack;
        this.mana = mana;
        this.ability = ability;
        this.comboAbility = comboAbility;
    }

   
    public Card( String name, int goldCost, int manaCost, int factionId, int heal, int gold, int attack,
                int mana, String ability, String comboAbility) {
        this.name = name;
        this.goldCost = goldCost;
        this.manaCost = manaCost;
        this.faction = Faction.values()[factionId];  // Convert factionId to Faction enum
        this.heal = heal;
        this.gold = gold;
        this.attack = attack;
        this.mana = mana;

        
        this.ability = ability != null ? player -> System.out.println(ability) : null;  // Example placeholder
        this.comboAbility = comboAbility != null ? player -> System.out.println(comboAbility) : null;  // Example placeholder
    }

    

    public void play(Player player) {
        player.addGold(gold);
        player.addMana(mana);
        player.increaseAttack(attack);
    }

    public void activateAbility(Player player) {
        if (ability != null && !usedAbility) {
            ability.accept(player);
            this.usedAbility = true;
        }
        if (comboAbility != null && !usedCombo) {
            if (player.hasFactionCards(this.faction, this.factionRequirement)) {
                comboAbility.accept(player);
                this.usedCombo = true;
            }
        }
    }

    // Getters and Setters
    public String getName() { return name; }
    public int getGoldCost() { return goldCost; }
    public int getManaCost() { return manaCost; }
    public Faction getFaction() { return faction; }  
    public int getGold() { return gold; }
    public int getAttack() { return attack; }
    public int getMana() { return mana; }
    public int getHeal() { return heal; }

    public void setName(String name) { this.name = name; }
    public void setGoldCost(int goldCost) { this.goldCost = goldCost; }
    public void setManaCost(int manaCost) { this.manaCost = manaCost; }
    public void setFaction(Faction faction) { this.faction = faction; }  
    public void setGold(int gold) { this.gold = gold; }
    public void setAttack(int attack) { this.attack = attack; }
    public void setMana(int mana) { this.mana = mana; }
    public void setHeal(int heal) { this.heal = heal; }
}
