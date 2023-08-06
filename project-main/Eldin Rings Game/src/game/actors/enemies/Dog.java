package game.actors.enemies;

import game.utils.RandomNumberGenerator;
import game.weapons.DogMouth;

/**
 * A Dog Enemy
 */
public class Dog extends Enemy {

    /**
     * Constructor
     */
    public Dog() {
        super("Dog", 'a', 104, RandomNumberGenerator.getRandomInt(52, 1390));
        super.addWeaponToInventory(new DogMouth());
        this.addCapability(EnemyTypes.STORMVEILENEMY);
    }
}
