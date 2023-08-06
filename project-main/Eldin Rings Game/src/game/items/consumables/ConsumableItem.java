package game.items.consumables;

import engine.actors.Actor;
import engine.items.Item;
import game.actions.ConsumeAction;
/**
 * An item that can be consumed
 */
public abstract class ConsumableItem extends Item implements Consumable {
    private int maxConsumptions;

    public ConsumableItem(String name, char displayChar, boolean portable, ConsumableStatus consumableStatus, int maxConsumptions) {
        super(name, displayChar, portable);
        this.addCapability(consumableStatus);
        this.maxConsumptions = maxConsumptions;
        this.addAction(new ConsumeAction(this, maxConsumptions));
    }

    /**
     * The effect of what happens when a consumable is consumed
     *
     * @param actor the actor consuming
     */
    public void consume(Actor actor) {
    }
}