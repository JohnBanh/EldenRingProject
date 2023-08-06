package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.GameMap;
import engine.positions.Location;
import game.utils.ResetManager;
import game.actors.Player;

/**
 * ResetAction class, resets the game if executed.
 */

public class ResetAction extends Action {
    ResetManager resetManagerInstance;
    Location restLocation;

    /**
     * Constructor.
     * @param playerLocation
     */
    public ResetAction(Location playerLocation) {
        restLocation = playerLocation;
    }

    /**
     * gets the resetManagerInstance and resets the world when player executes the option
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player.getInstance().setSpawn(restLocation);
        resetManagerInstance = ResetManager.getInstance();
        this.resetManagerInstance.run(map,false);
        return "World and player has been reset!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " rests at Site Of Grace";
    }
}
