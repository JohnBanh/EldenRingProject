package game.items;

import engine.actors.Actor;
import engine.items.Item;
import engine.items.PickUpAction;
import engine.positions.Location;
import game.actors.Status;
import game.actions.RunesPickUpAction;

/**
 * a bag of Runes that have been dropped!
 */
public class DroppedRunes extends Item {
    private int runeValue;

    private Location previousLocation;
    private static DroppedRunes instance;

    /***
     * Constructor.
     * @param runeValue the number of runes that are being dropped
     */
    private DroppedRunes(int runeValue, Location previousLocation) {
        super("Runes", '$', true);
        this.runeValue = runeValue;
        this.previousLocation = previousLocation;
        this.addCapability(Status.CONTAINS_RUNES);
    }
    public static DroppedRunes getInstance(int runeValue, Location previousLocation){
        if (instance == null) {
            instance = new DroppedRunes(runeValue, previousLocation);
        }
        return instance;

    }

    /**
     * get the number of runes
     * @return int representing the number of runes
     */
    public int getRuneValue() {
        return runeValue;
    }

    public static DroppedRunes getInstance() {
        return instance;
    }

    public Location getPreviousLocation() {
        return previousLocation;
    }

    /**
     * Create and return an action to pick this Item up.
     * If this Item is not portable, returns null.
     *
     * @return a new PickUpItemAction if this Item is portable, null otherwise.
     */

    @Override
    public PickUpAction getPickUpAction(Actor actor) {
        //make it so that only actors capable of being HOSTILE_TO_ENEMY can pick up the dropped runes
        if (super.portable && actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            return new RunesPickUpAction(DroppedRunes.getInstance());
        }
        return null;
    }

}
