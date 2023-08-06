package game.environments;

import game.actors.enemies.GiantDog;
import game.actors.enemies.LoneWolf;

/**
 * Gust Of Wind environment
 */
public class GustOfWind extends Environment {
    /**
     * Constructor.
     */
    public GustOfWind() {
        super('&');
        super.spawnChance = 33;
        addZoneActor(0, LoneWolf::new);
        addZoneActor(1, GiantDog::new);
    }
}