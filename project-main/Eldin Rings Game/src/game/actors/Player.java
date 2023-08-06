package game.actors;

import engine.actions.Action;
import engine.actions.ActionList;
import engine.actors.Actor;
import engine.displays.Display;
import engine.positions.GameMap;
import engine.displays.Menu;
import engine.positions.Location;

import game.items.DroppedRunes;
import game.utils.Resettable;
import game.actors.enemies.EnemyTypes;
import game.items.consumables.FlaskOfCrimsonTears;
import game.status_effects.StatusEffect;
import game.utils.ResetManager;
import game.utils.RuneManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * @author John Banh
 * Modified by:
 *
 */
public class Player extends Actor implements Resettable {

	private final Menu menu = new Menu();
	private static Location spawn;

	private static Player playerInstance;
	private List<StatusEffect> statusEffects;

	/**
	 * Constructor. Private as only one instance should exist, therefore this constructor does not
	 * need to be called outside the class
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	private Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(EnemyTypes.ALLY);
		ResetManager.getInstance().registerResettable(this); //Add player to the resettable array.
		FlaskOfCrimsonTears flaskOfCrimsonTears = FlaskOfCrimsonTears.getInstance();
		this.addItemToInventory(flaskOfCrimsonTears);
		statusEffects = new ArrayList<>();
		RuneManager runeManager = RuneManager.getInstance();
		runeManager.addActorToRuneManager(this,0);
	}
	public void addBuff(StatusEffect effect){
		statusEffects.add(effect);
	}
	public void removeBuff(StatusEffect effect){
		statusEffects.remove(effect);
	}
	/**
	 * method to retrieve the instance of the class if it exists already, or create a new one if it doesn't
	 *
	 * @param name
	 * @param displayChar
	 * @param hitPoints
	 * @return the instance of the class
	 */
	public static Player getInstance(String name, char displayChar, int hitPoints) {
		if (playerInstance == null) {
			playerInstance = new Player(name, displayChar, hitPoints);
		}
		return playerInstance;
	}

	/**
	 * to get the instance of the class if it exists, otherwise, return null.
	 * This is used for when the instance is needed without any parameters.
	 *
	 * @return
	 */
	public static Player getInstance() {
		if (playerInstance == null) {
			return null;
		}
		return playerInstance;
	}

	/**
	 * gives the player the actions that they can take in the current turn.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return menu consisting of the actions
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		statusEffects.removeIf(StatusEffect::isExpired);
		FlaskOfCrimsonTears flaskOfCrimsonTears = FlaskOfCrimsonTears.getInstance();
		for(StatusEffect effect: statusEffects){
				effect.apply(this);
		}
		System.out.println("Player HP: " + printHp());
		System.out.println("Tarnished Rune Count: " + (RuneManager.getInstance().getBalance(this)));

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null) {
			return lastAction.getNextAction();
		}
		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * get the spawn location of the player.
	 *
	 * @return the spawn location
	 */
	public Location getSpawn() {
		return spawn;
	}

	/**
	 * to set the spawn location of the player
	 *
	 * @param spawn
	 */
	public void setSpawn(Location spawn) {
		this.spawn = spawn;
	}

	/**
	 * reset method for the Player. Changes his location to the spawn location,
	 * resets the HP to max HP and
	 * replenishes flasks to max consumption
	 *
	 * @param map
	 */
	@Override
	public void reset(GameMap map, boolean playerDead) {
		RuneManager runeManager = RuneManager.getInstance();
		//reset player runes and drop runes when he dies.
		if(playerDead == true){
			//get the balance of the player
			int targetBalance = runeManager.getBalance(this);
			//make a new droppedRunes instance with the player's balance
			if(DroppedRunes.getInstance() != null){
				Location previousLocation = DroppedRunes.getInstance().getPreviousLocation();
				previousLocation.removeItem(DroppedRunes.getInstance());
			}
			DroppedRunes droppedRunes = DroppedRunes.getInstance(targetBalance, map.locationOf(this));
			//make the player's rune balance 0
			runeManager.subRunes(this,targetBalance);
			//add the dropped runes to where the player died
			map.locationOf(this).addItem(droppedRunes);
			System.out.println(this.toString() + " dropped " + targetBalance + " runes.");
		}
		//move the actor to the spawn location
		map.removeActor(this);
		map.addActor(this, this.getSpawn());
		//reset HP to max
		this.resetMaxHp(maxHitPoints);


	}
}

