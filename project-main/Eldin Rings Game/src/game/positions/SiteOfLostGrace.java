package game.positions;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.positions.GameMap;
import engine.positions.Ground;
import engine.positions.Location;
import game.actions.ResetAction;

/**
 * class for Site of Lost Grace which is where the player can rest to reset the world and replinish health and potions
 * Created by:
 * @author Romal Patel
 */
public class SiteOfLostGrace extends Ground {
    /**
     * Constructor.
     */
    public SiteOfLostGrace() {
        super('U');
    }

    /**
     * allows the player to enter the site of lost grace
     * @param actor the Actor to check
     * @return
     */
    public boolean canActorEnter(Actor actor) {return true;}

    /**
     * to give the reset option only if the player is standing on the Site of Lost Grace.
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return the actionList containing the resetAction if the player is standing on top
     */
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actionList = new ActionList();

        GameMap map = location.map();
        Location playerLocation = map.locationOf(actor);
        if (location == playerLocation){

            actionList.add(new ResetAction(playerLocation));
        }
        return actionList;
    }

    @Override
    public void tick(Location location){}
}
