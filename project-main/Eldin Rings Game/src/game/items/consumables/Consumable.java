package game.items.consumables;

import engine.actors.Actor;
/**
 * Behaviours for consumables
 */
public interface Consumable {
    /**
     * The effect of what happens when a consumable is consumed
     * @param actor the actor consuming
     */
    void consume(Actor actor);

    /**
     * check if a consumable has a particular capability
     * @param capability
     * @return true if it has the particular capability, false otherwise
     */
    boolean hasCapability(Enum<?> capability);
}
