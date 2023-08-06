package game.actors.enemies;

import game.utils.RandomNumberGenerator;
import game.weapons.DoggySlam;

/**
 * BEHOLD, DOG!
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class GiantDog extends Enemy {
    /**
     * Constructor
     */
    public GiantDog() {
        super("Giant Dog", 'G', 693, RandomNumberGenerator.getRandomInt(313, 1808));
        super.addWeaponToInventory(new DoggySlam());
        this.addCapability(EnemyTypes.CANIDAEENEMY);
    }
}
