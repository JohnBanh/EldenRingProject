package game.positions;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.positions.Ground;
import engine.positions.Location;
import game.actions.TravelAction;

/**
 * A door which allows the player to traverse through maps
 */
public class FogDoor extends Ground {

    /**
     * The destination when travelling through the door
     */
    LocationStringPair locations;

    /**
     * Constructor.
     */
    public FogDoor(LocationStringPair locations) {
        super('D');
        this.locations = locations;
    }

    /**
     * a list of allowable actions that are given to an actor if nearby.
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if (location.getActor() == actor) {
            actionList.add(new TravelAction(locations));
        }
        return actionList;
    }
}
