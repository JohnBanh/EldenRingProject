package game.items.consumables;

import engine.actors.Actor;
import game.utils.RuneManager;
import game.utils.RandomNumberGenerator;

public class GoldenRunes extends ConsumableItem implements Consumable{
    private int runeBuff;

    /***
     * Constructor.
     */
    public GoldenRunes() {
        super("Golden Runes", '*', true, ConsumableStatus.NON_PERMANENT,1);
        this.runeBuff = RandomNumberGenerator.getRandomInt(200,10000);
    }
    /**
     * what happens when you consume the item
     * @param actor the actor that is consuming the consumable
     */
    @Override
    public void consume(Actor actor) {
        RuneManager runeManager = RuneManager.getInstance();
        runeManager.addRunes(actor,runeBuff);
    }

}
