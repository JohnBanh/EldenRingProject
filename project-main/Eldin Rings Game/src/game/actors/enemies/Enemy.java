package game.actors.enemies;

import engine.actions.Action;
import engine.actions.ActionList;
import engine.actions.DoNothingAction;
import engine.actors.Actor;
import engine.displays.Display;
import engine.positions.GameMap;
import engine.weapons.WeaponItem;
import game.behaviours.AggressiveBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.actions.AttackAction;
import game.actors.Status;
import game.utils.ResetManager;
import game.utils.Resettable;
import game.utils.RuneManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An Abstract Enemy Class which lets the player attack and attacks other enemies
 * Created by:
 * @author John Banh
 */
public abstract class Enemy extends Actor implements Resettable {

    /**
     * Creates an array of behaviours
     */
    public Map<Integer, Behaviour> behaviours = new HashMap<>();

    private int runeCount;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints, int runeCount) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(0, new AggressiveBehaviour());
        this.behaviours.put(1, new FollowBehaviour());
        this.behaviours.put(2, new WanderBehaviour());
        this.runeCount = runeCount;
        ResetManager.getInstance().registerResettable(this); //Add all enemies to the resettable array.
        RuneManager.getInstance().addActorToRuneManager(this,runeCount); //Add actor to the runemanager hashmap
    }


    /**
     * gets the number of runes that the enemy is worth.
     *
     * @return int representing rune value.
     */
    public int getRuneCount() {
        return runeCount;
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * The enemy can be attacked by any actor that has the HOSTILE_TO_ENEMY capability and allows player to choose which weapons and kills to use
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
            List<WeaponItem> weapons = otherActor.getWeaponInventory();
            for (WeaponItem weapon : weapons) {
                actions.add(new AttackAction(this, direction, weapon));
                Action uniqueSkill = weapon.getSkill(this, direction);
                actions.add(uniqueSkill);
                }
            // HINT 1: The AttackAction above allows you to attack the enemy with your intrinsic weapon.
            // HINT 1: How would you attack the enemy with a weapon?
            }
        return actions;
    }

    /**
     * We are to assume all enemies are resettable, therefore, we override the reset method as an
     * "overall" method. Enemies that are not resettable can have their own reset method in their own class.
     */
    @Override
    public void reset(GameMap map, boolean playerDead){
        map.removeActor(this); //removes the actor. In this case this -> the current Enemy; which will be removed.
    }
}

