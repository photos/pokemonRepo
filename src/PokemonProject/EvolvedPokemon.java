//********************************************************************
// Forrest Collins, Manuel Puentes, David Larsen

// Nov 2, 2015					EGR 327
// Time spent: 1 hour
// Purpose: This class extends the Pokemon class to evolve an existing
// 			Pokemon object and change all of its attributes accordingly
//********************************************************************
package PokemonProject;

import java.util.Random;

public class EvolvedPokemon extends Pokemon {

	public EvolvedPokemon(String nickname, String kindOfPokemon, int health,
			int attack, int defense, int speed, boolean isEvolved) {
		
		super(nickname, kindOfPokemon, health, attack, defense, speed, isEvolved);
		isEvolved = true;
		
		// increase every attribute by a random amount
		increaseAttribute(health);
		increaseAttribute(attack);
		increaseAttribute(defense);
		increaseAttribute(speed);
		
		// increase a random attribute again by a random amount
		abilityEffect();
	}

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
