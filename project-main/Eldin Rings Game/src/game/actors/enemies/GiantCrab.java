package game.actors.enemies;

import game.utils.RandomNumberGenerator;
import game.weapons.CrabbySlam;

/**
 * A Giant Crab Enemy
 */
public class GiantCrab extends Enemy{

    /**
     * Constructor
     */
    public GiantCrab() {
        super("Giant Crab", 'c', 407,RandomNumberGenerator.getRandomInt(318,4961));
        super.addWeaponToInventory(new CrabbySlam());
        this.addCapability(EnemyTypes.CUSTACEANENEMY);
    }
}
