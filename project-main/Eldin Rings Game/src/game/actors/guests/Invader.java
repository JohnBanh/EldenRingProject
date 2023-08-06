package game.actors.guests;

import engine.positions.GameMap;
import engine.weapons.WeaponItem;
import game.actors.enemies.Enemy;
import game.actors.enemies.EnemyTypes;
import game.utils.RandomNumberGenerator;
import game.utils.Resettable;
/**
 * An Invader Enemy class that extends Enemy
 * Created by:
 * @author John Banh
 */
public class Invader extends Enemy implements Resettable {


    /**
     * Constructor.
     * @param hitPoints
     * @param weapon
     */
    public Invader(int hitPoints, WeaponItem weapon){
        super("Invader",'ඞ', hitPoints, RandomNumberGenerator.getRandomInt(1358,5578));
        super.addWeaponToInventory(weapon);
        this.addCapability(EnemyTypes.INVADER);
    }

    /**
     * reset method
     * @param map
     * @param playerDead
     */
    @Override
    public void reset(GameMap map, boolean playerDead) {
        if(playerDead == true){
            map.removeActor(this);
        }
    }
}
