package game.utils;

import engine.positions.Location;

public class Utils {
    /**
     * distance between a and b
     * @param a location 1
     * @param b location 2
     * @return an integer representing the distance between two actors.
     */
    public static int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
