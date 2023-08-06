package game.actions;
import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.Exit;
import engine.positions.GameMap;
import engine.positions.Location;
import game.actors.guests.Ally;
import game.actors.guests.Invader;
import game.utils.RandomNumberGenerator;
import game.weapons.Club;
import game.weapons.GreatKnife;
import game.weapons.Uchigatana;
/**
 * Action Class that spawns an Ally or Invader with a randomness system
 */
public class SpawnAction extends Action{
    Location summonSignLocation;
    Location spawnLocation;

    /**
     * Constructor.
     * @param location
     */
    public SpawnAction(Location location){summonSignLocation = location;}

    /**
     * execute method for when user decides to execute. Spawns Ally or Invader with a 50% chance each and a random class.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int spawnType = RandomNumberGenerator.getRandomInt(1,2);
        int spawnClass = RandomNumberGenerator.getRandomInt(1,4);

        for(Exit moveableLocations : summonSignLocation.getExits()){ //For each moveable location that can be moved to in the exits of the current location do:
            Location destination = moveableLocations.getDestination();
            if(!destination.containsAnActor()){ //Make sure the next destination does not contain another actor (e.g enemy)
                spawnLocation = destination;
            }
        }
        //Chooses random class first and then either ally or invader randomly
        switch (spawnClass) {
            //Samurai
            case 1:
                if(spawnType ==2){
                    map.addActor(new Invader(455, new Uchigatana()),spawnLocation);
                }else if(spawnType ==1){
                    map.addActor(new Ally(455, new Uchigatana()),spawnLocation);
                }
                break;
            //Bandit
            case 2:
                if(spawnType ==2){
                    map.addActor(new Invader(414, new GreatKnife()),spawnLocation);
                }else if(spawnType ==1){
                    map.addActor(new Ally(414, new GreatKnife()),spawnLocation);
                }

                break;
            //Wretch
            case 3:
                if(spawnType ==2){
                    map.addActor(new Invader(414, new Club()),spawnLocation);
                }
                else if(spawnType ==1){
                    map.addActor(new Ally(414, new Club()),spawnLocation);
                }
                break;
            //Astrologer
            case 4:
                if(spawnType ==2){
                    map.addActor(new Invader(396, new Club()),spawnLocation);
                }
                else if(spawnType ==1){
                    map.addActor(new Ally(396, new Club()),spawnLocation);
                }
                break;
        }
        String returnStatement = null;
        if(spawnType == 1){
            returnStatement = "Ally has been summoned";
        }else if (spawnType == 2){
            returnStatement = "Invader has been summoned";
        }
        return returnStatement;


    }

    /**
     * menu description
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " summons guest";
    }
}
