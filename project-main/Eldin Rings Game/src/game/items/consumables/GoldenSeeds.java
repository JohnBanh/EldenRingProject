package game.items.consumables;

import engine.actors.Actor;

public class GoldenSeeds extends ConsumableItem implements Consumable{
    public GoldenSeeds() {
        super("Golden Seeds", 'O', true, ConsumableStatus.NON_PERMANENT,1);
    }
    /**
     * what happens when you consume the item
     * @param actor the actor that is consuming the consumable
     */
    @Override
    public void consume(Actor actor) {
        FlaskOfCrimsonTears flaskOfCrimsonTears = FlaskOfCrimsonTears.getInstance();
        flaskOfCrimsonTears.refill();
    }
}
