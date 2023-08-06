package game.actions;

import engine.actions.Action;
import engine.actions.ActionList;
import engine.actors.Actor;
import engine.items.Item;
import engine.positions.GameMap;
import engine.positions.Location;
import engine.weapons.WeaponItem;
import game.actors.enemies.EnemyTypes;
import game.utils.ResetManager;
import game.utils.RuneManager;
import game.actors.Status;
import game.actors.enemies.PileOfBones;

/**
 * An action executed if an actor is killed.
 */
public class DeathAction extends Action {
    /**
     * Reset manager
     */
    ResetManager resetManagerInstance;

    /**
     * Attacker of the actor
     */
    private Actor attacker;

    public DeathAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was
     * also turns SkeletalEnemy into PileOfBones
     * @param target The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        RuneManager runeManager = RuneManager.getInstance();
        String result = "";
        //DEATH RESET
        if (!target.isConscious() && target.hasCapability(Status.HOSTILE_TO_ENEMY)){
            result += menuDescription(target);
            resetManagerInstance = ResetManager.getInstance();
            this.resetManagerInstance.run(map, true);
        }
        else {

            ActionList dropActions = new ActionList();
            // drop all items
            if (target.hasCapability(EnemyTypes.SKELETALENEMY) && !(target.hasCapability(Status.DEAD))) {
                //create pile of bones and pass on the runeCount from Skeletal Enemy
                PileOfBones pileOfBones = new PileOfBones(target, runeManager.getBalance(target));
                for (Item item : target.getItemInventory())
                    pileOfBones.addItemToInventory(item);
                for (WeaponItem weapon : target.getWeaponInventory())
                    pileOfBones.addWeaponToInventory(weapon);
                // remove actor
                Location location = map.locationOf(target);
                map.removeActor(target);
                map.addActor(pileOfBones, location);
                result += target.toString() + " turned into a pile of bones.";
                return result;
            }
            //drop all items from the target's inventory
            for (Item item : target.getItemInventory())
                dropActions.add(item.getDropAction(target));
            for (WeaponItem weapon : target.getWeaponInventory())
                dropActions.add(weapon.getDropAction(target));
            for (Action drop : dropActions)
                drop.execute(target, map);

            //If the attacker is a player, add the runeCount of the enemy to the player's runePouch
            if (attacker.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                runeManager.addRunes(attacker, runeManager.getBalance(target));
            }
            //remove actor
            map.removeActor(target);
            result += menuDescription(target);

        }
        return result;
    }

    /**
     * the string of the actor dying
     * @param actor The actor performing the action.
     * @return actor is killed
     */
    @Override
    public String menuDescription(Actor actor) {
        if(actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            return(
                    "`YMM'   `MM' .g8\"\"8q. `7MMF'   `7MF'    `7MM\"\"\"Yb. `7MMF'`7MM\"\"\"YMM  `7MM\"\"\"Yb.   \n" +
                            "  VMA   ,V .dP'    `YM. MM       M        MM    `Yb. MM    MM    `7    MM    `Yb. \n" +
                            "   VMA ,V  dM'      `MM MM       M        MM     `Mb MM    MM   d      MM     `Mb \n" +
                            "    VMMP   MM        MM MM       M        MM      MM MM    MMmmMM      MM      MM \n" +
                            "     MM    MM.      ,MP MM       M        MM     ,MP MM    MM   Y  ,   MM     ,MP \n" +
                            "     MM    `Mb.    ,dP' YM.     ,M        MM    ,dP' MM    MM     ,M   MM    ,dP' \n" +
                            "   .JMML.    `\"bmmd\"'    `bmmmmd\"'      .JMMmmmdP' .JMML..JMMmmmmMMM .JMMmmmdP'   \n");
        }
        return actor + " is killed.";
    }
}


