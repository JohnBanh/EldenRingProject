package game.items.consumables;

import engine.actors.Actor;

public class GodGodFruit extends ConsumableItem implements Consumable{
    public GodGodFruit() {
        super("God God Fruit", 'G', true, ConsumableStatus.NON_PERMANENT,1);
    }

    /**
     * what happens when you consume the item
     * @param actor the actor that is consuming the consumable
     */
    @Override
    public void consume(Actor actor) {
        actor.increaseMaxHp(10000);
        actor.heal(10000);
    }
}
