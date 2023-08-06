package game.utils;

import engine.positions.GameMap;

/**
 * A resettable interface
 */
public interface Resettable {
    void reset(GameMap map, boolean playerDead);
}
