package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.relics.PCLRelic;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleRelic
public class PeriodicTable extends PCLRelic {
    public static final String ID = createFullID(ConjurerResources.conjurer, PeriodicTable.class);

    public PeriodicTable() {
        super(ID, RelicTier.STARTER, LandingSound.SOLID);
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