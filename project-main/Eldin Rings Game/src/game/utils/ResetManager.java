package game.utils;

import engine.positions.GameMap;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables.
 */
public class ResetManager {
    private List<Resettable> resettables;
    private static ResetManager instance;

    /**
     * ResetManager constructor. Privatised as there will only be one instance of ResetManager
     * and therefore constructor should not be called outside the class.
     */
    private ResetManager() {
        this.resettables = new ArrayList<>();
    }

    /**
     * method to retrieve the instance of the class if it exists already, or create a new one if it doesn't
     * @return the class instance
     */
    public static ResetManager getInstance(){
        if (instance == null) {
            instance = new ResetManager();
        }
        return instance;

    }

    /**
     * method to trigger the reset method for all resettables.
     * @param map
     */
    public void run(GameMap map, boolean playerDead) {
        for (Resettable resettable:resettables){
            resettable.reset(map, playerDead);
        }
    }

    /**
     * method to add a resettable to the resettable array
     * @param resettable
     */
    public void registerResettable(Resettable resettable) {
        this.resettables.add(resettable);
    }

    /**
     * method to remove a resettable to the resettable array
     * @param resettable
     */
    public void removeResettable(Resettable resettable) {
        this.resettables.remove(resettable);
    }
}
