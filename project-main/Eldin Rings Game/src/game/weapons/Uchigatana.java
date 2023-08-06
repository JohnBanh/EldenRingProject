package game.weapons;


import engine.actions.Action;
import engine.actors.Actor;
import game.actions.Unsheathe;

/**
 * The class for the Uchigatana weapon
 */

public class Uchigatana extends SellableWeapon {
    /**
     * Constructor.
     */
    public Uchigatana() {
        super("Uchigatana", ')', 115, "slashes", 80,500);

    }

    /**
     * Returns whatever Skill there is needed to be returned
     * @see engine.weapons.Weapon
     * @param target target actor
     * @param direction
     * @return
     */
    //getSkill method is from the Weapon Interface -> Basically return whatever Skill there is needed to be returned
    public Action getSkill(Actor target, String direction){
        Action uniqueSkill = new Unsheathe(this, target, direction);
        return uniqueSkill;
    }
}
