package game.items.consumables;

import engine.actors.Actor;
import game.actors.Player;
import game.status_effects.DamageBuff;
import game.status_effects.EffectType;

/**
 * A type of consumable item called Sugon Mushroom that can be consumed by an actor.
 * Consuming this mushroom applies a damage buff effect to the player for a certain number of turns.
 */
public class SugonMushroom extends ConsumableItem implements Consumable{
    private int turnsRemaining;
    public SugonMushroom() {
        super("Sugon Mushroom", 'M', true, ConsumableStatus.NON_PERMANENT,1);
        this.turnsRemaining = 3;
    }
    /**
     * The effect of what happens when a consumable is consumed
     * @param actor the actor consuming
     */
    @Override
    public void consume(Actor actor) {
        Player player = Player.getInstance();
        assert player != null;
        player.addBuff(new DamageBuff(50,turnsRemaining, EffectType.POISON));
    }

}
