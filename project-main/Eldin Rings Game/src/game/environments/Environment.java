package game.environments;

import engine.actors.Actor;
import engine.positions.Ground;
import engine.positions.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

/**
 * A class which calculates which side of map and random spawn chances
 */
public abstract class Environment extends Ground {

    /**
     * random number generator
     */
    public Random rand = new Random();

    /**
     * Holds actors
     */
    private final Map<Integer, Supplier<Actor>> zoneActors = new HashMap<>();

    /**
     * chance to spawn
     */
    public int spawnChance;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Environment(char displayChar) {
        super(displayChar);
    }


    /**
     * Spawns actors
     * @param location The location of the Ground
     */
    public void tick(Location location) {
        super.tick(location);
        if (rand.nextInt(100) <= spawnChance && location.getActor() == null) {
            int zone = calculateZone(location);
            Supplier<Actor> actorSupplier = zoneActors.get(zone);
            if (actorSupplier != null) {
                location.addActor(actorSupplier.get());
            }
        }
    }

    /**
     * Adds possible enemies
     * @param zone environment location
     * @param actorSupplier enemy for that location
     */
    protected void addZoneActor(int zone, Supplier<Actor> actorSupplier) {
        zoneActors.put(zone, actorSupplier);
    }

    /**
     * Calculating location
     * @param location location
     * @return key for enemy
     */
    protected int calculateZone(Location location) {
        if (!EnvironmentUtil.leftSide(location)){
                return 1;
        }
        return 0;
    }
}