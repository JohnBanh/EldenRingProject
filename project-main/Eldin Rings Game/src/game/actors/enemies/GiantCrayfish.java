package game.actors.enemies;

import game.utils.RandomNumberGenerator;
import game.weapons.GiantPincer;

/**
 * A Giant Crayfish
 */
public class GiantCrayfish extends Enemy{

    /**
     * Constructor
     */
    public GiantCrayfish() {
        super("Giant Crayfish", 'R', 4803,RandomNumberGenerator.getRandomInt(500,2374));
        super.addWeaponToInventory(new GiantPincer());
        this.addCapability(EnemyTypes.CUSTACEANENEMY);
    }
}