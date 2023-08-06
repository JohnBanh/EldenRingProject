package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.GameMap;
import engine.weapons.Weapon;

import java.util.Random;

/**
 * Unsheathe Ability class. Doubles damage
 */

public class Unsheathe extends Action {
    private Weapon weapon;
    private Actor target;
    private String direction;
    private int damage;
    private final int hitrate = 60;// chance that the ability will hit

    private int damageMultiplier = 2; //This ability allows the weapon to do double the damage.

    private Random randomGenerator = new Random();

    /**
     * Constructor.
     * @param weapon
     * @param target
     * @param direction
     */
    public Unsheathe(Weapon weapon, Actor target, String direction){
        this.weapon = weapon;
        this.target = target;
        this.direction = direction;
        this.damage = weapon.damage() * this.damageMultiplier;
    }

    /**
     * method to execute the action when the player chooses to use the ability.
     * doubles the damage of the attack if successful
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return result in String
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //check if the attack is successful first
        if (!(randomGenerator.nextInt(100) <= this.hitrate)) {
            return actor + " misses " + target + ".";
        }
        //double the damage and attack Enemy
        String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
        if (!target.isConscious()) {
            result += "\n" + new DeathAction(actor).execute(target, map);
        }
        return result;

    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with " + weapon +"'s Unsheathe ability";
    }
}
