package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.GameMap;
import engine.weapons.WeaponItem;
import game.utils.RuneManager;

/**
 * an action that allows the player buy an item.
 */
public class BuyWeaponAction extends Action {
    private int price;
    private WeaponItem item;
    private String itemName;
    public BuyWeaponAction(int price, WeaponItem item) {
        this.price = price;
        this.item = item;
        this.itemName = item.getClass().getSimpleName();
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
        //get balance of actor
        RuneManager runeManager = RuneManager.getInstance();
        int balance = runeManager.getBalance(actor);
        //if the actor has enough money buy the weapon (add weapon to inventory, subtract price from balance)
        if(balance >= price){
            runeManager.subRunes(actor,price);
            actor.addWeaponToInventory(item);
            return menuDescription(actor);
        }else{
            return "Purchase Failed. Insufficient rune count.";
        }
    }
    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " buys " + itemName + " for " + price + " runes";
    }
}
