package game.actors.enemies;

import game.utils.RandomNumberGenerator;
import game.weapons.Grossmesser;

/**
 * Heavy Skeletal Swordsman
 */
public class HeavySkeletalSwordsman extends Enemy {
    /**
     * Constructor
     */
    public HeavySkeletalSwordsman() {
        super("Heavy Skeletal Swordsman", 'q', 153, RandomNumberGenerator.getRandomInt(35, 892));
        super.addWeaponToInventory(new Grossmesser());
        this.addCapability(EnemyTypes.SKELETALENEMY);
    }
}