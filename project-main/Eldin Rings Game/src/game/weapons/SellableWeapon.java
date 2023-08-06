package game.weapons;

import engine.actors.Actor;
import engine.positions.Exit;
import engine.positions.GameMap;
import engine.positions.Location;
import engine.weapons.WeaponItem;
import game.actions.SellWeaponAction;
import game.actors.Status;

/**
 * A sellable weapon that can be sold to a trader.
 */
public abstract class SellableWeapon extends WeaponItem {
    protected final int price;
    protected SellWeaponAction sellWeaponAction;
    public SellableWeapon(String name, char displayChar, int damage, String verb, int hitRate, int price) {
        super(name, displayChar, damage, verb, hitRate);
        this.price = price;
    }
    /**
     * Perform the ticking action of the sellable weapon.
     * It checks the exits of the current location and allows the actor to sell the item to a trader
     * if the actor has the CAN_TRADE status.
     *
     * @param currentLocation the current location of the sellable weapon
     * @param actor           the actor that holds the sellable weapon
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        //make sure there is only ever one sellAction
        if(sellWeaponAction != null){
            this.removeAction(sellWeaponAction);
        }

        GameMap map = currentLocation.map();
        for(Exit exit: currentLocation.getExits()){
            if (exit.getDestination().containsAnActor()){
                Actor someActor = map.getActorAt(exit.getDestination());
                if(someActor.hasCapability(Status.CAN_TRADE) && actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
                    sellWeaponAction = new SellWeaponAction(this.price,this);
                    addAction(sellWeaponAction);
                    break;
                }
            }
        }
    }
}
