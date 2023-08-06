package game.environments;

import game.actors.enemies.GiantCrab;
import game.actors.enemies.GiantCrayfish;

/**
 * Puddle of Water environment
 */
public class PuddleOfWater extends Environment {
    /**
     * Constructor.
     */
    public PuddleOfWater() {
        super('~');
        super.spawnChance = 2;
        addZoneActor(0, GiantCrab::new);
        addZoneActor(1, GiantCrayfish::new);
    }
}
