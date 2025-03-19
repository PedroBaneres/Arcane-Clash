package com.arcaneclash.arcane_clash_backend.model;

import java.util.List;

public class Game {
    private Player player1;
    private Player player2;
    private TurnManager turnManager;
    private TradeZone tradeZone;

    public Game(Player player1, Player player2, List<Card> deck) {
        this.player1 = player1;
        this.player2 = player2;
        this.tradeZone = new TradeZone(deck);  
        this.turnManager = new TurnManager(this);
    }

    public void startGame() {
        
        player1.drawCard();  
        player1.drawCard();
        player1.drawCard();

        player2.drawCard();  
        player2.drawCard();
        player2.drawCard();
        player2.drawCard();
        player2.drawCard();
        player2.drawCard();

        turnManager.startTurn(player1,player2);  
    }

    public void endGame(Player winner) {
        
        System.out.println(winner.getName() + " wins!");
    }

    public TradeZone getTradeZone() {
        return tradeZone;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
