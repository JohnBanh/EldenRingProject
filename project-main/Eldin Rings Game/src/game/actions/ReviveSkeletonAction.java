package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.GameMap;

/**
 * An Action that revives the actor
 */
public class ReviveSkeletonAction extends Action {

    /**
     * Constructor
     */
    public ReviveSkeletonAction() {
    }

    /**
     * when this action is executed, it will return "Skeleton has been revived"
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the String describing that the current actor has revived
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return menuDescription(actor);
    }

    /**
     * In the menu, the description for this action will be "Skeleton has been revived"
     *
     * @param actor The actor performing the action.
     * @return the String "Skeleton has been revived" to be shown in the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Skeleton has been revived";
    }
}