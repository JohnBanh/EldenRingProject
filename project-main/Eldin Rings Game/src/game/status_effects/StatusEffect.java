package game.status_effects;

import engine.actors.Actor;
/**
 * A status effect that is bestowed upon an actor for a certain number of turns.
 */
public abstract class StatusEffect {
    private int duration;
    private EffectType effectType;

    public StatusEffect(int duration, EffectType effectType) {
        this.duration = duration;
        this.effectType = effectType;
    }
    /**
     * Applies the damage effect to the given actor.
     *
     * @param actor The actor to apply the effect to.
     */
    public abstract void apply(Actor actor);

    /**
     * reduces the number of turns the effect has
     */
    public void decrementDuration() {
        duration--;
    }
    /**
     * Checks if the damage effect has expired.
     *
     * @return True if the effect has expired, false otherwise.
     */
    public boolean isExpired() {
        return duration <= 0;
    }
}
