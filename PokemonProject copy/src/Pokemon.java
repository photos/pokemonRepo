//********************************************************************
// Forrest Collins, Manuel Puentes, David Larsen

// Oct 20, 2015					EGR 327
// Time spent: 1 hr
// Purpose: (Not yet implemented) A pokemon object from a MySQL
//			database will be returned and its key values will
//			be used to construct a Pokemon object, which will
//			be accessed when evolving that Pokemon, which
//			will inherit the Pokemon class's member variables.
//			
//********************************************************************
import java.util.Date;

//----------------
// MARK: - Pokemon
//----------------
public class Pokemon {
	
	protected static String nickname;
	protected static String kindOfPokemon;
	protected static int health;
	protected static int attack;
	protected static int defense;
	protected static int speed;
	protected static boolean isEvolved;
	
	//-------------------------
	// Mark: - Main Constructor
	//-------------------------
	public Pokemon (String nickname, String kindOfPokemon, int health, int attack, int defense, int speed, boolean isEvolved){
		this.nickname = nickname;
		this.kindOfPokemon = kindOfPokemon;
		this.health = health;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.isEvolved = isEvolved;
	}
	
	//--------------------------
	// MARK: - Setters & Getters
	//--------------------------
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getKindOfPokemon() {
		return kindOfPokemon;
	}
	public void setKindOfPokemon(String kindOfPokemon) {
		this.kindOfPokemon = kindOfPokemon;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public boolean isEvolved() {
		return isEvolved;
	}
	public void setEvolved(boolean isEvolved) {
		this.isEvolved = isEvolved;
	}
}
