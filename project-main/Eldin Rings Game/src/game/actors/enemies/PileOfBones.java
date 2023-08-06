package game.actors.enemies;

import engine.actions.Action;
import engine.actions.ActionList;
import engine.actions.DoNothingAction;
import game.actions.ReviveSkeletonAction;
import engine.actors.Actor;
import engine.displays.Display;
import engine.positions.GameMap;
import engine.positions.Location;
import game.actors.Status;

/**
 * What a skeletal enemy becomes after death
 */
public class PileOfBones extends Enemy {
    /**
     * turns before revival
     */
    int turns = 3;
    /**
     * The actor before bones
     */
    Actor skeletalEnemy;

    /**
     * the amount of runes the PileOfBones is worth
     */
    private int runeCount;
    /**
     * Constructor
     */
    public PileOfBones(Actor skeletalEnemy,int runeCount) {
        super("Pile of Bones", 'X', 1,runeCount);
        this.skeletalEnemy = skeletalEnemy;
        this.runeCount = runeCount;
        this.addCapability(Status.DEAD);
        this.addCapability(EnemyTypes.SKELETALENEMY);
    }

    /**
     * Revives the skeleton if 3 turns are up
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return actions
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        turns--;
        if (turns < 0) {
            Location location = map.locationOf(this);
            map.removeActor(this);
            map.addActor(skeletalEnemy, location);
            return new ReviveSkeletonAction();
        }

        return new DoNothingAction();
    }
}
