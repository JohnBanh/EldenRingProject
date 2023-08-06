package game.items;

import engine.actors.Actor;
import engine.items.Item;
import engine.positions.Exit;
import engine.positions.GameMap;
import engine.positions.Location;
import game.actions.SwapWeaponAction;
import game.actors.Status;
import game.weapons.AxeOfGodrick;
import game.weapons.GraftedDragon;
/**
 * An item that can be traded for Axe of Godrick or Grafted Dragon.
 *
 */
public class RemembranceOfTheGrafted extends Item {
    SwapWeaponAction swapForAxe,swapForGraftedDragon;

    public RemembranceOfTheGrafted() {
        super("Remembrance of the Grafted", '0', true);
    }
    /**
     * Perform the ticking action of the sellable weapon.
     * It checks the exits of the current location and allows the actor to trade the item for another
     * if the actor(exit) has the CAN_TRADE status.
     *
     * @param currentLocation the current location of the sellable weapon
     * @param actor           the actor that holds the sellable weapon
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if(swapForAxe != null && swapForGraftedDragon != null){
            this.removeAction(swapForAxe);
            this.removeAction(swapForGraftedDragon);
        }

        GameMap map = currentLocation.map();
        for(Exit exit: currentLocation.getExits()){
            if (exit.getDestination().containsAnActor()){
                Actor someActor = map.getActorAt(exit.getDestination());
                if(someActor.hasCapability(Status.CAN_TRADE) && actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
                    swapForAxe = new SwapWeaponAction(this,new AxeOfGodrick());
                    swapForGraftedDragon = new SwapWeaponAction(this,new GraftedDragon());
                    addAction(swapForAxe);
                    addAction(swapForGraftedDragon);
                    break;
                }
            }
        }
    }
}
