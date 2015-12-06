
//********************************************************************
// EGR327 Project		CBU
// EvolvedPokemon.java	java class file for PokemonProject
// Created 11-2-15		David Larsen
// This class extends the Pokemon class to evolve an existing
// Pokemon object and change all of its attributes accordingly
// REVISION HISTORY:
// Date			By			Details
// 11-2-15		David		Created EvolvedPokemon.java and method logic
//********************************************************************

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JOptionPane;

public class EvolvedPokemon extends Pokemon {

//	public EvolvedPokemon(String nickname, String kindOfPokemon, int health,
//			int attack, int defense, int speed, boolean isEvolved) throws SQLException {
	public EvolvedPokemon(Pokemon p1) {
		super(nickname, kindOfPokemon, health, attack, defense, speed, isEvolved);
		
		// increase every attribute by a random amount
		increaseAttribute();
		
		// increase a random attribute again by a random amount
		abilityEffect();
		
	}

	// Increases the given attribute by a random factor between 1.0 and 1.5
	public double increaseAttribute() {
		Random rand = new Random();
		
		// multiply attribute by random number between 1 and 1.5
		double attackModifier = ((rand.nextDouble() / 2) + 1);
		
		return attackModifier;
	}
	
	// Randomly increases one of the pokemon's attributes
	private void abilityEffect() {
		Random rand = new Random();
		int attributeSelector = rand.nextInt(4);
	
	}
}
