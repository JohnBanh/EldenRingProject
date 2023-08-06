package game.behaviours;

import java.util.ArrayList;
import java.util.Random;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.Exit;
import engine.positions.GameMap;
import engine.positions.Location;
import game.actors.enemies.EnemyTypes;

public class WanderBehaviour implements Behaviour {
	
	private final Random random = new Random();

	/**
	 * Returns a MoveAction to wander to a random location, if possible.
	 * Despawns the actor with a 10% chance
	 * If no movement is possible, returns null.
	 * 
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no MoveAction is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<>();

		if (random.nextInt(100) < 10){

			if(!(actor.hasCapability(EnemyTypes.ALLY)|| actor.hasCapability(EnemyTypes.INVADER))) //Do not want to despawn allies and invaders
				map.removeActor(actor);
			return null;
		}
		
		for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
            	actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
            }
        }
		
		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
		else {
			return null;
		}

	}
}
