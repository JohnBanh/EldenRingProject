package game.behaviours;

import engine.actors.Actor;
import engine.actions.Action;
import engine.positions.Exit;
import engine.positions.GameMap;
import engine.positions.Location;
import engine.actions.MoveActorAction;
import game.actors.Player;

/**
 * A class that figures out a MoveAction that will move the actor one step 
 * closer to a target Actor.
 */
public class FollowBehaviour implements Behaviour {
	/**
	 * Constructor.
	 *
	 */
	public FollowBehaviour() {
	}

	@Override
	public Action getAction(Actor actor, GameMap map) {
		Player player = null;
/*		Actor target = null;
		for (int x : map.getXRange()) {
			for (int y : map.getYRange()) {
				Location location = map.at(x, y);
				if (map.isAnActorAt(location)) {
					Actor a = location.getActor();
					if (a.hasCapability(Status.HOSTILE_TO_ENEMY)) {
						target = a;
						break;
					}
				}
			}
			if (target != null) {
				break;
			}
		}

		if (target == null || !map.contains(actor)) {
			return null;
		}*/

		Location here = map.locationOf(actor);
		Location there = map.locationOf(player.getInstance());

		int currentDistance = distance(here, there);
		if (currentDistance == 2) {
			for (Exit exit : here.getExits()) {
				Location destination = exit.getDestination();
				if (destination.canActorEnter(actor)) {
					int newDistance = distance(destination, there);
					if (newDistance < currentDistance) {
						return new MoveActorAction(destination, exit.getName());
					}
				}
			}
		}

		return null;
	}


	/**
	 * Compute the Manhattan distance between two locations.
	 *
	 * @param a the first location
	 * @param b the first location
	 * @return the number of steps between a and b if you only move in the four cardinal directions.
	 */
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}
}