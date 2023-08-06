package game.actions;

import engine.actors.Actor;
import engine.items.PickUpItemAction;
import engine.positions.GameMap;
import game.utils.RuneManager;
import game.items.DroppedRunes;

public class RunesPickUpAction extends PickUpItemAction{
    private final DroppedRunes droppedRunes;
    public RunesPickUpAction(DroppedRunes droppedRunes) {
        super(droppedRunes);
        this.droppedRunes =droppedRunes;
    }

    /**
     *When dropped runes is "picked up", the rune count of the dropped runes is added to the balance of the player's
     * rune count. the dropped runes does not add the item to inventory but instead just removes it from the map.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the action suitable for feedback in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        RuneManager runeManager = RuneManager.getInstance();
        //remove dropped item from map
        map.locationOf(actor).removeItem(droppedRunes);
        //add the rune count of dropped runes to player's rune count
        runeManager.addRunes(actor,droppedRunes.getRuneValue());
        return actor.toString() + " has picked up " + droppedRunes.getRuneValue();
    }
}

