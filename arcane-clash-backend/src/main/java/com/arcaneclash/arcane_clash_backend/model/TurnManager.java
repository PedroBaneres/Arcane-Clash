package com.arcaneclash.arcane_clash_backend.model;

public class TurnManager {
    private Player currentPlayer;
    private Player opponent;
    private Game game;

    public TurnManager(Game game) {
        this.game = game;
    }

    // Start a new turn for the current player
    public void startTurn(Player player, Player opponent) {
        currentPlayer = player;
        this.opponent = opponent;
        System.out.println(currentPlayer.getName() + "'s turn begins!");

        
        

        
    }

    // End the current player's turn
    public void endTurn() {
      
        opponent.lifePoints -= currentPlayer.getAttack();
        

        currentPlayer.setAttack(0); 
        currentPlayer.setGold(0);   
        currentPlayer.getCardsInPlay().clear();  
        currentPlayer.getHand().clear();  
        
        for (int i = 0; i < 5; i++) {
            currentPlayer.drawCard();
        }
        
    

        startTurn(opponent, currentPlayer);
    }

    

    public void setPlayers(Player player1, Player player2) {
        this.currentPlayer = player1;
        this.opponent = player2;
    }
}
