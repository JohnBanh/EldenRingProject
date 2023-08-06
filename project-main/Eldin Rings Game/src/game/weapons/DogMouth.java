package game.weapons;

import engine.weapons.WeaponItem;

public class DogMouth extends WeaponItem {
    /**
     * Constructor
     */
    public DogMouth(){
        super("Dog Mouth", '?', 101, "bites", 93);
        super.portable = false;
    }
}