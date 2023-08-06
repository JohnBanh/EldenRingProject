package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.GameMap;
import engine.weapons.WeaponItem;
import game.utils.RuneManager;

/**
 * Allows an actor to sell an item.
 */
public class SellWeaponAction extends Action {
    private int price;
    private WeaponItem weaponItem;
    public SellWeaponAction(int price, WeaponItem weaponItem) {
        this.price = price;
        this.weaponItem = weaponItem;
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
        //add the runes to actor's rune count
        RuneManager runeManager = RuneManager.getInstance();
        runeManager.addRunes(actor,price);
        //remove weapon from actor's inventory
        actor.removeWeaponFromInventory(weaponItem);
        return menuDescription(actor);
    }
    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        String description = actor.toString() + " receives " + price + " runes";
        description += " for selling " + weaponItem.toString();
        return description;
    }
}
