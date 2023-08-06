package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.items.Item;
import engine.positions.GameMap;
import engine.weapons.WeaponItem;

/**
 * An action that allows an actor to swap items with merchants
 */
public class SwapWeaponAction extends Action {
    private Item tradedItem;
    private WeaponItem receivedWeapon;
    public SwapWeaponAction(Item tradedItem, WeaponItem receivedWeapon) {
        this.tradedItem = tradedItem;
        this.receivedWeapon = receivedWeapon;

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
        actor.removeItemFromInventory(tradedItem);
        actor.addWeaponToInventory(receivedWeapon);
        return menuDescription(actor);
    }
    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return tradedItem.toString() + " has been traded for " + receivedWeapon.toString();
    }
}
