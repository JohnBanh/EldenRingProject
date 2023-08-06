package game.actors.Traders;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.positions.GameMap;
import game.actions.BuyWeaponAction;
import game.weapons.Club;
import game.weapons.GreatKnife;
import game.weapons.Uchigatana;

public class MerchantKale extends Trader {
    /**
     * Constructor.
     */
    public MerchantKale() {
        super("Merchant Kale",'K',1);
    }

    /**
     * a list of allowable actions that are given to an actor if nearby.
     * @param otherActor the Actor acting
     * @param direction the direction of the Ground from the Actor
     * @param map the map
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        ActionList actionList = new ActionList();
        //add the ability for the player to buy items
        if (canTrade(otherActor,map.locationOf(this))) {
            actionList.add(new BuyWeaponAction(5000, new Uchigatana()));
            actionList.add(new BuyWeaponAction(3500, new GreatKnife()));
            actionList.add(new BuyWeaponAction(600, new Club()));
            }
        return actionList;
    }
}
