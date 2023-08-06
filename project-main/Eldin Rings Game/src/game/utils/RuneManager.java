package game.utils;

import engine.actors.Actor;

import java.util.HashMap;
import java.util.Map;

/**
 * the manager of all runes in existence!
 */
public class RuneManager {
    private Map<Actor, Integer> runeCount = new HashMap<>();

    private static RuneManager runeManager = null;

    /**
     * Makes sure there is only one instance of the rune manager in existence.
     * @return an instance of rune manager
     */
    public static RuneManager getInstance(){
        if(runeManager == null){
            runeManager = new RuneManager();
        }
        return runeManager;
    }

    /**
     * Adds the actor and the associated rune balance to the hashmap
     * @param actor holder of runes
     * @param balance number of runes held
     */
    public void addActorToRuneManager(Actor actor, int balance){
        runeCount.put(actor, balance);
    }

    /**
     * Gets the balance of a specific instance of actor
     * @param actor holder of runes
     * @return the number of runes the holder owns
     */
    public int getBalance(Actor actor){
        return runeCount.get(actor);
    }

    /**
     * Adds the actor's rune balance with another balance
     * @param actor holder of runes
     * @param value the number of runes being added to the actor
     */
    public void addRunes(Actor actor, int value){
        int total = this.getBalance(actor) + value;
        runeCount.put(actor, total);
    }
    /**
     * Subtracts the actor's rune balance with another balance
     * @param actor holder of runes
     * @param value the number of runes being removed from the actor.
     */
    public void subRunes(Actor actor, int value){
        int total = this.getBalance(actor) - value;
        runeCount.put(actor, total);
    }

}
