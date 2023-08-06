package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.items.Item;
import engine.positions.GameMap;
import game.items.consumables.Consumable;
import game.items.consumables.ConsumableStatus;

/**
 * Allows an actor to consume a consumable.
 */
public class ConsumeAction extends Action {
    private Consumable consumable;
    private int usesLeft;
    private int maxUses;

    public ConsumeAction(Consumable consumable,int usesLeft) {
        this.consumable = consumable;
        this.usesLeft = usesLeft;
        this.maxUses = usesLeft;
    }
    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(usesLeft > 0){
            consumable.consume(actor);
            usesLeft--;
            if(usesLeft == 0 && consumable.hasCapability(ConsumableStatus.NON_PERMANENT)){
                actor.removeItemFromInventory((Item) consumable);
            }
        return menuDescription(actor);
        }else{
            return consumable + " is empty";
        }
    }
    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " consumes " + consumable + ". (uses left: " + usesLeft + "/" + maxUses + ")";

    }

}
