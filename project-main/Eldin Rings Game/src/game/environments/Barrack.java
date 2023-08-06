package game.environments;

import game.actors.enemies.GodrickSoldier;

/**
 * Barrack environment
 */
public class Barrack extends Environment {
    /**
     * Constructor.
     */
    public Barrack() {
        super('B');
        super.spawnChance = 45;
        addZoneActor(0, GodrickSoldier::new);
    }
}