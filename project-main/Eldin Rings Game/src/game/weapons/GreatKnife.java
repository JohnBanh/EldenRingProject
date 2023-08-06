package game.weapons;

import engine.actions.Action;
import engine.actors.Actor;
import game.actions.Quickstep;

/**
 * The class for the Great Knife weapon
 */
public class GreatKnife extends SellableWeapon {
    /**
     * Constructor.
     */
    public GreatKnife() {
        super("Great Knife", '/', 75, "stabs", 70,350);
    }
    /**
     * Returns whatever Skill there is needed to be returned
     * @see engine.weapons.Weapon
     * @param target target actor
     * @param direction
     * @return
     */
    public Action getSkill(Actor target, String direction){
        Action uniqueSkill = new Quickstep(this, target, direction);
        return uniqueSkill;
    }
}
