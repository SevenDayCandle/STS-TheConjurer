package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleRelic
public class FortuneGlue extends PCLRelic {
    public static final PCLRelicData DATA = register(FortuneGlue.class, ConjurerResources.conjurer)
            .setProps(RelicTier.SHOP, LandingSound.MAGICAL);
    public static final int MULT = 12;

    public FortuneGlue() {
        super(DATA);
        this.counter = getValue();
    }

    @Override
    public int changeRareCardRewardChance(int rareCardChance) {
        return rareCardChance * MULT;
    }

    @Override
    public int changeUncommonCardRewardChance(int rareCardChance) {
        return rareCardChance * MULT;
    }

    @Override
    public int getValue() {
        return 3;
    }

    @Override
    public void onVictory() {
        super.onVictory();

        this.flash();
        --this.counter;
        if (this.counter == 0) {
            usedUp();
        }
    }
}