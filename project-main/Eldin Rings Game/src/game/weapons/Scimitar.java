package game.weapons;

import engine.actions.Action;
import engine.actors.Actor;
import engine.weapons.WeaponItem;
import game.actions.GroupAttackAction;

public class Scimitar extends WeaponItem {
    public Scimitar() {
        super("Scimitar", 's', 118, "slashes", 88);
    }

    public Action getSkill(Actor target, String direction){
        Action uniqueSkill = new GroupAttackAction(this, target, direction);
        return uniqueSkill;
    }
}
