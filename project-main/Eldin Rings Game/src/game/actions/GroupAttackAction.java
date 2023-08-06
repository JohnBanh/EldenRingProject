package game.actions;

import java.util.ArrayList;
import java.util.Random;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.Exit;
import engine.positions.GameMap;
import engine.weapons.Weapon;

/**
 * An Action to attack another Actor.
 */
public class GroupAttackAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * The direction of incoming attack.
     */

    private String direction;

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Weapon used for the attack
     */
    private Weapon weapon;

    /**
     * Constructor
     * @param weapon being used
     * @param target target being attacked
     * @param direction direction of the actor
     */
    public GroupAttackAction(Weapon weapon, Actor target, String direction) {
        this.weapon = weapon;
        this.target = target;
        this.direction = direction;
    }

    /**
     * When executed, the chance to hit of the weapon that the Actor used is computed to determine whether
     * the actor will hit the target. If so, deal damage to the target and determine whether the target is killed.
     *
     * @param actor The actor performing the attack action.
     * @param map The map the actor is on.
     * @return the result of the attack, e.g. whether the target is killed, etc.
     * @see DeathAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ArrayList<String> locations = new ArrayList<>();
        ArrayList<Actor> actors = new ArrayList<>();
        for (Exit exit : map.locationOf(actor).getExits()) {
            if (map.getActorAt(exit.getDestination()) != null) {
                actors.add(map.getActorAt(exit.getDestination()));
                locations.add(exit.getName());
            }
        }
        int i;
        for (i = 0; i < locations.size(); i++){
            if (locations.get(i) == direction) {
                locations.remove(i);
                locations.add(0, direction);
                actors.remove(i);
                actors.add(0, target);
                break;

            }
        }
        for (i = 0; i < locations.size(); i++) {
            this.direction = locations.get(i);
            this.target = actors.get(i);
            executes(actor, map);
        }
        return actor.toString() + " area attack finished. ";
    }

    public String executes(Actor actor, GameMap map) {
        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }
        if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
            System.out.println(actor + " misses " + target + ".");
            return null;
        }
        int damage = weapon.damage();
        System.out.println( actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.");
        target.hurt(damage);
        if (!target.isConscious()) {
            System.out.println(new DeathAction(actor).execute(target, map));
        }

        return null;
    }

    /**
     * Describes which target the actor is attacking with which weapon
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with " + weapon + "'s spin Attack";
    }
}