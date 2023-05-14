package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleRelic
public class PeriodicTable extends PCLRelic {
    public static final PCLRelicData DATA = register(PeriodicTable.class, ConjurerResources.conjurer)
            .setProps(RelicTier.STARTER, LandingSound.SOLID);

    public PeriodicTable() {
        super(DATA);
    }

    @Override
    public void atBattleStart() {
        PCLActions.bottom.draw(getValue())
                .setFilter(f -> f.type == PCLEnum.CardType.SUMMON, true)
                .addCallback(cards -> {
                    for (AbstractCard c : cards) {
                        PCLActions.bottom.modifyCost(c, -getValue(), false, true);
                    }
                });
    }

    public int getValue() {
        return 1;
    }
}