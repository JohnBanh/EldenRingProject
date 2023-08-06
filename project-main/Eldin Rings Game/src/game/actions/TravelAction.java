package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.GameMap;
import game.positions.LocationStringPair;

/**
 * Allows the player to travel to a new map
 */
public class TravelAction extends Action {
    /**
     * Location of destination
     */
    LocationStringPair location;

    public TravelAction(LocationStringPair location){
        this.location = location;
    }

    /**
     * Move the actor
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String of where to actor teleported
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Actor actorAtLoc = location.getLocation().getActor();
            if (actorAtLoc != null){
                location.getLocation().map().removeActor(actorAtLoc);
            }
        map.moveActor(actor, location.getLocation());
        return menuDescription(actor);
    }

    /**
     * To string method
     * @param actor The actor performing the action.
     * @return teleports message
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " teleports to " + location.getStringValue();
    }
}
