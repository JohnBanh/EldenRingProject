package game.weapons;


/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 */
public class Club extends SellableWeapon {


    /**
     * Constructor
     */
    public Club() {
        super("Club", '!', 103, "bonks", 80,100);
    }

}
