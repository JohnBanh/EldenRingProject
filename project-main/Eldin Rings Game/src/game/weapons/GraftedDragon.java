package game.weapons;

import engine.weapons.WeaponItem;
/**
 * A weapon that can be used to attack the enemy and is dropped by nothing.
 * It deals 89 damage with 90% hit rate
 */
public class GraftedDragon extends WeaponItem {
    public GraftedDragon() {
        super("Grafted Dragon", 'N', 89, "unleashes on", 90);
    }
}
