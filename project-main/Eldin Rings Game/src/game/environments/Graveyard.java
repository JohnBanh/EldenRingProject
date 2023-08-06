package game.environments;

import game.actors.enemies.HeavySkeletalSwordsman;
import game.actors.enemies.SkeletalBandit;

/**
 * Graveyard environment
 */
public class Graveyard extends Environment {
    /**
     * Constructor.
     */
    public Graveyard() {
        super('n');
        super.spawnChance = 27;
        addZoneActor(0, HeavySkeletalSwordsman::new);
        addZoneActor(1, SkeletalBandit::new);
    }
}
