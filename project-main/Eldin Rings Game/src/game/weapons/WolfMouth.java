package game.weapons;

import engine.weapons.WeaponItem;

public class WolfMouth extends WeaponItem {
    /**
     * Constructor
     */
    public WolfMouth(){
        super("Wolf Mouth", '?', 97, "bites", 95);
        super.portable = false;
    }
}
