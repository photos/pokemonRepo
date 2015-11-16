//********************************************************************
// Forrest Collins, Manuel Puentes, David Larsen

// Nov 2, 2015					EGR 327
// Time spent: 1 hour
// Purpose: This class extends the Pokemon class to evolve an existing
// 			Pokemon object and change all of its attributes accordingly
//********************************************************************
package PokemonProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JOptionPane;

public class EvolvedPokemon extends Pokemon {

	public EvolvedPokemon(String nickname, String kindOfPokemon, int health,
			int attack, int defense, int speed, boolean isEvolved) throws SQLException {
		
		super(nickname, kindOfPokemon, health, attack, defense, speed, isEvolved);
		isEvolved = true;
		
		Connection myConnection = null;
		PreparedStatement myStatement = null;
		// Get a connection to the MySQL database
		myConnection = DriverManager.getConnection("jdbc:mysql://localhost/pokemonschema", "student", "student");
		System.out.println("Database connection successful!");
		
		// Create a statement to "insert" a new Pokemon object in the database
		// "Pokemon" in this insert statement is the name of the table in my MySQL database
		myStatement = myConnection.prepareStatement("");
		
		myStatement.setString(1, nickname);
		myStatement.setString(2, kindOfPokemon);
		myStatement.setInt(3, health);
		myStatement.setInt(4, attack);
		myStatement.setInt(5, defense);
		myStatement.setInt(6, speed);
		
		// increase every attribute by a random amount
		increaseAttribute(health);
		increaseAttribute(attack);
		increaseAttribute(defense);
		increaseAttribute(speed);
		
		// increase a random attribute again by a random amount
		abilityEffect();
		
		
		
/******************************************************************************************************/		
/******************************************************************************************************/		
		//Have to change the code below because saveDataOutput does nothing and I have no clue what its for
		
		int saveDataOutput = 0;
			
		// If there is new data in the database, let the user know their data was saved
		myStatement.executeUpdate("UPDATE pokemonschema.pokemon " + "SET health = " + getHealth() +
				  "WHERE nickname=" + nickname);
		
		myStatement.executeUpdate("UPDATE pokemonschema.pokemon " + "SET attack = " + getAttack() +
				  "WHERE nickname=" + nickname);
		
		myStatement.executeUpdate("UPDATE pokemonschema.pokemon " + "SET defense = " + getDefense() +
				  "WHERE nickname=" + nickname);
		
		myStatement.executeUpdate("UPDATE pokemonschema.pokemon " + "SET speed = " + getSpeed() +
				  "WHERE nickname=" + nickname);
		if (saveDataOutput > 0){
			JOptionPane.showMessageDialog(null, "Data is saved.");
		} else {
			JOptionPane.showMessageDialog(null, "Data is not saved.");
		}
	}
	
/******************************************************************************************************/
/******************************************************************************************************/
	
	
	
	

	// Increases the given attribute by a random factor between 1.0 and 1.5
	private void increaseAttribute(int attribute) {
		Random rand = new Random();
		
		// multiply attribute by random number between 1 and 1.5
		attribute *= ((rand.nextDouble() / 2) + 1);
	}
	
	// Randomly increases one of the pokemon's attributes
	private void abilityEffect() {
		Random rand = new Random();
		int attributeSelector = rand.nextInt(4);
		
		switch (attributeSelector) {
			case 0: increaseAttribute(health);
				break;
			case 1: increaseAttribute(attack);
				break;
			case 2: increaseAttribute(defense);
				break;
			case 3: increaseAttribute(speed);
				break;
			default:
				break;
		}
	}
	
	
	
}
