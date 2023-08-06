package game.actors.guests;

import engine.actions.Action;
import engine.actions.ActionList;
import engine.actions.DoNothingAction;
import engine.actors.Actor;
import engine.displays.Display;
import engine.positions.GameMap;
import engine.weapons.WeaponItem;
import game.behaviours.AggressiveBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;
import game.actors.enemies.EnemyTypes;
import game.utils.ResetManager;
import game.utils.Resettable;

import java.util.HashMap;
import java.util.Map;
/**
 * A class for Ally, which attack Enemies but not the player. Similar to the Enemy Class with couple differences
 * Created by:
 * @author John Banh
 */
public class Ally extends Actor implements Resettable {
    public Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * Constructor
     * @param hitPoints
     * @param weapon
     */
    public Ally(int hitPoints, WeaponItem weapon) {
        super("Ally", 'A', hitPoints);
        super.addWeaponToInventory(weapon);
        this.addCapability(EnemyTypes.ALLY);
        this.behaviours.put(0, new AggressiveBehaviour());
        this.behaviours.put(1, new WanderBehaviour());
        ResetManager.getInstance().registerResettable(this); //Add all enemies to the resettable array.
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
     * We are to assume all enemies are resettable, therefore, we override the reset method as an
     * "overall" method. Enemies that are not resettable can have their own reset method in their own class.
     */
    @Override
    public void reset(GameMap map, boolean playerDead){
        if(playerDead == true){
            map.removeActor(this);
        }
    }
}

