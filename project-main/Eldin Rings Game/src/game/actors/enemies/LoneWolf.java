package game.actors.enemies;

import game.utils.RandomNumberGenerator;
import game.weapons.WolfMouth;

/**
 * BEHOLD, DOG!
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class LoneWolf extends Enemy{

    /**
     * Constructor
     */
    public LoneWolf() {
        super("Lone Wolf", 'h', 102, RandomNumberGenerator.getRandomInt(55,1470));
        super.addWeaponToInventory(new WolfMouth());
        this.addCapability(EnemyTypes.CANIDAEENEMY);
    }
}

