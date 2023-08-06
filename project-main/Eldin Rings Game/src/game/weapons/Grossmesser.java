package game.weapons;

import engine.actions.Action;
import engine.actors.Actor;
import game.actions.GroupAttackAction;

public class Grossmesser extends SellableWeapon {

    /**
     * Constructor
     */
    public Grossmesser() {
        super("Grossmesser", '?', 115, "slashes", 85,100);
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