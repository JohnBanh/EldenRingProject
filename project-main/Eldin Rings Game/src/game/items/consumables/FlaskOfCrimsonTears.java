package game.items.consumables;

import engine.actors.Actor;
import engine.positions.GameMap;
import game.actions.ConsumeAction;
import game.utils.Resettable;

/**
 * A flask of crimson tears that can heal the consumer.
 */
public class FlaskOfCrimsonTears extends ConsumableItem implements Consumable, Resettable {
    private int healthRestoration;
    private int maxUses;
    private static FlaskOfCrimsonTears flaskOfCrimsonTears = null;

    public static FlaskOfCrimsonTears getInstance() {
        if(flaskOfCrimsonTears  == null){
            flaskOfCrimsonTears = new FlaskOfCrimsonTears();
        }
        return flaskOfCrimsonTears;
    }
    /**
     * Constructor
     */
    public FlaskOfCrimsonTears() {
        super("Flask Of Crimson Tears",'F',false, ConsumableStatus.PERMANENT,2);
        this.healthRestoration = 250;
        this.maxUses = 2;
    }
    /**
     * what happens when you consume the item
     * @param actor the actor that is consuming the consumable
     */
    @Override
    public void consume(Actor actor) {
        actor.heal(healthRestoration);
    }

    /**
     * refill the consumable
     */
    public void refill(){
        removeAction(this.getAllowableActions().get(0));
        addAction(new ConsumeAction(this, maxUses));
    }

    /**
     * reset method for the flask of crimson tears.
     * @param map the map that actor is currently on
     * @param playerDead true if actor is dead, false otherwise
     */
    @Override
    public void reset(GameMap map, boolean playerDead) {
        refill();
    }
}
