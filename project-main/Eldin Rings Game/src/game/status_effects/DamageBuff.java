package game.status_effects;

import engine.actors.Actor;
/**
 * A status effect that applies damage to an actor for a certain number of turns.
 */
public class DamageBuff extends StatusEffect{
    private int damageAmount;
    private int remainingTurns;
    /**
     * Constructs a new DamageBuff with the specified damage amount and number of turns.
     *
     * @param damageAmount   The amount of damage to apply.
     * @param numTurns       The number of turns the damage effect lasts.
     */
    public DamageBuff(int damageAmount, int numTurns,EffectType effectType) {
        super(numTurns,effectType);
        this.damageAmount = damageAmount;
        this.remainingTurns = numTurns;

    }
    /**
     * Applies the damage effect to the given actor.
     *
     * @param actor The actor to apply the effect to.
     */
    @Override
    public void apply(Actor actor) {
            actor.hurt(damageAmount);
            decrementDuration();
            System.out.println("Player takes " + damageAmount + " damage from Poison. Turns remaining: " + remainingTurns);
    }
}
