package com.arcaneclash.arcane_clash_backend.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {
    private static final String DB_URL = "jdbc:mysql://192.168.56.101/arcane_clash";
    private static final String USERNAME = "admin00";
    private static final String PASSWORD = "alumno";

    // Method to establish database connection
    public Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }

    // Method to retrieve all the cards from the database
    public List<Card> getAllCards() {
        List<Card> cards = new ArrayList<>();
        String query = "SELECT * FROM cards";

        try (Connection connection = getDatabaseConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int goldCost = resultSet.getInt("goldCost");
                int manaCost = resultSet.getInt("manaCost");
                int factionId = resultSet.getInt("faction_id");
                int heal = resultSet.getInt("heal");
                int gold = resultSet.getInt("gold");
                int attack = resultSet.getInt("attack");
                int mana = resultSet.getInt("mana");
                String ability = resultSet.getString("ability");
                String comboAbility = resultSet.getString("comboAbility");

                // Create card object and add it to the list
                Card card = new Card(  name, goldCost, manaCost, factionId, heal, gold, attack, mana, ability, comboAbility);
                cards.add(card);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cards;
    }
}
