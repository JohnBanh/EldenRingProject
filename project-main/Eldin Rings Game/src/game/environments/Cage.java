package game.environments;

import game.actors.enemies.Dog;

/**
 * Cage environment
 */
public class Cage extends Environment {
    /**
     * Constructor.
     */
    public Cage() {
        super('<');
        super.spawnChance = 37;
        addZoneActor(0, Dog::new);
    }
}