package game.actions;

import engine.actions.Action;
import engine.actions.MoveActorAction;
import engine.actors.Actor;
import engine.positions.Exit;
import engine.positions.GameMap;
import engine.positions.Location;
import engine.weapons.Weapon;

/**
 * Quickstep Ability class. Attacks an enemy and moves away first accessible location
 */

public class Quickstep extends Action {
    private Weapon weapon;
    private Actor target;
    private String direction;
    private int damage;

    /**
     * Constructor.
     * @param weapon
     * @param target
     * @param direction
     */
    public Quickstep(Weapon weapon, Actor target, String direction){
        this.target =target;
        this.direction = direction;
        this.weapon = weapon;
        this.damage = weapon.damage();
    }

    /**
     * method to execute the action when the player chooses to use the ability.
     * attacks the enemy and moves away to the first accessible exit
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Action step = null;
        for(Exit moveableLocations : map.locationOf(actor).getExits()){ //For each moveable location that can be moved to in the exits of the current location do:
            Location destination = moveableLocations.getDestination();
            if(!destination.containsAnActor()){ //Make sure the next destination does not contain another actor (e.g enemy)
                String nextDirection = moveableLocations.getName();
                step = new MoveActorAction(destination, nextDirection);
                break; //need to break or player will keep moving till there is no Actor.
            }
        }
        String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
        if (!target.isConscious()) {
            result += "\n" + new DeathAction(actor).execute(target, map);
        }
        if(step!=null){
            result += "\n"+step.execute(actor,map);
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with " + weapon +"'s Quickstep ability";

    }
}
