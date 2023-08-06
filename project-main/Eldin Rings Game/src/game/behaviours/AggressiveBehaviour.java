package game.behaviours;

import engine.actors.Actor;
import engine.actions.Action;
import engine.positions.Exit;
import engine.positions.GameMap;
import engine.positions.Location;
import engine.weapons.Weapon;
import engine.weapons.WeaponItem;
import game.actors.enemies.EnemyTypes;
import game.actions.AttackAction;

import java.util.List;
import java.util.Random;

/**
 * A class that figures out an Attack Action is applicable
 */
public class AggressiveBehaviour implements Behaviour {

    /**
     * Random number generator
     */
    private final Random random = new Random();

    /**
     * Returns a Attack action is there are enemies to attack with a 50% chance of a group attack if possible
     * If no attack possible return null
     *
     * @param actor the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return an Action, or null
     */
    public Action getAction(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(actor);
        if(currentLocation == null){
            return null;
        }
        for (Exit exit : currentLocation.getExits()) {
            Location adjacentLocation = exit.getDestination();
            Actor adjacentActor = map.getActorAt(adjacentLocation);
            if (adjacentActor != null && adjacentActor != actor) {
                List<EnemyTypes> actorTypes = actor.findCapabilitiesByType(EnemyTypes.class);
                List<EnemyTypes> adjacentTypes = adjacentActor.findCapabilitiesByType(EnemyTypes.class);
                boolean actorAlly = false;
                for (EnemyTypes actorType : actorTypes){
                    if (adjacentTypes.contains(actorType)){
                        actorAlly = true;
                        break;
                    }
                }
                if (actorAlly) {
                    continue;
                }
                List<WeaponItem> weapons = actor.getWeaponInventory();
                Weapon weapon = weapons.get(0);
                if (random.nextInt(100) < 50) {
                    Action uniqueSkill = weapon.getSkill(adjacentActor, adjacentLocation.toString());
                    return uniqueSkill;
                }
                return new AttackAction(adjacentActor, exit.getName(), weapon);
            }
        }
        // No adjacent actors found, return null
        return null;

    }
}