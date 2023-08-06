package game.weapons;

import engine.actions.Action;
import engine.actors.Actor;
import engine.weapons.WeaponItem;
import game.actions.GroupAttackAction;

public class CrabbySlam extends WeaponItem {
    /**
     * Constructor
     */
    public CrabbySlam() {
        super("Crabby Slam", '?', 208, "slams", 90);
        super.portable = false;
    }

    /**
     * gets the skill group attack action for the weapon
     * @param target target actor
     * @param direction target actor location
     * @return GroupAttackAction skill
     */
    public Action getSkill(Actor target, String direction){
        Action uniqueSkill = new GroupAttackAction(this, target, direction);
        return uniqueSkill;
    }
}
