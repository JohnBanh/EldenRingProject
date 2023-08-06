package game.actors.enemies;

import game.utils.RandomNumberGenerator;
import game.weapons.Scimitar;

/**
 * Skeletal Bandit
 */
public class SkeletalBandit extends Enemy {

    /**
     * Constructor
     */
    public SkeletalBandit() {
        super("Skeletal Bandit", 'b', 184, RandomNumberGenerator.getRandomInt(35, 892));
        super.addWeaponToInventory(new Scimitar());
        this.addCapability(EnemyTypes.SKELETALENEMY);
    }
}

