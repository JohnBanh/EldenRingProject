package game.positions;

import engine.actors.Actor;
import engine.positions.Ground;
import engine.positions.Location;
import game.actions.DeathAction;
import game.actors.Player;
import game.actors.Status;

/**
 * A class that represents the cliff.
 */
public class Cliff extends Ground {
    /**
     * Previous location before the actors turn
     */
    Location oldLocation;

    /**
     * The player
     */
    Player player;

    /**
     * Constructor
     */
    public Cliff() {
        super('+');
    }

    /**
     * only the player can fall off the cliff
     * @param actor the Actor to check
     * @return
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
    }

    public void tick(Location location){
        if (location.getActor() != null) {
            Actor actor = location.getActor();
            if(location.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)){
                location.map().moveActor(player.getInstance(), oldLocation);
                oldLocation.getActor().hurt(99999);
                System.out.println(actor.toString() + " fell off a cliff. \n" + new DeathAction(actor).execute(actor, location.map()));
            }
        }
        oldLocation = location.map().locationOf(player.getInstance());
    }
}

