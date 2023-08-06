package game.actors.enemies;

import game.utils.RandomNumberGenerator;
import game.weapons.DogMouth;

/**
 * A Godrick Soldier Enemy
 */
public class GodrickSoldier extends Enemy{

    /**
     * Constructor
     */
    public GodrickSoldier() {
        super("Godrick Soldier", 'p', 198, RandomNumberGenerator.getRandomInt(38, 70));
        super.addWeaponToInventory(new DogMouth());
        this.addCapability(EnemyTypes.STORMVEILENEMY);
    }
}