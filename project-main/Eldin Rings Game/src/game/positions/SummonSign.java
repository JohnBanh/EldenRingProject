package game.positions;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.positions.GameMap;
import engine.positions.Ground;
import engine.positions.Location;
import game.actions.SpawnAction;
/**
 * Summon Sign position class. ALlows user to execute SpawnAction when ontop or next to the sign
 */
public class SummonSign extends Ground {
    /**
     * constructor
     */
    public SummonSign(){
        super('=');
    }

    /**
     * whether actor can enter or not
     * @param actor the Actor to check
     * @return
     */
    public boolean canActorEnter(Actor actor){return true;}

    /**
     * Give action if Player is ontop or next to sign
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return
     */
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actionList = new ActionList();

        GameMap map = location.map();
        Location playerLocation = map.locationOf(actor);
        //get distance between summon sign and player
        if(actor != null) {
            int distance = distance(playerLocation, location);
            if (distance <= 2) {
                actionList.add(new SpawnAction(location));
            }
        }

        return actionList;
    }

    /**
     * to calc the distance between two location
     * @param a
     * @param b
     * @return
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

}
