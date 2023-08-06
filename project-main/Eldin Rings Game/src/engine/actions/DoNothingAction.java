package engine.actions;

import engine.actors.Actor;
import engine.positions.GameMap;

/**
 * An Action that doesn't do anything.
 * Use this to implement waiting or similar actions in game clients.
 */
public class DoNothingAction extends Action {

	/**
	 * Constructor
	 */
	public DoNothingAction() {
	}

	/**
	 * when this action is executed, it will return "actor does nothing"
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return the String describing that the current actor does nothing
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}

	/**
	 * In the actions menu, the description for this action will be "actor does nothing"
	 *
	 * @param actor The actor performing the action.
	 * @return the String "actor does nothing" to be shown in the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " does nothing";
	}

	/**
	 * By default, we assign 5 as a hotkey for the do nothing action
	 *
	 * @return the String 5
	 */
	@Override
	public String hotkey() {
		return "5";
	}
}
