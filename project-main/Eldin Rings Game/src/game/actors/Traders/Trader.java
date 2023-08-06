package game.actors.Traders;

import engine.actions.Action;
import engine.actions.ActionList;
import engine.actions.DoNothingAction;
import engine.actors.Actor;
import engine.displays.Display;
import engine.positions.GameMap;
import engine.positions.Location;
import game.actors.Status;
import game.utils.Utils;

/**
 * An entity that particular actors can trade with.
 */
public abstract class Trader extends Actor {

    public Trader(String name, char displayChar,int hitPoints) {
        super(name, displayChar,hitPoints);
        this.addCapability(Status.CAN_TRADE);
    }

    /**
     * determines whether the actor is close enough to trade with the trader
     * @param actor the actor that is being compared to the trader
     * @param location the current Location
     * @return true if the actor can trade with the trader or false if the actor cannot trade with the trader
     */
    public boolean canTrade(Actor actor, Location location){
        GameMap map = location.map();
        //get distance between merchant and player
        Location playerLocation = map.locationOf(actor);
        if(actor != null) {
            int distance = Utils.distance(playerLocation, location);
            if (distance <= 2) {
                return true;
                }
            }
        return false;
    }
    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

}
