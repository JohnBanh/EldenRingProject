package game.weapons;

import engine.weapons.WeaponItem;
/**
 * A weapon that can be used to attack the enemy and is dropped by nothing.
 * It deals 84 damage with 84% hit rate
 */
public class AxeOfGodrick extends WeaponItem {

    public AxeOfGodrick() {
        super("Axe of Godrick", 'T', 142, "hacks at", 84);
    }
}
