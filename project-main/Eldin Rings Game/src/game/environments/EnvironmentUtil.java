package game.environments;

import engine.positions.GameMap;
import engine.positions.Location;

/**
 * Util class
 */
public class EnvironmentUtil {
    /**
     * calculates which side location is on
     * @param location
     * @return true if location is on left side
     */
    public static boolean leftSide(Location location) {
        GameMap gameMap = location.map();
        int mapWidth = gameMap.getXRange().max();
        int x = location.x();
        if (x < (mapWidth / 2)) {
            return true;
        }
        return false;
    }
}
